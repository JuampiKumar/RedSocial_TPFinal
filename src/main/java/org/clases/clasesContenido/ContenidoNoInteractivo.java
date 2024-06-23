package org.clases.clasesContenido;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Categoria;
import org.clases.clasesContenido.Contenido;
import org.enumeradores.Estado;
import org.interfaces.IEstado;

public class ContenidoNoInteractivo extends Contenido implements IEstado {
    //Constructor
    public ContenidoNoInteractivo(String titulo, String contenido, Categoria categoria, String idUsuario) {
        super(titulo, contenido, categoria, idUsuario, "no_interactivo");
    }

    @JsonCreator
    public ContenidoNoInteractivo(
            @JsonProperty("idContenido") String idContenido,
            @JsonProperty("idUsuario") String idUsuario,
            @JsonProperty("titulo") String titulo,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("categoria") Categoria categoria,
            @JsonProperty("estado") Estado estado
    ) {
        super(idContenido, idUsuario, titulo, contenido, categoria, estado, "no_interactivo");
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

    @Override
    public void cambiarEstado() {
        if (this.getEstado() == Estado.ACTIVO) {
            this.desactivar();
        }else{
            this.activar();
        }
    }

    @Override
    public String toString() {
        return "No interactivo" +
                ". ID: " + getIdContenido() +
                ". ID usuario: " + getIdUsuario() +
                ". Categoria: " + getCategoria() +
                ". " + getTitulo();
    }
}
