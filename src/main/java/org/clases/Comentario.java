package org.clases;

import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Estado;
import org.interfaces.IIdentificable;

public class Comentario implements IIdentificable {
    //Atributos
    private final int idComentario;
    private final int idContenido;
    private final int idUsuario;
    private Usuario usuario;
    private Estado estado;
    private String comentario;

    private static int id = 0;

    //Constructor
    public Comentario(int idContenido, Usuario usuario, Estado estado, String comentario) {
        this.idContenido = idContenido;
        this.usuario = usuario;
        this.estado = estado;
        this.comentario = comentario;
        this.idUsuario = usuario.getIdUsuario();
        this.idComentario = id;
        id++;
    }

    public Comentario(int idContenido, int idUsuario, Estado estado, String comentario) {
        this.idContenido = idContenido;
        this.usuario = usuario;
        this.estado = estado;
        this.comentario = comentario;
        this.idUsuario = idUsuario;
        this.idComentario = id;
        id++;
    }

    //Getter and Setter
    public int getIdContenido() {
        return idContenido;
    }

    public Usuario getUsuario() {
        return usuario;
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
