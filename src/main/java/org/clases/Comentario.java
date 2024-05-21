package clases;

import clases.clasesUsuarios.Usuario;
import enumeradores.Estado;

public class Comentario {
    //Atributos
    private int idContenido;
    private Usuario usuario;
    private Estado estado;
    private String comentario;

    //Constructor
    public Comentario(int idContenido, Usuario usuario, Estado estado, String comentario) {
        this.idContenido = idContenido;
        this.usuario = usuario;
        this.estado = estado;
        this.comentario = comentario;
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
}
