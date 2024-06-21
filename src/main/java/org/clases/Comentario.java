package org.clases;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.enumeradores.Estado;
import org.interfaces.IEstado;

import java.io.Serializable;

public class Comentario implements Serializable, IEstado {
    //Atributos
    private final String idComentario;
    private final String idContenido;
    private final String idUsuario;
    private Estado estado;
    private String comentario;
    private static int idIncremental = 0;

    //Constructor
    public Comentario(String idContenido, String idUsuario, Estado estado, String comentario) {
        this.idContenido = idContenido;
        this.estado = estado;
        this.comentario = comentario;
        this.idUsuario = idUsuario;
        this.idComentario = "M" + idIncremental;
        idIncremental++;
    }
    @JsonCreator
    public Comentario(
            @JsonProperty("idContenido") String idContenido,
            @JsonProperty("idUsuario") String idUsuario,
            @JsonProperty("idComentario") String idComentario,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("comentario") String comentario
    ) {
        this.idComentario = idComentario;
        this.idContenido = idContenido;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.comentario = comentario;
        idIncremental++;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    //Getter and Setter
    public String getIdContenido() {
        return idContenido;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getComentario() {
        return comentario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdComentario() {
        return idComentario;
    }


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
    public String toString() {
        return "Comentario" +
                ". ID: " + getIdComentario() +
                ". ID usuario: " + getIdUsuario() +
                ". ID Contenido: " + getIdContenido() +
                ". " + getComentario();
    }
}
