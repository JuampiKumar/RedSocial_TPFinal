package clases.clasesContenido;

import clases.clasesUsuarios.Usuario;
import enumeradores.Categoria;

public class ContenidoNoInteractivo extends clases.clasesContenido.Contenido {
    //Constructor
    public ContenidoNoInteractivo(String titulo, String contenido, Categoria categoria, Usuario usuario) {
        super(titulo, contenido, categoria, usuario);
    }

}
