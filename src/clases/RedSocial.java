package clases;

import clases.clasesContenido.Contenido;
import clases.clasesUsuarios.Usuario;
import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class RedSocial {
    //Atributos
    GestorRedSocial gestor;

    //Constructor
    public RedSocial(){
        this.gestor = new GestorRedSocial();
    }

    //Metodos
    public void inicio(){
        int opcion = 0;
        do{
            opcion = gestor.menuInicio();
            switch(opcion){
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    ingresoUsuario();
                    break;
            }
        }while(opcion != 3);
    }

    private void registrarUsuario(){
        gestor.menuRegistrarUsuario();
    }

    private void ingresoUsuario(){
        gestor.menuIngresoUsuario();
    }
}
