package org.clases.clasesUsuarios;

import org.clases.clasesContenido.Contenido;
import org.enumeradores.Estado;
import org.enumeradores.Categoria;
import org.interfaces.IIdentificable;

import java.util.*;

public class Usuario implements IIdentificable {
    //Atributos
    private final int idUsuario;
    private String userName;
    private String password;
    private String mail;
    private Estado estado;
    HashMap<Integer,Contenido> contenidosPublicados;
    HashMap<Integer,Contenido> contenidosLikeados;
    private List<Categoria> preferencias;
    private List<Contenido> publicados;
    private List<Contenido> likeados;

    private static int id = 0;

    //Constructor
    public Usuario(String userName, String password, String mail) {
        this.idUsuario = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.preferencias = new ArrayList<>();
        this.publicados = new LinkedList<>();
        this.likeados = new LinkedList<>();
        this.contenidosPublicados = new HashMap<>();
        this.contenidosLikeados = new HashMap<>();
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

    public List<Contenido> getPublicados() {
        return publicados;
    }

    public List<Contenido> getLikeados() {
        return likeados;
    }

    @Override
    public int getId() {
        return getIdUsuario();
    }

    //Metodos
    public boolean buscarEnLikeado(Contenido contenido){
        boolean flag = false;
        if(this.likeados.contains(contenido)){
            flag = true;
        }
        return flag;
    }

    public void agregarLikeado(Contenido contenido){
        if(contenido != null){
            this.likeados.add(contenido);
        }
    }

    public void eliminarLikeado(Contenido contenido){
        if(contenido != null){
            this.likeados.remove(contenido);
        }
    }

    public void agregarPublicado(Contenido contenido){
        if(contenido != null){
        this.publicados.add(contenido);
        }
    }

    public void eliminarPublicado(Contenido contenido){
            if(contenido != null){
        this.publicados.remove(contenido);
    }
}


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
