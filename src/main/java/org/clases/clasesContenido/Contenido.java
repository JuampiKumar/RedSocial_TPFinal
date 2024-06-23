package org.clases.clasesContenido;

import com.fasterxml.jackson.annotation.*;
import org.enumeradores.Categoria;
import org.enumeradores.Estado;
import org.interfaces.IEstado;

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
public abstract class Contenido implements Comparable<Contenido>, Serializable, IEstado{
    //Atributos
    @JsonProperty("tipo")
    private String tipo;
    private final String idContenido;
    private String idUsuario;
    private String titulo;
    private String contenido;
    private Categoria categoria;
    private Estado estado;
    private static int idIncremental = 0;

    //Constructor
    public Contenido(String titulo, String contenido, Categoria categoria, String idUsusario, String tipo) {
        this.idContenido =  "C" + idIncremental;
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
            @JsonProperty("idContenido") String idContenido,
            @JsonProperty("idUsuario") String idUsuario,
            @JsonProperty("titulo") String titulo,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("categoria") Categoria categoria,
            @JsonProperty("estado") Estado estado,
            @JsonProperty("tipo")String tipo
        ){
        this.idContenido = idContenido;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.estado = estado;
        this.tipo = tipo;
        idIncremental++;
    }

    //Getters y Setters

    public String getIdContenido() {
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getTipo() {
        return tipo;
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
        return idContenido.equals(contenido.idContenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContenido);
    }

    @Override
    public int compareTo(Contenido o) {
        Integer numero = Integer.parseInt(this.idContenido.substring(1));
        Integer numero2 = Integer.parseInt(o.idContenido.substring(1));
        return numero2.compareTo(numero);
    }

}
