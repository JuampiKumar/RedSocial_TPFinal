package org.controladorArchivos;

import org.clases.clasesUsuarios.Usuario;
import org.clases.clasesContenido.Contenido;
import org.controladorArchivos.Controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.processing.RoundEnvironment;


public class ControladorEXCEL<T> extends Controlador {

    //ESCRITURA DEL ARCHIVO EXCEL
    public String mostrarDatosEXCEL (List<T> listaGenerica) throws IOException{
        return mostrarDatosEXCEL(listaGenerica, DEFAULT_PATH);
    }

    public String mostrarDatosEXCEL (List<T> listaGenerica, String pathArchivo) throws IOException{
        //Se especifica la ruta donde se creara el nuevo archivo de excel
        String ruta = STR."\{pathArchivo}\{DEFAULT_FILENAME}\{EXCEL_EXTENSION}";
        //Se notifica en forma de logger que se inicio el proceso y se printea la ruta
        logger.log(Level.INFO, ruta);
        //Se crea un libro de Excel
        try(Workbook libroExcel = new XSSFWorkbook()){
            //Se crea una hoja en ese libro de Excel
            Sheet hoja = libroExcel.createSheet("PRUEBA");
            //Ahora se crean los encabezados, cargando una fila entera (ROW) celda por celda (CELL)
            //Como el objetivo es que esta clase se use PARA TODAS LAS CLASES, se redirige a un metodo especial
            int opcion = determinarClase(listaGenerica);
            System.out.print(opcion);
            if(opcion != -1) {
                //Este metodo devuelve un Boolean, tengo que incorporar una verificacion o mensaje
                //Creacion de encabezados segun el tipo de clase
                crearEncabezados(opcion, hoja);
                //Carga los datos segun la clase
                //Este metodo devuelve un Boolean, tengo que incorporar una verificacion o mensaje
                cargarEXCEL(listaGenerica, hoja, opcion);
            }
            System.out.print(2);
            //Setea el tamaño de las columnas
            for (int i = 0; i < 4; i++) {
                hoja.autoSizeColumn(i);
            }
            //Escribir el archivo de salida
            try (FileOutputStream fileOut = new FileOutputStream(ruta)) {
                libroExcel.write(fileOut);
            }

        }catch (IOException caso){
            //En caso de error, y como cuando se notifica la ruta del archivo, en este caso se informa el error de creacion
            logger.log(Level.SEVERE, STR."No se creo el archivo.");
        }
        return ruta;
    }

    //Este metodo determina que contiene la lista: Usuarios o Contenidos
    public int determinarClase(List<T> listaGenerica){
        if (listaGenerica.getFirst() instanceof Usuario){
            return 1;
        }else if (listaGenerica.getFirst() instanceof Contenido){
            return 2;
        }
        //Error, no corresponde la lista o esta VACIA, mejorar la verificacion, es a modo de prueba
        return -1;
    }

    public boolean crearEncabezados(int opcion, Sheet hoja){
        Row encabezado = hoja.createRow(0);
        if (opcion == 1){
            encabezado.createCell(0).setCellValue("ID Usuario");
            encabezado.createCell(1).setCellValue("User Name");
            encabezado.createCell(2).setCellValue("Password");
            encabezado.createCell(3).setCellValue("Mail");
            encabezado.createCell(4).setCellValue("Estado");
            return true;
        } else if (opcion == 2) {
            encabezado.createCell(0).setCellValue("ID Contenido");
            encabezado.createCell(1).setCellValue("User Name");
            encabezado.createCell(2).setCellValue("Titulo");
            encabezado.createCell(3).setCellValue("Contenido");
            encabezado.createCell(4).setCellValue("Categoria");
            encabezado.createCell(5).setCellValue("Estado");
            return true;
        }
        return false;
    }

    public boolean cargarEXCEL(List<T> listaGenerica, Sheet hoja, int opcion){
        int rowNum = 1;
        Row fila = hoja.createRow(rowNum++);
        if(opcion == 1){
            for (T t : listaGenerica) {
                fila = hoja.createRow(rowNum++);
                Usuario user = (Usuario) t;
                fila.createCell(0).setCellValue(user.getIdUsuario()); //ID USUARIO
                fila.createCell(1).setCellValue(user.getUserName()); //USER NAME
                fila.createCell(2).setCellValue(user.getPassword()); //PASSWORD
                fila.createCell(3).setCellValue(user.getMail()); //MAIL
                fila.createCell(4).setCellValue(String.valueOf(user.getEstado())); //ESTADO
            }
        }else{
            for(T t : listaGenerica){
                Contenido contenido = (Contenido) t;
                fila.createCell(0).setCellValue(contenido.getIdContenido()); //ID CONTENIDO
                fila.createCell(1).setCellValue(contenido.getUsuario().getUserName()); //USER NAME
                fila.createCell(2).setCellValue(contenido.getTitulo()); //TITULO
                fila.createCell(3).setCellValue(contenido.getContenido()); //CONTENIDO
                fila.createCell(4).setCellValue(String.valueOf(contenido.getCategoria())); //CATEGORIA
                fila.createCell(5).setCellValue(String.valueOf(contenido.getEstado())); //ESTADO
            }
        }

        return false;
    }

    //LECTURA DEL ARCHIVO EXCEL



}
