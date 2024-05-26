package org.clases.gestores;

import org.clases.Comentario;

import org.clases.clasesUsuarios.Usuario;
import org.controladorArchivos.ImpresoraJSON;
import org.enumeradores.Categoria;
import org.enumeradores.Estado;
import org.clases.clasesContenido.Contenido;
import org.clases.clasesContenido.ContenidoInteractivo;
import org.clases.clasesContenido.ContenidoNoInteractivo;
import org.interfaces.IIdentificable;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void cargarDatosARedSocial(){
        Usuario usuario1 = new Usuario("diafir", "2002", "yahveh@gmail.com");
        Comentario comentario1 = new Comentario(1, usuario1.getIdUsuario(), Estado.ACTIVO, "jajajaj xd");
        Comentario comentario2 = new Comentario(2, usuario1.getIdUsuario(), Estado.ACTIVO, "tremendo pa");
        Comentario comentario3 = new Comentario(3, usuario1.getIdUsuario(), Estado.ACTIVO, "i cant belive it");
        this.comentarios.add(comentario1);
        this.comentarios.add(comentario2);
        this.comentarios.add(comentario3);
        this.usuarios.add(usuario1);
        this.contenidos.add(new ContenidoInteractivo("Cómo cultivar tomates en casa", "1 . Descubre los mejores consejos y trucos para obtener una cosecha abundante.", Categoria.ACTUALIDAD, usuario1.getIdUsuario()));
        this.contenidos.add(new ContenidoNoInteractivo("Los beneficios del yoga para la salud mental", "2 mental. Descubre cómo esta práctica milenaria puede ayudarte a reducir el estrés, mejorar la concentración y encontrar la paz interior.", Categoria.ACTUALIDAD, usuario1.getIdUsuario()));
        Contenido contenido1 = new ContenidoInteractivo("Entrevista exclusiva con Elon Musk", "3 . Descubre sus ideas revolucionarias sobre el futuro de la tecnología, el espacio y la sostenibilidad.", Categoria.ACTUALIDAD, usuario1.getIdUsuario());
        ((ContenidoInteractivo)contenido1).agregarComentario(comentario1);
        ((ContenidoInteractivo)contenido1).agregarComentario(comentario2);
        ((ContenidoInteractivo)contenido1).agregarComentario(comentario3);
        this.contenidos.add(contenido1);
        this.contenidos.add(new ContenidoNoInteractivo("Reseña de la última película de Quentin Tarantino", "4  de Quentin Tarantino. Analizamos la trama, los personajes y el estilo único del aclamado director.", Categoria.ACTUALIDAD, usuario1.getIdUsuario()));
        this.contenidos.add(new ContenidoNoInteractivo("Los mejores destinos para mochileros en América del Sur", "5  en América del Sur. Desde las playas paradisíacas de Brasil hasta las impresionantes montañas de Perú, tenemos todo cubierto.", Categoria.VIDEOJUEGOS, usuario1.getIdUsuario()));
        this.usuarios.add(new Usuario("1", "1", "yahveh@gmail.com"));
    }

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

    public boolean agregarContenido(Contenido contenido){
        boolean flag = false;
        if(contenido != null) {
            this.contenidos.add(contenido);
            System.out.println(this.contenidos);
        }
        return flag;
    }

    public Contenido obtenerContenido(){
        Contenido contenido = null;
        Iterator<Contenido> iterador = obtenerIteradorContenido();
        if(iterador.hasNext()){
            contenido = iterador.next();
        }
        return contenido;
    }

    public Contenido avanzar(Contenido contenidoActual){
        Contenido contenido = null;
        boolean flagEncontrado = false;
        Iterator<Contenido> iterator = obtenerIteradorContenido();
        while (iterator.hasNext()) {
            contenido = iterator.next();
            if (flagEncontrado) {
                return contenido;
            }
            if (contenido.equals(contenidoActual)) {
                flagEncontrado = true;
            }
        }
        return contenido;
    }

    public Contenido retroceder (Contenido contenidoActual){
        Contenido contenido = contenidoActual;
        Iterator<Contenido> iterator = obtenerIteradorContenido();
        Contenido contenidoAnterior = contenidoActual;
        while (iterator.hasNext()) {
            contenido = iterator.next();
            if (contenido.equals(contenidoActual)) {
                return contenidoAnterior;
            }
            contenidoAnterior = contenido;
        }

        return contenido;
    }

    // Método para ver el contenido actual
    public Iterator<Contenido> obtenerIteradorContenido() {
        // Crear una copia de la colección de contenidos
        Iterator<Contenido> iterator = this.contenidos.iterator();
        return iterator;
    }

    // Método para ver el contenido actual filtrando categorias y estado de contenido
    public Iterator<Contenido> obtenerIteradorContenido(Usuario usuario) {
        Set<Categoria> preferencias = new HashSet<>(usuario.getPreferencias());
        return this.contenidos.stream()
                .filter(c -> c.getEstado() == Estado.ACTIVO && (preferencias.isEmpty() || preferencias.contains(c.getCategoria())))
                .toList()
                .iterator();
    }

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

    public <T extends IIdentificable> T buscarPorId(int id, Class<T> tipo) {
        Stream<? extends IIdentificable> stream = null;

        if (tipo == Usuario.class) {
            stream = this.usuarios.stream();
        } else if (tipo == Comentario.class) {
            stream = this.comentarios.stream();
        } else if (tipo == Contenido.class) {
            stream = this.contenidos.stream();
        }

        if (stream != null) {
            Optional<? extends IIdentificable> result = stream.filter(item -> item.getId() == id).findFirst();
            if (result.isPresent()) {
                return tipo.cast(result.get());
            }
        }

        return null;
    }

    public void actualizarDatosUsuario(String nuevoUserName, String nuevaPassword, String nuevoMail, int id){
        Usuario usuario = buscarPorId(id, Usuario.class);
        usuario.setMail(nuevoMail);
        usuario.setPassword(nuevaPassword);
        usuario.setUserName(nuevoUserName);
    }

    public List<Comentario> devolverListaDeComentarioDeContenido(ContenidoInteractivo contenido){
        List<Comentario> comentariosOrdenados = comentarios.stream()
                .filter(comentario -> comentario.getIdContenido() == contenido.getIdContenido())
                .sorted()
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
            System.out.println("Ha ocurrido un error al guardar los datos en JSON.");
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

