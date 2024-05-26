package org.clases.clasesContenido;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.annotations.Expose;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Categoria;
import org.enumeradores.Estado;
import org.interfaces.IIdentificable;

import java.io.Serializable;
import java.util.Objects;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContenidoInteractivo.class, name = "interactivo"),
        @JsonSubTypes.Type(value = ContenidoNoInteractivo.class, name = "no_interactivo")
})
public abstract class Contenido implements Comparable<Contenido>, IIdentificable, Serializable {
    //Atributos
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty
    private final int idContenido;
    @JsonProperty
    private int idUsuario;
    @JsonProperty
    private String titulo;
    @JsonProperty
    private String contenido;
    @JsonProperty
    private Categoria categoria;
    @JsonProperty
    private Estado estado;
    @JsonProperty
    private static int idIncremental = 0;

    //Constructor
    public Contenido(String titulo, String contenido, Categoria categoria, int idUsusario, String tipo) {
        this.idContenido = idIncremental;
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.estado = Estado.ACTIVO;
        this.idUsuario = idUsusario;
        idIncremental++;
        this.tipo = tipo;
    }

    @JsonCreator
    public Contenido(
            @JsonProperty("idContenido") int idContenido,
            @JsonProperty("idUsuario") int idUsuario,
            @JsonProperty("titulo") String titulo,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("categoria") Categoria categoria,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("idIncremental") int idIncremental,
            @JsonProperty("tipo")String tipo
        ){
        this.idContenido = idContenido;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.estado = estado;
        this.tipo = tipo;
    }

    //Getters y Setters

    public int getIdContenido() {
        return idContenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public int getId() {
        return getIdContenido();
    }

    //Overide
    @Override
    public String toString() {
        return "Contenido{" +
                "titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", categoria=" + categoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenido contenido)) return false;
        return idContenido == contenido.idContenido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContenido);
    }

    @Override
    public int compareTo(Contenido o) {
        return Integer.compare(o.idContenido,this.idContenido);
    }
}
