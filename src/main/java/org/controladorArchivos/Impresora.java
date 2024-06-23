package org.controladorArchivos;

import org.clases.Comentario;
import org.clases.clasesContenido.Contenido;
import org.clases.clasesUsuarios.Usuario;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public abstract class Impresora {
    public static final Logger logger = Logger.getLogger(Impresora.class.getName());
    //public static final String DEFAULT_PATH = "C:/Users/yahve/Downloads/RedSocial_TPFinal/RedSocial_TPFinal";
    //public static final String DEFAULT_PATH = Paths.get(System.getProperty("user.home"), "Desktop", "RedSocial_TPFinal").toString();
    //public static final String DEFAULT_PATH = "C:/Users/juanp/Desktop/VERSION NUEVA/RedSocial_TPFinal/RedSocial_TPFinal";
    public static final String DEFAULT_PATH = "C:/Users/yahve/OneDrive/Desktop/RedSocial_TPFinal";
    public abstract String imprimirUsuario(List<Usuario> usuarios, String path) throws IOException;

    public abstract List<Usuario> leerUsuario(String path) throws IOException;

    public abstract String imprimircContenido(List<Contenido> contenidos, String path) throws IOException;

    public abstract List<Contenido> leerContenido(String path) throws IOException;

    public abstract String imprimirComentario(List<Comentario> comentarios, String path) throws IOException;

    public abstract List<Comentario> leerComentario(String path) throws IOException;
}