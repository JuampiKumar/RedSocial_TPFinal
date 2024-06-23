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
import org.interfaces.IEstado;

import java.util.LinkedList;
import java.util.List;

public class ContenidoInteractivo extends Contenido implements IEstado {
    //Atributos
    private int likes;
    private List<String> comentarios;

    //Constructor
    public ContenidoInteractivo(String titulo, String contenido, Categoria categoria, String idUsuario) {
        super(titulo, contenido, categoria, idUsuario, "interactivo");
        this.likes = 0;
        this.comentarios = new LinkedList<>();
    }

    @JsonCreator
    public ContenidoInteractivo(
            @JsonProperty("idContenido") String idContenido,
            @JsonProperty("idUsuario") String idUsuario,
            @JsonProperty("titulo") String titulo,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("categoria") Categoria categoria,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("likes") int likes,
            @JsonProperty("comentarios") List<String> comentarios
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

    public List<String> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario){
        this.comentarios.add(comentario.getIdComentario());
    }

    public void eliminarComentario(Comentario comentario){
        this.comentarios.remove(comentario.getIdComentario());
    }

    @Override
    public boolean activar() {
        if (this.getEstado() == Estado.INACTIVO){
            this.setEstado(Estado.ACTIVO);
            return true;
        }
        return false;
    }

    @Override
    public boolean desactivar(){
        if (this.getEstado() == Estado.ACTIVO){
            this.setEstado(Estado.INACTIVO);
            return true;
        }
        return false;
    }

    @Override
    public void cambiarEstado() {
        if (this.getEstado() == Estado.ACTIVO) {
            this.desactivar();
        }else{
            this.activar();
        }
    }

    @Override
    public String toString() {
        return "Interactivo" +
                ". ID: " + getIdContenido() +
                ". ID usuario: " + getIdUsuario() +
                ". Categoria: " + getCategoria() +
                ". ID de Comentarios: " + getComentarios() +
                ". " + getTitulo();
    }
}
