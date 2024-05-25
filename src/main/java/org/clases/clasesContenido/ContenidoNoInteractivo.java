package org.clases.clasesContenido;

import org.clases.clasesUsuarios.Usuario;
import org.enumeradores.Categoria;
import org.clases.clasesContenido.Contenido;

public class ContenidoNoInteractivo extends Contenido {
    //Constructor
    public ContenidoNoInteractivo(String titulo, String contenido, Categoria categoria, Usuario usuario) {
        super(titulo, contenido, categoria, usuario);
    }

}
