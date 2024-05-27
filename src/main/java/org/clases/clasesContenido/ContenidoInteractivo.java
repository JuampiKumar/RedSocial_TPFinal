package org.clases.clasesContenido;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.clases.Comentario;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Categoria;
import org.clases.clasesContenido.Contenido;
import org.enumeradores.Estado;

import java.util.LinkedList;
import java.util.List;

public class ContenidoInteractivo extends Contenido {
    //Atributos
    private int likes;
    private List<Integer> comentarios;

    //Constructor
    public ContenidoInteractivo(String titulo, String contenido, Categoria categoria, int idUsuario) {
        super(titulo, contenido, categoria, idUsuario, "interactivo");
        this.likes = 0;
        this.comentarios = new LinkedList<>();
    }

    @JsonCreator
    public ContenidoInteractivo(
            @JsonProperty("idContenido") int idContenido,
            @JsonProperty("idUsuario") int idUsuario,
            @JsonProperty("titulo") String titulo,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("categoria") Categoria categoria,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("idIncremental") int idIncremental,
            @JsonProperty("likes") int likes,
            @JsonProperty("comentarios") List<Integer> comentarios
    ) {
        super(idContenido, idUsuario, titulo, contenido, categoria, estado, "interactivo");
        this.likes = likes;
        this.comentarios = comentarios;
    }

    //Getters y Setters
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Integer> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario){
        this.comentarios.add(comentario.getIdComentario());
    }

    public void eliminarComentario(Comentario comentario){
        this.comentarios.remove((Integer)comentario.getIdComentario());
    }

}
