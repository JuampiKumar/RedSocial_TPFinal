package clases.gestores;

import clases.Comentario;
import clases.PanelGrafico;
import clases.clasesContenido.Comunicado;
import clases.clasesContenido.Contenido;
import clases.clasesUsuarios.Usuario;
import enumeradores.Categoria;

import java.util.*;

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
        this.usuarios.add(new Usuario("diafir", "2002", "yahveh@gmail.com"));
        this.contenidos.add(new Comunicado("Cómo cultivar tomates en casa", "Aprende paso a paso cómo cultivar tomates en tu jardín o terraza. Descubre los mejores consejos y trucos para obtener una cosecha abundante.", Categoria.ACTUALIDAD, "GreenThumb"));
        this.contenidos.add(new Comunicado("Los beneficios del yoga para la salud mental", "Explora los efectos positivos del yoga en la salud mental. Descubre cómo esta práctica milenaria puede ayudarte a reducir el estrés, mejorar la concentración y encontrar la paz interior.", Categoria.ACTUALIDAD, "YogiPro"));
        this.contenidos.add(new Comunicado("Entrevista exclusiva con Elon Musk", "Lee la entrevista exclusiva con Elon Musk, CEO de Tesla y SpaceX. Descubre sus ideas revolucionarias sobre el futuro de la tecnología, el espacio y la sostenibilidad.", Categoria.ACTUALIDAD, "TechEnthusiast"));
        this.contenidos.add(new Comunicado("Reseña de la última película de Quentin Tarantino", "Descubre nuestra opinión sobre la última película de Quentin Tarantino. Analizamos la trama, los personajes y el estilo único del aclamado director.", Categoria.ACTUALIDAD, "Cinephile"));
        this.contenidos.add(new Comunicado("Los mejores destinos para mochileros en América del Sur", "Explora los destinos más emocionantes y económicos para mochileros en América del Sur. Desde las playas paradisíacas de Brasil hasta las impresionantes montañas de Perú, tenemos todo cubierto.", Categoria.VIDEOJUEGOS, "WanderlustExplorer"));
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
        System.out.println(this.contenidos);
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
        Contenido contenido = null;
        Iterator<Contenido> iterator = obtenerIteradorContenido();
        Contenido contenidoAnterior = null;
        while (iterator.hasNext()) {
            contenido = iterator.next();
            if (contenido.equals(contenidoActual)) {
                return contenidoAnterior;
            }
            contenidoAnterior = contenido;
        }

        return null;

    }

    // Método para ver el contenido actual
    public Iterator<Contenido> obtenerIteradorContenido() {
        // Crear una copia de la colección de contenidos
        Iterator<Contenido> iterator = this.contenidos.iterator();
        return iterator;
    }

    public void mostrarArbol(){
        Iterator<Contenido> iterador = obtenerIteradorContenido();
        Contenido contenido = null;
        while(iterador.hasNext()) {
            contenido = iterador.next();
            System.out.println(contenido.toString());
        }
    }

}
