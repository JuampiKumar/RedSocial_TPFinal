package org.clases.clasesContenido;

import org.clases.Comentario;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Categoria;
import org.clases.clasesContenido.Contenido;

import java.util.LinkedList;
import java.util.List;

public class ContenidoInteractivo extends Contenido {
    //Atributos
    private int likes;
    private List<Comentario> comentarios;

    //Constructor
    public ContenidoInteractivo(String titulo, String contenido, Categoria categoria, Usuario usuario) {
        super(titulo, contenido, categoria, usuario);
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

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario){
        this.comentarios.add(comentario);
    }

    public void eliminarComentario(Comentario comentario){
        this.comentarios.remove(comentario);
    }
}
