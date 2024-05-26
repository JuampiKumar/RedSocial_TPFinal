package org.clases.clasesUsuarios;

import com.fasterxml.jackson.annotation.JsonCreator;
import interfaces.IAdministrador;
import org.clases.clasesContenido.Contenido;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Categoria;
import org.enumeradores.Estado;

import java.util.List;

public class Administrador extends Usuario implements IAdministrador {
    //Constructor
    public Administrador(int idUsuario, String userName, String password, String mail) {
        super(userName, password, mail);
    }
    @JsonCreator
    public Administrador(int idUsuario, String userName, String password, String mail, Estado estado, List<Categoria> preferencias, List<Integer> publicados, List<Integer> likeados, int id) {
        super(idUsuario, userName, password, mail, estado, preferencias, publicados, likeados, id);
    }
    //
}
