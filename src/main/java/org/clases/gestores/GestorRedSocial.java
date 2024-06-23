package org.clases.gestores;

import com.github.javafaker.Faker;
import org.clases.Comentario;

import org.clases.clasesUsuarios.Usuario;
import org.controladorArchivos.ImpresoraJSON;
import org.enumeradores.Categoria;
import org.enumeradores.Estado;
import org.clases.clasesContenido.Contenido;
import org.clases.clasesContenido.ContenidoInteractivo;
import org.clases.clasesContenido.ContenidoNoInteractivo;
import org.excepciones.ErrorValidacionException;
import org.interfaces.IEstado;

import java.io.IOException;
import java.util.*;

import static org.controladorArchivos.Impresora.DEFAULT_PATH;

public class GestorRedSocial {
    //Atributos
    private Scanner scanner;
    private TreeSet<Contenido> contenidos;
    private List<Usuario> usuarios;
    private List<Comentario> comentarios;
    private ImpresoraJSON impresoraJSON;

    //Constructor
    public GestorRedSocial() {
        this.scanner = new Scanner(System.in);
        this.comentarios = new LinkedList<>();
        this.usuarios = new LinkedList<>();
        this.contenidos = new TreeSet<>();
        this.impresoraJSON = new ImpresoraJSON();
    }

    //Metodos
    public void registrarUsuarioNuevo(String userName, String passWord, String correo) throws ErrorValidacionException {
        if (!validarMail(correo)) {
            throw new ErrorValidacionException("Correo electrónico no válido.");
        }
        if (validarUsuario(userName)) {
            throw new ErrorValidacionException("El nombre de usuario ya existe.");
        }
        this.usuarios.add(new Usuario(userName, passWord, correo));
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

    public Usuario encontrarUsuario(String userName, String passWord) throws ErrorValidacionException {
        for(Usuario usuario : this.usuarios){
            if(usuario.getUserName().equals(userName) && usuario.getPassword().equals(passWord)){
                if(usuario.getEstado() == Estado.INACTIVO){
                    throw new ErrorValidacionException("Usuario dado de baja.");
                }
                return usuario;
            }
        }
        throw new ErrorValidacionException("Nombre de usuario o contraseña incorrectos.");
    }

    public boolean agregarContenido(Contenido contenido){
        boolean flag = false;
        if(contenido != null) {
            this.contenidos.add(contenido);
        }
        return flag;
    }

    // Método para ver el contenido actual
    public Iterator<Contenido> obtenerIteradorContenido() {
        return this.contenidos.iterator();
    }

    // Método para ver el contenido actual filtrando categorias y estado de contenido
    public Iterator<Contenido> obtenerIteradorContenido(Usuario usuario) {
        Set<Categoria> preferencias = new HashSet<>(usuario.getPreferencias());
        return this.contenidos.stream()
                .filter(c -> c.getEstado() == Estado.ACTIVO && (preferencias.isEmpty() || preferencias.contains(c.getCategoria())))
                .toList()
                .iterator();
    }

    public Iterator<Usuario> obtenerIteradorUsuarios() {
        return this.usuarios.stream().toList().iterator();
    }

    public Iterator<Comentario> obtenerIteradorComentario(){return this.comentarios.stream().iterator();}

    public boolean agregarPublicacionAUsuario(Usuario usuario, Contenido contenido){
        boolean flag = false;
        if(contenido != null) {
            usuario.agregarPublicado(contenido);
            flag = true;
        }
        return flag;
    }

    public String retornarCantidadLikes(Contenido contenido){
        int cantidad = 0;
        if(contenido instanceof ContenidoInteractivo){
            cantidad = ((ContenidoInteractivo) contenido).getLikes();
        }
        return Integer.toString(cantidad);
    }

    private boolean buscarEnLikeadosDeUsuario(Usuario usuario, Contenido contenido){
        return usuario.buscarEnLikeado(contenido);
    }

    public void likeDisLikeContenido(Usuario usuario, Contenido contenido){
        if(contenido instanceof ContenidoInteractivo){
            if(buscarEnLikeadosDeUsuario(usuario, contenido)){
                ((ContenidoInteractivo) contenido).setLikes(((ContenidoInteractivo) contenido).getLikes()-1);
                usuario.eliminarLikeado(contenido);
            }else{
                ((ContenidoInteractivo) contenido).setLikes(((ContenidoInteractivo) contenido).getLikes()+1);
                usuario.agregarLikeado(contenido);
            }
        }
    }

    public Comentario crearComentario(Usuario usuario, Contenido contenido, String texto){
        Comentario comentario = null;
        if(usuario != null && contenido != null){
            comentario = new Comentario(contenido.getIdContenido(), usuario.getIdUsuario(), Estado.ACTIVO, texto);
        }
        return comentario;
    }

    public void agregarComentario(Usuario usuario, Contenido contenido, String texto){
        if(contenido instanceof ContenidoInteractivo && !texto.trim().isEmpty()){
        Comentario comentario = crearComentario(usuario, contenido, texto);
        this.comentarios.add(comentario);
            ((ContenidoInteractivo)contenido).agregarComentario(comentario);
        }
    }

    public void agregarPreferencia(Usuario usuario,Categoria preferenciaSeleccionada) {
        usuario.getPreferencias().add(preferenciaSeleccionada);
    }

    public void eliminarPreferencia(Usuario usuario,Categoria preferenciaSeleccionada) {
        if (usuario.getPreferencias().contains(preferenciaSeleccionada)) {
            usuario.getPreferencias().remove(preferenciaSeleccionada);
        }
    }

    public Usuario buscarUsuarioPorId(String dato) {
        Optional<Usuario> result = usuarios.stream().filter(item -> item.getIdUsuario().equalsIgnoreCase(dato) || item.getUserName().equalsIgnoreCase(dato)).findFirst();
        return result.orElse(null);
    }

    public Comentario buscarComentarioPorId(String id) {
        Optional<Comentario> result = comentarios.stream().filter(item -> item.getIdComentario().equalsIgnoreCase(id)).findFirst();
        return result.orElse(null);
    }

    public Contenido buscarContenidoPorId(String id) {
        Optional<Contenido> result = contenidos.stream().filter(item -> item.getIdContenido().equalsIgnoreCase(id)).findFirst();
        return result.orElse(null);
    }

    public void actualizarDatosUsuario(String nuevoUserName, String nuevaPassword, String nuevoMail, String id){
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setMail(nuevoMail);
        usuario.setPassword(nuevaPassword);
        usuario.setUserName(nuevoUserName);
    }

    public List<Comentario> devolverListaDeComentarioDeContenido(ContenidoInteractivo contenido){
        List<Comentario> comentariosOrdenados = comentarios.stream()
                .filter(comentario -> comentario.getIdContenido().equals(contenido.getIdContenido()))
                .toList();

        return comentariosOrdenados;
    }

    public void guardarDatosEnJSON() {
        try {
            impresoraJSON.imprimirUsuario(this.usuarios, DEFAULT_PATH + "/usuarios.json");
            impresoraJSON.imprimircContenido(new ArrayList<>(this.contenidos), DEFAULT_PATH + "/contenidos.json");
            impresoraJSON.imprimirComentario(this.comentarios, DEFAULT_PATH + "/comentarios.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatosEnJSON() {
        try {
            this.usuarios = impresoraJSON.leerUsuario(DEFAULT_PATH + "/usuarios.json/usuarios.json");
            this.contenidos = new TreeSet<>(impresoraJSON.leerContenido(DEFAULT_PATH + "/contenidos.json/contenidos.json"));
            this.comentarios = impresoraJSON.leerComentario(DEFAULT_PATH + "/comentarios.json/comentarios.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

