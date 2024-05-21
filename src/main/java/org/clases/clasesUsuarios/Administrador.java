package clases.clasesUsuarios;

import interfaces.IAdministrador;

public class Administrador extends Usuario implements IAdministrador {
    //Constructor
    public Administrador(int idUsuario, String userName, String password, String mail) {
        super(userName, password, mail);
    }
    //
}
