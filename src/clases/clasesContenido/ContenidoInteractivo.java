package clases.clasesContenido;

import clases.Comentario;
import enumeradores.Categoria;

import java.util.LinkedList;
import java.util.List;

public class ContenidoInteractivo extends Contenido{
    //Atributos
    private int likes;
    private List<Comentario> comentarios;

    //Constructor
    public ContenidoInteractivo(String titulo, String contenido, Categoria categoria) {
        super(titulo, contenido, categoria);
        this.likes = 0;
        this.comentarios = new LinkedList<>();
    }

    //Getters y Setters
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
