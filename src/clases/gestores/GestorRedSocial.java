package clases.gestores;

import clases.Comentario;
import clases.PanelGrafico;
import clases.clasesContenido.Comunicado;
import clases.clasesContenido.Contenido;
import clases.clasesUsuarios.Usuario;
import enumeradores.Categoria;

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

    public void registrarUsuarioNuevo(String userName, String passWord, String correo){
        if(validarUsuario(userName) == false  && validarMail(correo) == true){
            this.usuarios.add(new Usuario(userName, passWord, correo));
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

    public Usuario encontrarUsuario(String userName, String passWord){
        for(Usuario usuario : this.usuarios){
            if(usuario.getUserName().equals(userName) && usuario.getPassword().equals(passWord)){
                return usuario;
            }
        }
        return null;
    }

    public void agregarContenido(Contenido contenido){
        this.contenidos.add(contenido);
    }
}
