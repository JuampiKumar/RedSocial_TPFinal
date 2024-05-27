package org.clases.clasesContenido;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Categoria;
import org.clases.clasesContenido.Contenido;
import org.enumeradores.Estado;

public class ContenidoNoInteractivo extends Contenido {
    //Constructor
    public ContenidoNoInteractivo(String titulo, String contenido, Categoria categoria, int idUsuario) {
        super(titulo, contenido, categoria, idUsuario, "no_interactivo");
    }

    @JsonCreator
    public ContenidoNoInteractivo(
            @JsonProperty("idContenido") int idContenido,
            @JsonProperty("idUsuario") int idUsuario,
            @JsonProperty("titulo") String titulo,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("categoria") Categoria categoria,
            @JsonProperty("estado") Estado estado
    ) {
        super(idContenido, idUsuario, titulo, contenido, categoria, estado, "no_interactivo");
    }
}
