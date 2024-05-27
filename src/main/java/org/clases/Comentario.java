package org.clases;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Estado;
import org.interfaces.IIdentificable;

import java.io.Serializable;

public class Comentario implements IIdentificable, Serializable {
    //Atributos
    private final int idComentario;
    private final int idContenido;
    private final int idUsuario;
    private Estado estado;
    private String comentario;
    private static int idIncremental = 0;

    //Constructor
    public Comentario(int idContenido, int idUsuario, Estado estado, String comentario) {
        this.idContenido = idContenido;
        this.estado = estado;
        this.comentario = comentario;
        this.idUsuario = idUsuario;
        this.idComentario = idIncremental;
        idIncremental++;
    }
    @JsonCreator
    public Comentario(
            @JsonProperty("idContenido") int idContenido,
            @JsonProperty("idUsuario") int idUsuario,
            @JsonProperty("idComentario") int idComentario,
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
    public int getIdContenido() {
        return idContenido;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getComentario() {
        return comentario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdComentario() {
        return idComentario;
    }

    @JsonIgnore
    @Override
    public int getId() {
        return getIdComentario();
    }
}
