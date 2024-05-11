package clases;

import enumeradores.Estado;

public class Comentario {
    //Atributos
    private int idContenido;
    private int idUsuario;
    private Estado estado;
    private String comentario;

    //Constructor
    public Comentario(int idContenido, int idUsuario, Estado estado, String comentario) {
        this.idContenido = idContenido;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.comentario = comentario;
    }
}
