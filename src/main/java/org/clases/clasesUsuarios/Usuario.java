package org.clases.clasesUsuarios;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.clases.clasesContenido.Contenido;
import org.enumeradores.Estado;
import org.enumeradores.Categoria;
import org.interfaces.IIdentificable;

import java.io.Serializable;
import java.util.*;

public class Usuario implements IIdentificable, Serializable {
    //Atributos
    @JsonProperty
    private final int idUsuario;
    @JsonProperty
    private String userName;
    @JsonProperty
    private String password;
    @JsonProperty
    private String mail;
    @JsonProperty
    private Estado estado;
    @JsonProperty
    private List<Categoria> preferencias;
    @JsonProperty
    private List<Integer> publicados;
    @JsonProperty
    private List<Integer> likeados;
    @JsonProperty
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
        this.estado = Estado.ACTIVO;
        id++;
    }

    @JsonCreator
    public Usuario(
            @JsonProperty("idUsuario") int idUsuario,
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password,
            @JsonProperty("mail") String mail,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("preferencias") List<Categoria> preferencias,
            @JsonProperty("publicados") List<Integer> publicados,
            @JsonProperty("likeados") List<Integer> likeados,
            @JsonProperty("id") int id
            ) {
        this.idUsuario = idUsuario;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.estado = estado;
        this.preferencias = preferencias;
        this.publicados = publicados;
        this.likeados = likeados;
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

    public List<Integer> getPublicados() {
        return publicados;
    }

    public List<Integer> getLikeados() {
        return likeados;
    }

    @Override
    public int getId() {
        return getIdUsuario();
    }

    //Metodos
    public boolean buscarEnLikeado(Contenido contenido){
        boolean flag = false;
        if(this.likeados.contains(contenido.getIdContenido())){
            flag = true;
        }
        return flag;
    }

    public void agregarLikeado(Contenido contenido){
        if(contenido != null){
            this.likeados.add(contenido.getIdContenido());
        }
    }

    public void eliminarLikeado(Contenido contenido){
        if(contenido != null){
            this.likeados.remove(contenido.getIdContenido());
        }
    }

    public void agregarPublicado(Contenido contenido){
        if(contenido != null){
        this.publicados.add(contenido.getIdContenido());
        }
    }

    public void eliminarPublicado(Contenido contenido){
            if(contenido != null){
        this.publicados.remove(contenido.getIdContenido());
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
