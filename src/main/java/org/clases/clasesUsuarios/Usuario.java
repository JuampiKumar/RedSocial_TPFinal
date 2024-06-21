package org.clases.clasesUsuarios;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.clases.clasesContenido.Contenido;
import org.enumeradores.Estado;
import org.enumeradores.Categoria;
import org.interfaces.IEstado;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable, IEstado{
    //Atributos
    private final String idUsuario;
    private String userName;
    private String password;
    private String mail;
    private Estado estado;
    private List<Categoria> preferencias;
    private List<String> publicados;
    private List<String> likeados;
    private static int idIncremental = 0;
    private boolean admin;

    //Constructor
    public Usuario(String userName, String password, String mail) {
        this.idUsuario = "U" + idIncremental;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.preferencias = new ArrayList<>();
        this.publicados = new LinkedList<>();
        this.likeados = new LinkedList<>();
        this.estado = Estado.ACTIVO;
        if(idUsuario.equals("U0")){
            this.admin = true;
        }else this.admin = false;
        idIncremental++;
    }

    @JsonCreator
    public Usuario(
            @JsonProperty("idUsuario") String idUsuario,
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password,
            @JsonProperty("mail") String mail,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("preferencias") List<Categoria> preferencias,
            @JsonProperty("publicados") List<String> publicados,
            @JsonProperty("likeados") List<String> likeados,
            @JsonProperty("admin") Boolean admin
            ) {
        this.idUsuario = idUsuario;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.estado = estado;
        this.preferencias = preferencias;
        this.publicados = publicados;
        this.likeados = likeados;
        this.admin = admin;
        idIncremental++;
    }

    //Getter y Setter
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getIdUsuario() {
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

    public Boolean getAdmin(){
        return admin;
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

    public List<String> getPublicados() {
        return publicados;
    }

    public List<String> getLikeados() {
        return likeados;
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
        if(contenido != null && !this.likeados.contains(contenido.getIdContenido())){
            this.likeados.add(contenido.getIdContenido());
        }
    }

    public void eliminarLikeado(Contenido contenido){
        if(contenido != null && this.likeados.contains(contenido.getIdContenido())){
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return idUsuario.equals(usuario.idUsuario) && Objects.equals(userName, usuario.userName);
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

    @Override
    public boolean activar() {
        if (this.getEstado() == Estado.INACTIVO){
            this.setEstado(Estado.ACTIVO);
            return true;
        }
        return false;
    }

    @Override
    public boolean desactivar(){
        if (this.getEstado() == Estado.ACTIVO){
            this.setEstado(Estado.INACTIVO);
            return true;
        }
        return false;
    }


    // ESTO SOLIA SER UNA INTERFAZ

    /*
    @Override
    public boolean esAdministrador() {
        if(this.idUsuario == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean noEsAdministrador() {
        if(this.idUsuario != 0) {
            return true;
        }
        else return false;
    }
    */
}
