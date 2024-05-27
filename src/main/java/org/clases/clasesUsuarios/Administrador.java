package org.clases.clasesUsuarios;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    public Administrador(
            @JsonProperty("idUsuario") int idUsuario,
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password,
            @JsonProperty("mail") String mail,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("preferencias") List<Categoria> preferencias,
            @JsonProperty("publicados") List<Integer> publicados,
            @JsonProperty("likeados") List<Integer> likeados
    ) {
        super(idUsuario, userName, password, mail, estado, preferencias, publicados, likeados);
    }

}
