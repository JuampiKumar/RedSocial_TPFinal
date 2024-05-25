package org.controladorArchivos;

import com.github.javafaker.Faker;

import java.io.File;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.logging.Logger;

public abstract class Controlador {

    //ESTA CLASE PERMITE GENERAR MENSAJES QUE INFORMAN SITUACION DE ESTADO DEL PROGRAMA
    public static final Logger logger = Logger.getLogger(Controlador.class.getName());

    public static final String DESKTOP_PATH = STR."\{System.getProperty("user.home")}\{File.separator}Desktop";

    public static final String DEFAULT_FOLDER = "RedSocial_Maven";

    public static final String DEFAULT_PATH = STR."\{DESKTOP_PATH}/\{DEFAULT_FOLDER}/";

    public static final String EXCEL_EXTENSION = ".xlsx";

    public static final String DEFAULT_FILENAME = STR."-\{"TESTEO"}";

    private static final Locale locale = new Locale("es", "ES");

    public static String getPeriodo() {
        return LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, locale);
    }

}
