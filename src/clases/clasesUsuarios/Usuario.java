package clases.clasesUsuarios;

import enumeradores.Estado;
import enumeradores.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    //Atributos
    private int idUsuario;
    private String nombre;
    private String userName;
    private String password;
    private String mail;
    private Estado estado;
    private List<Categoria> preferencias;

    private static int id = 0;

    //Constructor
    public Usuario(String nombre, String userName, String password, String mail) {
        this.idUsuario = id;
        this.nombre = nombre;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.preferencias = new ArrayList<>();
        this.estado = Estado.ACTIVO;
        id++;
    }

    //Getter y Setter
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Categoria> getPreferencias() {
        return preferencias;
    }

    //Metodos


    //Override


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return idUsuario == usuario.idUsuario && Objects.equals(userName, usuario.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return "ID: " + idUsuario +
                ", userName: " + userName;
    }
}
