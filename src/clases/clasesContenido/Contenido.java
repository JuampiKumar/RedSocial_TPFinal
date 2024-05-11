package clases.clasesContenido;

import enumeradores.Categoria;
import enumeradores.Estado;

import java.util.Objects;

public abstract class Contenido implements Comparable<Contenido>{
    //Atributos
    private int idContenido;
    private String titulo;
    private String contenido;
    private Categoria categoria;
    private Estado estado;

    //Constructor
    public Contenido(String titulo, String contenido, Categoria categoria) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.estado = Estado.ACTIVO;
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
        return Integer.compare(this.idContenido,o.idContenido);
    }
}
