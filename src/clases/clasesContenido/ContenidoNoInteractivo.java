package clases.clasesContenido;

import clases.clasesUsuarios.Usuario;
import enumeradores.Categoria;

public class ContenidoNoInteractivo extends Contenido{
    //Constructor
    public ContenidoNoInteractivo(String titulo, String contenido, Categoria categoria, Usuario usuario) {
        super(titulo, contenido, categoria, usuario);
    }

}
