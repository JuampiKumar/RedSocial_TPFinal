package org.controladorArchivos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.clases.Comentario;
import org.clases.clasesContenido.Contenido;
import org.clases.clasesUsuarios.Usuario;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ImpresoraJSON extends Impresora {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    public static final String JSON_EXTENSION = ".json";
    private static final Logger logger = Logger.getLogger(ImpresoraJSON.class.getName());

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    @Override
    public String imprimirUsuario(List<Usuario> usuarios, String path) throws IOException {
        return imprimir(usuarios, path, "usuarios");
    }

    @Override
    public List<Usuario> leerUsuario(String path) throws IOException {
        return leer(path, new TypeReference<List<Usuario>>() {});
    }

    @Override
    public String imprimircContenido(List<Contenido> contenidos, String path) throws IOException {
        return imprimir(contenidos, path, "contenidos");
    }

    @Override
    public List<Contenido> leerContenido(String path) throws IOException {
        return leer(path, new TypeReference<List<Contenido>>() {});
    }

    @Override
    public String imprimirComentario(List<Comentario> comentarios, String path) throws IOException {
        return imprimir(comentarios, path, "comentarios");
    }

    @Override
    public List<Comentario> leerComentario(String path) throws IOException {
        return leer(path, new TypeReference<List<Comentario>>() {});
    }

    private <T> String imprimir(List<T> lista, String path, String tipo) throws IOException {
        Path filePath = Paths.get(path, tipo + JSON_EXTENSION);
        return imprimir(lista, filePath);
    }

    private <T> String imprimir(List<T> lista, Path filePath) throws IOException {
        String name = filePath.toString();
        logger.log(Level.INFO, name);

        // Verificar y crear las carpetas si no existen
        Path directoryPath = filePath.getParent();
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        try (FileWriter writer = new FileWriter(name)) {
            objectMapper.writeValue(writer, lista);
            return name;
        } catch (IOException e) {
            logger.log(Level.SEVERE, String.format("Error al generar el archivo %s", name), e);
            throw e;
        }
    }

    private <T> List<T> leer(String path, TypeReference<List<T>> tipo) throws IOException {
        Path filePath = Paths.get(path);

        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            throw new FileNotFoundException("El archivo no existe o es un directorio: " + path);
        }

        try {
            String json = Files.readString(filePath);
            return objectMapper.readValue(json, tipo);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo: " + path, e);
            throw e;
        }
    }

}