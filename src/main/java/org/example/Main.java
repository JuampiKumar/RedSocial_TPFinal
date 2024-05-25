package org.example;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.clases.RedSocial;
import org.clases.clasesUsuarios.Usuario;
import org.clases.gestores.GestorRedSocial;
import org.controladorArchivos.ControladorEXCEL;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Usuario> listita = new LinkedList<>();

        Usuario usuario1 = new Usuario("diafir", "2002", "yahveh@gmail.com");
        listita.add(usuario1);
        Usuario usuario2 = new Usuario("JUAN", "1989", "JUAN@gmail.com");
        listita.add(usuario2);
        Usuario usuario3 = new Usuario("PABLO", "1995", "PABLO@gmail.com");
        listita.add(usuario3);
        Usuario usuario4 = new Usuario("KUMAR", "2010", "KUMAR@gmail.com");
        listita.add(usuario4);

        System.out.print(listita.size());

        ControladorEXCEL archivo = new ControladorEXCEL();

        try {
            archivo.mostrarDatosEXCEL(listita);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //RedSocial redSocial = new RedSocial();
        //redSocial.inicio();
    }
}