package org.enumeradores;

import java.io.Serializable;
import java.util.Random;

public enum Categoria implements Serializable {
    PELICULAS,
    ACTUALIDAD,
    VIDEOJUEGOS,
    TECNOLOGIA,
    DEPORTE,
    OTROS;

    public static Categoria getRandomCategoria() {
        Random random = new Random();
        Categoria[] categorias = values();
        return categorias[random.nextInt(categorias.length)];
    }
}
