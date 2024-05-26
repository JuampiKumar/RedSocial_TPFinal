package org.clases;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Estado;
import org.interfaces.IIdentificable;

import java.io.Serializable;

public class Comentario implements IIdentificable, Serializable {
    //Atributos
    @JsonProperty
    private final int idComentario;
    @JsonProperty
    private final int idContenido;
    @JsonProperty
    private final int idUsuario;
    @JsonProperty
    private Estado estado;
    @JsonProperty
    private String comentario;
    @JsonProperty
    private static int id = 0;

    //Constructor
    public Comentario(int idContenido, int idUsuario, Estado estado, String comentario) {
        this.idContenido = idContenido;
        this.estado = estado;
        this.comentario = comentario;
        this.idUsuario = idUsuario;
        this.idComentario = id;
        id++;
    }

    @JsonCreator
    public Comentario(
            @JsonProperty("idComentario") int idComentario,
            @JsonProperty("idContenido") int idContenido,
            @JsonProperty("idUsuario") int idUsuario,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("comentario") String comentario,
            @JsonProperty("id") int id
            ) {
        this.idComentario = idComentario;
        this.idContenido = idContenido;
        this.idUsuario = idUsuario;
        this.estado = estado;
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

    @Override
    public int getId() {
        return getIdComentario();
    }
}
