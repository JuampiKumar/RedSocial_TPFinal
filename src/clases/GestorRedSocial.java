package clases;

import clases.clasesContenido.Contenido;
import clases.clasesUsuarios.Usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class GestorRedSocial {
    //Atributos
    private Scanner scanner;
    private TreeSet<Contenido> contenidos;
    private List<Usuario> usuarios;
    private List<Comentario> comentarios;

    //Constructor
    public GestorRedSocial(){
        this.scanner = new Scanner(System.in);
        this.comentarios = new LinkedList<>();
        this.usuarios = new LinkedList<>();
        this.contenidos = new TreeSet<>();
    }

    //Metodos
    public int menuInicio(){
        System.out.println("Bienvenido a la Red Social");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print("Ingrese su opción: ");
        return scanner.nextInt();
    }

    public void menuRegistrarUsuario(){
        String nombre = " ";
        String userName = " ";
        String passWord = " ";
        String correo = " ";
        System.out.println("Ingrese los datos para registrarse:");
        System.out.print("Nombre: ");
        nombre = scanner.nextLine();
        System.out.print("Nombre de usuario: ");
        userName = scanner.nextLine();
        System.out.print("Contraseña: ");
        passWord = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        correo = scanner.nextLine();
        if(validarUsuario(userName) && validarMail(correo)){
            this.usuarios.add(new Usuario(nombre, userName, passWord, correo));
        }else{
            System.out.println("usuario o correo invalidos");
        }
    }

    private boolean validarUsuario(String userName){
        for (Usuario usuario : this.usuarios) {
            if (usuario.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    private boolean validarMail(String correo) {
        return correo.contains("@") && correo.contains(".com");
    }

    private void menuIngresoUsuario(){
        System.out.println("Ingrese sus datos para iniciar sesión:");
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario usuario = encontrarUsuario(nombreUsuario, contraseña);
        if (usuario != null) {
            System.out.println("¡Inicio de sesión exitoso!");
            // Aquí puedes agregar la lógica para permitir al usuario interactuar con la red social
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos. Por favor, intente de nuevo.");
        }
    }

    private static Usuario encontrarUsuario(String nombreUsuario, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUserName().equals(nombreUsuario) && usuario.getPassword().equals(contraseña)) {
                return usuario;
            }
        }
        return null;
    }


}
