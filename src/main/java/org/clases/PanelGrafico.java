package org.clases;

import org.clases.clasesContenido.Contenido;
import org.clases.clasesContenido.ContenidoInteractivo;
import org.clases.clasesContenido.ContenidoNoInteractivo;
import org.clases.clasesUsuarios.Usuario;
import org.clases.gestores.GestorRedSocial;
import org.enumeradores.Categoria;
import org.enumeradores.Estado;
import org.enumeradores.TipoContenido;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


public class PanelGrafico extends JFrame {
    private Contenido contenido;
    private Usuario usuario;
    private GestorRedSocial gestor;

    private final int SCREEN_WIDTH = 1000;
    private final int SCREEN_HEIGHT = 800;
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 30;
    private final int FIELD_WIDTH = 200;
    private final int FIELD_HEIGHT = 30;
    private final int AREA_WIDTH = 600;
    private final int AREA_HEIGHT = 80;
    private final int LIMITE_UNO = 25;
    private final int LIMITE_DOS = 15;
    private final int LIMITE_TRES = 30;
    private final int LIMITE_CONTENIDO = 350;
    private final int INSET_FINAL = 10;

    // Configurar la ventana principal
    public PanelGrafico(GestorRedSocial gestor) {
        setTitle("RED SOCIAL");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.gestor = gestor;
        this.contenido = null;
        this.usuario = null;
    }

    public void menuPrincipal() { //Menu principal
        //Creamos y seteamos la configuracion del panel de este menu
        JPanel panelMenuPrincipal = new JPanel();
        panelMenuPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.anchor = GridBagConstraints.CENTER;

        //Creamos los botones del menu principal
        JButton registrarseButton = new JButton("REGISTRARSE");
        JButton ingresarButton = new JButton("INGRESAR");
        JButton salirButton = new JButton("SALIR");

        //Dimensiones de los botones
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        registrarseButton.setPreferredSize(buttonSize);
        ingresarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        //Agregamos al panel los elementos
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelMenuPrincipal.add(registrarseButton, gbc);

        gbc.gridy = 1;
        panelMenuPrincipal.add(ingresarButton, gbc);

        gbc.gridy = 2;
        panelMenuPrincipal.add(salirButton, gbc);

        // Definir ActionListener para cada botón
        registrarseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuRegistro();
            }
        });

        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuIngreso();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor.guardarDatosEnJSON();
                dispose();
            }
        });

        // Mostrar el panel del menú principal en la ventana
        setContentPane(panelMenuPrincipal);
        setVisible(true);
    }

    public void menuRegistro() { //Menu regustro de usuario
        //Creamos y seteamos la configuracion del panel de este menu
        JPanel panelMenuRegistro = new JPanel();
        panelMenuRegistro.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.anchor = GridBagConstraints.CENTER;

        // Crear elementos para el menú de registro
        JLabel usernameLabel = new JLabel("NOMBRE DE USUARIO:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        JPasswordField passwordField = new JPasswordField();
        JLabel correoLabel = new JLabel("CORREO:");
        JTextField correoField = new JTextField();
        JButton registrarButton = new JButton("REGISTRAR");
        JButton salirButton = new JButton("SALIR");

        //Establecemos las dimensiones de los elementos
        Dimension fieldSize = new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
        usernameField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);
        correoField.setPreferredSize(fieldSize);

        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        registrarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        setCharacterLimit(usernameField, LIMITE_DOS);
        setCharacterLimit(passwordField, LIMITE_DOS);
        setCharacterLimit(correoField, LIMITE_TRES);

        //Agregamos al panel del menuRegistro los elementos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuRegistro.add(usernameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuRegistro.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuRegistro.add(passwordLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuRegistro.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuRegistro.add(correoLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuRegistro.add(correoField, gbc);

        //Espacio entre elmeentos
        gbc.insets = new Insets(150, INSET_FINAL, INSET_FINAL, INSET_FINAL);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuRegistro.add(registrarButton, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuRegistro.add(salirButton, gbc);

        // Definir ActionListener para el botón de registrar
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto y almacenarlos en variables
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // La contraseña se obtiene de manera diferente debido al uso de JPasswordField
                String correo = correoField.getText();
                gestor.registrarUsuarioNuevo(username,password, correo);
                menuPrincipal();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPrincipal();
            }
        });

        // Mostrar el panel del menú de registro en la ventana
        setContentPane(panelMenuRegistro);
        setVisible(true);
    }

    public void menuIngreso() { //Menu de Ingreso de usuario
        //Creamos y seteamos la configuracion del panel del menuIngresp
        JPanel panelMenuIngreso = new JPanel();
        panelMenuIngreso.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.anchor = GridBagConstraints.CENTER;

        // Crear elementos para el menú de ingreso
        JLabel usernameLabel = new JLabel("NOMBRE DE USUARIO:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        JPasswordField passwordField = new JPasswordField();
        JButton ingresarButton = new JButton("INGRESAR");
        JButton salirButton = new JButton("SALIR");

        //Definimos las dimensiones de lo elementos del panel
        Dimension fieldSize = new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
        usernameField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);

        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        ingresarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        setCharacterLimit(usernameField, LIMITE_DOS);
        setCharacterLimit(passwordField, LIMITE_DOS);

        //Agregamos al panel los elementos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuIngreso.add(usernameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuIngreso.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuIngreso.add(passwordLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuIngreso.add(passwordField, gbc);

        //Espacio entre elementos
        gbc.insets = new Insets(150, INSET_FINAL, INSET_FINAL, INSET_FINAL);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuIngreso.add(ingresarButton, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuIngreso.add(salirButton, gbc);

        // Definir ActionListener para el botón del menuIngreso
        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto y almacenarlos en variables
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // La contraseña se obtiene de manera diferente debido al uso de JPasswordField

                if(gestor.encontrarUsuario(username, password) != null){
                    usuario = gestor.encontrarUsuario(username, password);
                    menuPerfil();
                }else{
                    menuIngreso();
                }


            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPrincipal();
            }
        });

        // Mostrar el panel del menú de registro en la ventana
        setContentPane(panelMenuIngreso);
        setVisible(true);
    }

    public void menuPerfil(){ //Menu perfil del usuario ingresado
        //Se crea y setea la configuracion del panel del menuPerfil
        JPanel panelMenuPerfil = new JPanel();
        panelMenuPerfil.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.anchor = GridBagConstraints.CENTER;

        // Crear botones para las opciones del menú de perfil
        JButton publicarButton = new JButton("PUBLICAR");
        JButton verPublicacionesButton = new JButton("VER PUBLICACIONES");
        JButton miInfoButton = new JButton("MI INFORMACIÓN");
        JButton buscarUsuarioButton = new JButton("USUARIOS");
        JButton buscarContenidoButton = new JButton("CONTENIDOS");
        JButton buscarComentarioButton = new JButton("COMENTARIOS");
        JButton salirButton = new JButton("SALIR");

        //Definimos las dimensiones de los botones del panel
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        publicarButton.setPreferredSize(buttonSize);
        verPublicacionesButton.setPreferredSize(buttonSize);
        miInfoButton.setPreferredSize(buttonSize);
        buscarUsuarioButton.setPreferredSize(buttonSize);
        buscarContenidoButton.setPreferredSize(buttonSize);
        buscarComentarioButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        //Agregamos al panel los elementos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuPerfil.add(publicarButton, gbc);

        gbc.gridy = 1;
        panelMenuPerfil.add(verPublicacionesButton, gbc);

        gbc.gridy = 2;
        panelMenuPerfil.add(miInfoButton, gbc);

        gbc.gridy = 3;
        panelMenuPerfil.add(buscarUsuarioButton, gbc);

        gbc.gridy = 4;
        panelMenuPerfil.add(buscarContenidoButton, gbc);

        gbc.gridy = 5;
        panelMenuPerfil.add(buscarComentarioButton, gbc);

        gbc.gridy = 6;
        panelMenuPerfil.add(salirButton, gbc);

        if(!usuario.getAdmin()){
            buscarUsuarioButton.setVisible(false);
            buscarContenidoButton.setVisible(false);
        }

        // Definir ActionListener para cada botón
        publicarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPublicar();
            }
        });

        verPublicacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuVerContenido();
            }
        });

        miInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuVerMiInfo();
            }
        });


        buscarUsuarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuUsuarios();
            }
        });

        buscarContenidoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuContenidos();
            }
        });

        buscarComentarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {menuComentarios();}

        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPrincipal();
            }
        });

        // Mostrar el panel del menú de perfil en la ventana
        setContentPane(panelMenuPerfil);
        setVisible(true);
    }

    public void menuUsuarios() {
        // Crear y configurar el panel del menú principal
        JPanel panelMenuUsuarios = new JPanel(new BorderLayout());

        // Crear los botones del menú principal
        JButton buscarButton = new JButton("BUSCAR USUARIO");
        JButton salirButton = new JButton("SALIR");

        // Dimensiones de los botones
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        buscarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        // Crear el panel que contendrá los JLabels de los usuarios
        JPanel usuariosPanel = new JPanel();
        usuariosPanel.setLayout(new BoxLayout(usuariosPanel, BoxLayout.Y_AXIS));

        Runnable actualizarUsuarios = () -> {
            SwingUtilities.invokeLater(() -> {
                usuariosPanel.removeAll();

        Iterator<Usuario> iterador = gestor.obtenerIteradorUsuarios();
        while (iterador.hasNext()) {
            Usuario user = iterador.next();

            //Se crea un panel para almacenar individualmente los comentarios
            JPanel panelUsuarioIndividual = new JPanel(new BorderLayout());
            panelUsuarioIndividual.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            //Se crean los elementos del panel individual
            JLabel usuarioLabel = new JLabel(user.toString() + " Estado: " + user.getEstado());
            usuarioLabel.setHorizontalAlignment(SwingConstants.LEFT);

            // Añadir el JLabel al panel individual
            panelUsuarioIndividual.add(usuarioLabel, BorderLayout.WEST);

            // Añadir el panel individual al panel de usuarios
            usuariosPanel.add(panelUsuarioIndividual);

            // Añadir un separador después de cada usuario
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setPreferredSize(new Dimension(usuariosPanel.getWidth(), 10));
            usuariosPanel.add(separator);
        };
                usuariosPanel.revalidate();
                usuariosPanel.repaint();
            });
        };

        SwingUtilities.invokeLater(() -> {
            actualizarUsuarios.run();
        });

        // Crear un JScrollPane para el panel de usuarios
        JScrollPane scrollPane = new JScrollPane(usuariosPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajustar la velocidad de desplazamiento

        // Añadir el JScrollPane al panel principal
        panelMenuUsuarios.add(scrollPane, BorderLayout.CENTER);

        // Crear un panel para los botones y añadir los botones a este panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(buscarButton);
        buttonsPanel.add(salirButton);

        // Añadir el panel de botones al panel principal
        panelMenuUsuarios.add(buttonsPanel, BorderLayout.SOUTH);

        // Definir ActionListener para cada botón
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario user = gestor.buscarUsuarioPorId(JOptionPane.showInputDialog(null, "INGRESAR ID O NOMBRE DE USUARIO:"));
                String[] options = {"ACEPTAR", "DESACTIVAR"};
                if(user != null) {
                    if (user.getEstado() == Estado.INACTIVO) {
                        options[1] = "ACTIVAR";
                    }

                    int option = JOptionPane.showOptionDialog(null, user.toString() + "\nESTADO: " + user.getEstado() + "\nContenido publicado: " + user.getPublicados(), "Opciones de Usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    if (option == 1) {
                        if (user.getEstado() == Estado.ACTIVO) {
                            user.desactivar();
                        } else user.activar();
                        JOptionPane.showMessageDialog(null, "Usuario " + user.getEstado());
                        SwingUtilities.invokeLater(() -> {
                            actualizarUsuarios.run();
                        });
                    }
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil();
            }
        });

        // Mostrar el panel del menú usuario en la ventana
        setContentPane(panelMenuUsuarios);
        setVisible(true);
    }

    public void menuContenidos() {
        // Crear y configurar el panel del menú principal
        JPanel panelMenuContenidos = new JPanel(new BorderLayout());

        // Crear los botones del menú principal
        JButton buscarButton = new JButton("BUSCAR CONTENIDO");
        JButton salirButton = new JButton("SALIR");

        // Dimensiones de los botones
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        buscarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        // Crear el panel que contendrá los JLabels de los usuarios
        JPanel contenidosPanel = new JPanel();
        contenidosPanel.setLayout(new BoxLayout(contenidosPanel, BoxLayout.Y_AXIS));

        Runnable actualizarContenidos = () -> {
            SwingUtilities.invokeLater(() -> {
            contenidosPanel.removeAll();

            Iterator<Contenido> iterador = gestor.obtenerIteradorContenido();
            while (iterador.hasNext()) {
                Contenido cont = iterador.next();

                //Se crea un panel para almacenar individualmente los comentarios
                JPanel panelContenidoIndividual = new JPanel(new BorderLayout());
                panelContenidoIndividual.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                String contenidoTruncado = cont.getContenido().length() > 20 ? cont.getContenido().substring(0, 20) + "..." : cont.getContenido();
                //Se crean los elementos del panel individual
                JLabel usuarioLabel = new JLabel(cont.toString() + ". " + contenidoTruncado + " Estado: " + cont.getEstado());
                usuarioLabel.setHorizontalAlignment(SwingConstants.LEFT);

                // Añadir el JLabel al panel individual
                panelContenidoIndividual.add(usuarioLabel, BorderLayout.WEST);

                // Añadir el panel individual al panel de usuarios
                contenidosPanel.add(panelContenidoIndividual);

                // Añadir un separador después de cada usuario
                JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
                separator.setPreferredSize(new Dimension(contenidosPanel.getWidth(), 10));
                contenidosPanel.add(separator);
            };
                contenidosPanel.revalidate();
                contenidosPanel.repaint();
        });
        };

        SwingUtilities.invokeLater(() -> {
            actualizarContenidos.run();
        });

        // Crear un JScrollPane para el panel de usuarios
        JScrollPane scrollPane = new JScrollPane(contenidosPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajustar la velocidad de desplazamiento

        // Añadir el JScrollPane al panel principal
        panelMenuContenidos.add(scrollPane, BorderLayout.CENTER);

        // Crear un panel para los botones y añadir los botones a este panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(buscarButton);
        buttonsPanel.add(salirButton);

        // Añadir el panel de botones al panel principal
        panelMenuContenidos.add(buttonsPanel, BorderLayout.SOUTH);

        // Definir ActionListener para cada botón
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Contenido contenidoBuscado = gestor.buscarContenidoPorId(JOptionPane.showInputDialog(null, "INGRESAR ID DE CONTENIDO:"));
                String[] options = {"ACEPTAR", "DESACTIVAR"};
                if (contenidoBuscado != null) {
                    if (contenidoBuscado.getEstado() == Estado.INACTIVO) {
                        options[1] = "ACTIVAR";
                    }

                    JTextArea textArea = new JTextArea(contenidoBuscado.toString() + "\n" + contenidoBuscado.getContenido() + "\nESTADO: " + contenidoBuscado.getEstado());
                    textArea.setEditable(false);
                    textArea.setLineWrap(true);  // Permite el salto de línea automático
                    textArea.setWrapStyleWord(true);  // Rompe palabras largas en saltos de línea
                    textArea.setFont(UIManager.getFont("Label.font"));  // Utiliza la fuente del JLabel
                    textArea.setBackground(UIManager.getColor("OptionPane.background"));  // Establece el fondo para que coincida con el del JOptionPane
                    textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));


                    int option = JOptionPane.showOptionDialog(null, scrollPane, "Opciones de Contenido", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    if (option == 1) {
                        if (contenidoBuscado.getEstado() == Estado.ACTIVO) {
                            contenidoBuscado.desactivar();
                        } else contenidoBuscado.activar();
                        JOptionPane.showMessageDialog(null, "Cotenido " + contenidoBuscado.getEstado());
                        SwingUtilities.invokeLater(() -> {
                            actualizarContenidos.run();
                        });
                    }
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil();
            }
        });

        // Mostrar el panel del menú usuario en la ventana
        setContentPane(panelMenuContenidos);
        setVisible(true);
    }

    public void menuComentarios() {
        // Crear y configurar el panel del menú principal
        JPanel panelMenuComentarios = new JPanel(new BorderLayout());

        // Crear los botones del menú principal
        JButton buscarButton = new JButton("BUSCAR COMENTARIOS");
        JButton salirButton = new JButton("SALIR");

        // Dimensiones de los botones
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        buscarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        // Crear el panel que contendrá los JLabels de los usuarios
        JPanel comentariosPanel = new JPanel();
        comentariosPanel.setLayout(new BoxLayout(comentariosPanel, BoxLayout.Y_AXIS));

        Runnable actualizarComentarios = () -> {
            SwingUtilities.invokeLater(() -> {
                comentariosPanel.removeAll();

                Iterator<Comentario> iterador = gestor.obtenerIteradorComentario();
                while (iterador.hasNext()) {
                    Comentario coment = iterador.next();

                    //Se crea un panel para almacenar individualmente los comentarios
                    JPanel panelContenidoIndividual = new JPanel(new BorderLayout());
                    panelContenidoIndividual.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    String contenidoTruncado = coment.getComentario().length() > 20 ? coment.getComentario().substring(0, 20) + "..." : coment.getComentario();
                    //Se crean los elementos del panel individual
                    JLabel usuarioLabel = new JLabel(coment.toString() + ". " + contenidoTruncado + " Estado: " + coment.getEstado());
                    usuarioLabel.setHorizontalAlignment(SwingConstants.LEFT);

                    // Añadir el JLabel al panel individual
                    panelContenidoIndividual.add(usuarioLabel, BorderLayout.WEST);

                    // Añadir el panel individual al panel de usuarios
                    comentariosPanel.add(panelContenidoIndividual);

                    // Añadir un separador después de cada usuario
                    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
                    separator.setPreferredSize(new Dimension(comentariosPanel.getWidth(), 10));
                    comentariosPanel.add(separator);
                };
                comentariosPanel.revalidate();
                comentariosPanel.repaint();
            });
        };

        SwingUtilities.invokeLater(() -> {
            actualizarComentarios.run();
        });

        // Crear un JScrollPane para el panel de usuarios
        JScrollPane scrollPane = new JScrollPane(comentariosPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajustar la velocidad de desplazamiento

        // Añadir el JScrollPane al panel principal
        panelMenuComentarios.add(scrollPane, BorderLayout.CENTER);

        // Crear un panel para los botones y añadir los botones a este panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, INSET_FINAL, INSET_FINAL));
        buttonsPanel.add(buscarButton);
        buttonsPanel.add(salirButton);

        // Añadir el panel de botones al panel principal
        panelMenuComentarios.add(buttonsPanel, BorderLayout.SOUTH);

        // Definir ActionListener para cada botón
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Comentario comentarioBuscado = gestor.buscarComentarioPorId(JOptionPane.showInputDialog(null, "INGRESAR ID DE COMENTARIO:"));
                String[] options = {"ACEPTAR", "DESACTIVAR"};
                if(comentarioBuscado != null) {
                    if (comentarioBuscado.getEstado() == Estado.INACTIVO) {
                        options[1] = "ACTIVAR";
                    }

                    JTextArea textArea = new JTextArea(comentarioBuscado.toString() + "\n" + comentarioBuscado.getComentario() + "\nESTADO: " + comentarioBuscado.getEstado());
                    textArea.setEditable(false);
                    textArea.setLineWrap(true);  // Permite el salto de línea automático
                    textArea.setWrapStyleWord(true);  // Rompe palabras largas en saltos de línea
                    textArea.setFont(UIManager.getFont("Label.font"));  // Utiliza la fuente del JLabel
                    textArea.setBackground(UIManager.getColor("OptionPane.background"));  // Establece el fondo para que coincida con el del JOptionPane
                    textArea.setBorder(BorderFactory.createEmptyBorder(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL));

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));


                    int option = JOptionPane.showOptionDialog(null, scrollPane, "Opciones de Comentario", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    if (option == 1) {
                        if (comentarioBuscado.getEstado() == Estado.ACTIVO) {
                            comentarioBuscado.desactivar();
                        } else comentarioBuscado.activar();
                        JOptionPane.showMessageDialog(null, "Comentario " + comentarioBuscado.getEstado());
                        SwingUtilities.invokeLater(() -> {
                            actualizarComentarios.run();
                        });
                    }
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil();
            }
        });

        // Mostrar el panel del menú usuario en la ventana
        setContentPane(panelMenuComentarios);
        setVisible(true);
    }



    public void menuPublicar() { //Menu publicar
        //Creamos y seteamos la configuracion del panel del menuPublicar
        JPanel panelMenuPublicar = new JPanel();
        panelMenuPublicar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.anchor = GridBagConstraints.WEST;

        //Creamos los elementos del panel
        JLabel tipoLabel = new JLabel("INTERACCIONES:");
        JLabel tituloLabel = new JLabel("TÍTULO:");
        JLabel contenidoLabel = new JLabel("CONTENIDO:");
        JLabel categoriaLabel = new JLabel("CATEGORIA:");

        JRadioButton comunicadoRadioButton = new JRadioButton("NO");
        JRadioButton publicacionRadioButton = new JRadioButton("SI");
        ButtonGroup tipoGroup = new ButtonGroup(); //Creamos un grupo para trabajar en conjunto los radioButton
        tipoGroup.add(comunicadoRadioButton);
        tipoGroup.add(publicacionRadioButton);

        JTextField tituloField = new JTextField();
        JTextArea contenidoArea = new JTextArea();

        Categoria[] categorias = {Categoria.ACTUALIDAD, Categoria.DEPORTE, Categoria.PELICULAS, Categoria.TECNOLOGIA, Categoria.VIDEOJUEGOS, Categoria.OTROS};
        JComboBox<Categoria> categoriaComboBox = new JComboBox<>(categorias);

        JButton publicarButton = new JButton("PUBLICAR");
        JButton salirButton = new JButton("SALIR");

        contenidoArea.setLineWrap(true);
        contenidoArea.setWrapStyleWord(true);

        //Definimos las dimensiones de los elementos
        Dimension fieldSize = new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
        tituloField.setPreferredSize(fieldSize);
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        publicarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);
        contenidoArea.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
        setCharacterLimit(tituloField, LIMITE_UNO);
        setCharacterLimit(contenidoArea, LIMITE_CONTENIDO);
        //Agregamos al panel los elementos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuPublicar.add(tipoLabel, gbc);
        gbc.gridx = 1;
        panelMenuPublicar.add(comunicadoRadioButton, gbc);
        gbc.gridx = 2;
        panelMenuPublicar.add(publicacionRadioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuPublicar.add(tituloLabel, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelMenuPublicar.add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelMenuPublicar.add(contenidoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelMenuPublicar.add(contenidoArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panelMenuPublicar.add(categoriaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelMenuPublicar.add(categoriaComboBox, gbc);
        gbc.gridwidth = 2;

        JPanel panelMenuPublicarBotones = new JPanel();
        panelMenuPublicarBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbcBotones.anchor = GridBagConstraints.CENTER;

        gbcBotones.gridx = 0;
        panelMenuPublicarBotones.add(publicarButton, gbcBotones);
        gbcBotones.gridx = 3;
        panelMenuPublicarBotones.add(salirButton, gbcBotones);

        gbc.insets = new Insets(150, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuPublicar.add(panelMenuPublicarBotones, gbc);


        // Definir ActionListener para el botón de publicar
        publicarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TipoContenido tipo = comunicadoRadioButton.isSelected() ? TipoContenido.COMUNICADO : TipoContenido.PUBLICACION;
                String titulo = tituloField.getText();
                String contenido = contenidoArea.getText();
                Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();
                Contenido publicacion = null;
                if(!titulo.isEmpty() && !contenido.isEmpty()){
                    if (tipo == TipoContenido.PUBLICACION) { //Se va a crear un contenido u otro dependiendo la opcion elegida en los radioButton
                        publicacion = new ContenidoInteractivo(titulo, contenido, categoria, usuario.getIdUsuario());
                    } else {
                        publicacion = new ContenidoNoInteractivo(titulo, contenido, categoria, usuario.getIdUsuario());
                    }
                    gestor.agregarContenido(publicacion);
                    gestor.agregarPublicacionAUsuario(usuario, publicacion); //Se llaman metodos del gestor
                    menuPerfil();
                }else{
                    JOptionPane.showMessageDialog(null, "COMPLETAR EL CAMPO TITULO Y CONTENIDO");
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil();
            }
        });

        setContentPane(panelMenuPublicar);
        setVisible(true);
    }

    public void menuVerContenido() { //Menu para ver contenido
        //Se crea y setea la configuracion del panel principal del menuVerContenido
        JPanel panelVerContenido = new JPanel();
        panelVerContenido.setLayout(new BoxLayout(panelVerContenido, BoxLayout.Y_AXIS));
        //Se trae un iterador de la coleccion de contenidos del gestor
        Iterator<Contenido> iterator = gestor.obtenerIteradorContenido(usuario);
        while(iterator.hasNext()){
        //while(iterator.hasNext()){
            Contenido contenidoActual = iterator.next();
            if(contenidoActual.getEstado() == Estado.ACTIVO) {
                //Se agrega la verificacion de estado activo

                //Panel de comentarios que va a tener la opcion de crear comentario y un panel interno con cada comentario
                JPanel panelComentarios = new JPanel();
                panelComentarios.setLayout(new GridBagLayout());
                GridBagConstraints gbcComentarios = new GridBagConstraints();
                gbcComentarios.insets = new Insets(5, 5,  5,5);
                gbcComentarios.anchor = GridBagConstraints.WEST;
                panelComentarios.setVisible(false);
                JButton comentarioComentar = new JButton("COMENTAR");

                //Panel donde se vera el contenido, que a su vez tendra el panel de comentarios (panelComentarios)
                JPanel panelContenido = new JPanel();
                panelContenido.setLayout(new GridBagLayout());
                GridBagConstraints gbcCont = new GridBagConstraints();
                gbcCont.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
                gbcCont.anchor = GridBagConstraints.CENTER;

                //Se crean los elementos del panelContenido
                JLabel nombreLabel = new JLabel(gestor.buscarUsuarioPorId(contenidoActual.getIdUsuario()).getUserName());
                JLabel tituloLabel = new JLabel(contenidoActual.getTitulo());
                JLabel estadoLabel = new JLabel(String.valueOf((contenidoActual.getEstado())));

                JLabel likeLabel = new JLabel("LIKES: " + gestor.retornarCantidadLikes(contenidoActual));

                JTextArea contenidoArea = new JTextArea();
                contenidoArea.setLineWrap(true);  // Habilita el salto de línea automático
                contenidoArea.setWrapStyleWord(true);  // Hace que el salto de línea ocurra en los límites de palabras
                contenidoArea.setEditable(false);

                contenidoArea.setText(contenidoActual.getContenido());

                JButton comentariosButton = new JButton("COMENTARIOS");
                JButton likeButton = new JButton(":)");
                //Creacion del boton de DAR DE BAJA (tiene que ser solo visible para Administrador)
                JButton activoButton = new JButton("ACTIVAR - DESACTIVAR");


                //Se crea un Runnable para poder actualizar los comentarios cuando se escriba uno nuevo
                Runnable actualizarComentarios = () -> {

                    panelComentarios.removeAll();

                    gbcComentarios.gridx = 0;
                    gbcComentarios.gridy = 0;

                    panelComentarios.add(comentarioComentar, gbcComentarios);

                    for (Comentario comentario : gestor.devolverListaDeComentarioDeContenido((ContenidoInteractivo) contenidoActual)) {
                        if (comentario.getEstado() == Estado.ACTIVO) {
                            //Se crea un panel para almacenar individualmente los comentarios
                            //Se crea dentro de un bucle para poder generar un panel individual por cada comentario de cada contenido
                            JPanel panelComentarioIndividual = new JPanel(new GridBagLayout());
                            GridBagConstraints gbcComentario = new GridBagConstraints();
                            gbcComentario.insets = new Insets(5, 5, 5, 5);
                            gbcComentario.anchor = GridBagConstraints.WEST;

                            //Se crean los elementos del panel individual
                            JLabel comentarioUsuarioLabel = new JLabel(gestor.buscarUsuarioPorId(comentario.getIdUsuario()).getUserName() + ": ");
                            JButton desactivarComentarioUsuario = new JButton("DESACTIVAR");
                            JTextArea comentarioArea = new JTextArea(comentario.getComentario());

                            desactivarComentarioUsuario.setPreferredSize(new Dimension(150, 20));

                            comentarioArea.setLineWrap(true);  // Habilita el salto de línea automático
                            comentarioArea.setWrapStyleWord(true);  // Hace que el salto de línea ocurra en los límites de palabras
                            comentarioArea.setEditable(false);
                            comentarioArea.setColumns(40); // Esto establece el ancho preferido
                            comentarioArea.setRows(3); // Esto asegura que no tiene un alto fijo, el contenido lo ajustará

                            //Se agrega al panel indiivdual los elmentos
                            gbcComentario.gridx = 0;
                            gbcComentario.gridy = 0;
                            gbcComentario.anchor = GridBagConstraints.NORTHWEST;
                            panelComentarioIndividual.add(comentarioUsuarioLabel, gbcComentario);


                            gbcComentario.anchor = GridBagConstraints.SOUTHWEST;
                            panelComentarioIndividual.add(desactivarComentarioUsuario, gbcComentario);

                            gbcComentario.gridx = 1;
                            gbcComentario.anchor = GridBagConstraints.EAST;
                            gbcComentario.fill = GridBagConstraints.VERTICAL;  // Para que el JTextArea utilice el espacio horizontal disponible
                            //gbcComentario.weightx = 1.0;  // Distribuye el espacio horizontalmente
                            panelComentarioIndividual.add(comentarioArea, gbcComentario);

                            if(!usuario.getAdmin()){
                                desactivarComentarioUsuario.setVisible(false);
                            }

                            gbcComentarios.gridy++;
                            panelComentarios.add(panelComentarioIndividual, gbcComentarios);

                            desactivarComentarioUsuario.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(comentario.getEstado() == Estado.ACTIVO){
                                        comentario.desactivar();
                                        desactivarComentarioUsuario.setText("    ACTIVAR    ");
                                    }else if(comentario.getEstado() == Estado.INACTIVO){
                                        comentario.activar();
                                        desactivarComentarioUsuario.setText("DESACTIVAR");
                                    }
                                }
                            });
                        }
                    }
                    panelComentarios.revalidate();
                    panelComentarios.repaint();

                };

                // Acción del botón like
                likeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gestor.likeDisLikeContenido(usuario, contenidoActual);
                        System.out.println(contenidoActual);
                        likeLabel.setText("LIKES: " + gestor.retornarCantidadLikes(contenidoActual));
                    }
                });

                // Acción del botón comments
                comentariosButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        actualizarComentarios.run();
                        panelComentarios.setVisible(!panelComentarios.isVisible());
                    }
                });

                // Accion del boton de dar de baja


                //Se dimensionan los elementos del panel de contenido
                Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
                comentariosButton.setPreferredSize(buttonSize);
                likeButton.setPreferredSize(buttonSize);

                activoButton.setPreferredSize(buttonSize);

                contenidoArea.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));

                //Se agregan al panel de contenido los elementos
//                gbcCont.gridx = 0;
//                gbcCont.gridy = 0;
//                gbcCont.gridwidth = 2;
//                gbcCont.anchor = GridBagConstraints.CENTER;
//                panelContenido.add(tituloLabel, gbcCont);
//
//                gbcCont.gridx = 0;
//                gbcCont.gridy = 1;
//                panelContenido.add(nombreLabel, gbcCont);
//
//                gbcCont.anchor = GridBagConstraints.EAST;
//                gbcCont.gridx = 1;
//                gbcCont.gridy = 1;
//                panelContenido.add(estadoLabel, gbcCont);
//
//                gbcCont.anchor = GridBagConstraints.CENTER;
//
//                gbcCont.gridx = 0;
//                gbcCont.gridy = 2;
//                panelContenido.add(contenidoArea, gbcCont);
//
//                gbcCont.anchor = GridBagConstraints.WEST;
//                gbcCont.gridx = 0;
//                gbcCont.gridy = 3;
//                panelContenido.add(likeLabel, gbcCont);
//
//                gbcCont.gridx = 1;
//                gbcCont.gridy = 4;
//                gbcCont.anchor = GridBagConstraints.EAST;
//                panelContenido.add(comentariosButton, gbcCont);
//
//                gbcCont.anchor = GridBagConstraints.WEST;
//                gbcCont.gridx = 0;
//                panelContenido.add(likeButton, gbcCont);
//
//                gbcCont.anchor = GridBagConstraints.CENTER;
//                gbcCont.gridx = 0;
//                panelContenido.add(activoButton, gbcCont);

                gbcCont.gridx = 0;
                gbcCont.gridy = 0;
                gbcCont.gridwidth = 3;
                gbcCont.anchor = GridBagConstraints.CENTER;
                panelContenido.add(tituloLabel, gbcCont);

                gbcCont.gridx = 0;
                gbcCont.gridy = 1;
                gbcCont.gridwidth = 2;
                gbcCont.anchor = GridBagConstraints.WEST;
                panelContenido.add(nombreLabel, gbcCont);

                gbcCont.gridx = 1;
                gbcCont.anchor = GridBagConstraints.EAST;
                panelContenido.add(estadoLabel, gbcCont);

                gbcCont.gridx = 0;
                gbcCont.gridy = 2;
                gbcCont.gridwidth = 3;
                gbcCont.anchor = GridBagConstraints.CENTER;
                panelContenido.add(contenidoArea, gbcCont);

                gbcCont.gridx = 0;
                gbcCont.gridy = 3;
                gbcCont.gridwidth = 1;
                gbcCont.anchor = GridBagConstraints.WEST;
                panelContenido.add(likeLabel, gbcCont);

                gbcCont.gridx = 0;
                gbcCont.gridy = 4;
                gbcCont.anchor = GridBagConstraints.WEST;
                panelContenido.add(likeButton, gbcCont);

                gbcCont.gridx = 2;
                gbcCont.anchor = GridBagConstraints.EAST;
                panelContenido.add(comentariosButton, gbcCont);

                gbcCont.gridx = 0;
                gbcCont.gridwidth = 3;
                gbcCont.anchor = GridBagConstraints.CENTER;
                panelContenido.add(activoButton, gbcCont);

                //Si el contenido no es interactivo, se esconden los botones de comentario y likes
                if (!(contenidoActual instanceof ContenidoInteractivo)) {
                    comentariosButton.setVisible(false);
                    likeButton.setVisible(false);
                    likeLabel.setVisible(false);
                }

                if(!usuario.getAdmin()){
                    activoButton.setVisible(false);
                    estadoLabel.setVisible(false);
                }

                activoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(contenidoActual.getEstado() == Estado.ACTIVO){
                            contenidoActual.desactivar();
                        }else if(contenidoActual.getEstado() == Estado.INACTIVO){
                            contenidoActual.activar();
                        }
                        estadoLabel.setText(contenidoActual.getEstado().toString());
                    }
                });

                //Action listener del boton de crear comentario
                comentarioComentar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String textoComentario = JOptionPane.showInputDialog("Ingrese comentario: ");
                        if(textoComentario != null){
                            gestor.agregarComentario(usuario, contenidoActual, textoComentario);
                            actualizarComentarios.run();
                        }
                    }
                });

                //Se agregan al primer panel del menu los demas paneles, el panel de contenido y el panel de comentarios
                panelComentarios.setVisible(false);
                panelVerContenido.add(panelContenido);
                panelVerContenido.add(panelComentarios);
                panelVerContenido.add(Box.createRigidArea(new Dimension(0, 50))); //Separacion de elemtnos
                panelVerContenido.add(new JSeparator(SwingConstants.HORIZONTAL)); //Separador visual
            }
        }

        //Se crea el boton de salir para agregarlos en otro panel, para dejarlo fijo en pantalla
        JButton salirButton = new JButton("SALIR");

        // Acción del botón Salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenido = null;
                menuPerfil();
            }
        });

        // Añadir el panel principal al JScrollPane para permitir el desplazamiento vertical
        JScrollPane scrollPane = new JScrollPane(panelVerContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30); // Ajustar la velocidad de desplazamiento

        //agregamos el scrollPane con el panel principal a un contenedor para juntarlo con el boton de salir
        JPanel container = new JPanel(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);

        // Agregar el botón de salir en la parte superior izquierda del contenedor
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(salirButton, BorderLayout.WEST);
        container.add(topPanel, BorderLayout.NORTH);

        //Seteamos el scrollpane para que se quede arriba ?
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
            }
        });

        setContentPane(container);
        setVisible(true);
    }

    public void menuVerMiInfo() { //Menu para ver informacion como usuario
        //Creamos y seteamos la configuracion del panel
        JPanel panelVerMiInfo = new JPanel();
        panelVerMiInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.anchor = GridBagConstraints.CENTER;

        //Creamos los elementos del panel
        JLabel userNameLabel = new JLabel("NOMBRE DE USUARIO:");
        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        JLabel mailLabel = new JLabel("CORREO ELECTRÓNICO:");
        JLabel preferenciasLabel = new JLabel("PREFERENCIAS:");

        JTextArea userNameArea = new JTextArea();
        JTextArea passwordArea = new JTextArea();
        JTextArea mailArea = new JTextArea();
        JTextArea preferenciasArea = new JTextArea();

        // Hacer las áreas de texto de solo lectura
        userNameArea.setEditable(false);
        passwordArea.setEditable(false);
        mailArea.setEditable(false);
        preferenciasArea.setEditable(false);

        // Obtener información del usuario y establecerla en las áreas de texto
        userNameArea.setText(usuario.getUserName());
        passwordArea.setText(usuario.getPassword());
        mailArea.setText(usuario.getMail());
        StringBuilder preferenciasText = new StringBuilder();
        for (Categoria preferencia : usuario.getPreferencias()) {
            preferenciasText.append(preferencia).append("\n");
        }
        preferenciasArea.setText(preferenciasText.toString());

        JButton salirButton = new JButton("SALIR");
        JButton modificarInfoButton = new JButton("MODIFICAR INFORMACIÓN");

        //Definimos las dimensiones de los elementos
        Dimension fieldSize = new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
        userNameArea.setPreferredSize(fieldSize);
        passwordArea.setPreferredSize(fieldSize);
        mailArea.setPreferredSize(fieldSize);

        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        salirButton.setPreferredSize(buttonSize);
        modificarInfoButton.setPreferredSize(buttonSize);
        preferenciasArea.setPreferredSize(new Dimension(BUTTON_WIDTH,120));

        // Posiciones de los elementos en el panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelVerMiInfo.add(userNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelVerMiInfo.add(userNameArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelVerMiInfo.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelVerMiInfo.add(passwordArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelVerMiInfo.add(mailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelVerMiInfo.add(mailArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelVerMiInfo.add(preferenciasLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelVerMiInfo.add(preferenciasArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelVerMiInfo.add(salirButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panelVerMiInfo.add(modificarInfoButton, gbc);

        // Acción del botón Salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil();
            }
        });

        modificarInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuModifInfo();
            }
        });

        // Mostrar el panel
        setContentPane(panelVerMiInfo);
        setVisible(true);
    }

    public void menuModifInfo() { //Menu modificar informacion como usuario
        //Creamos y seteamos la configuracion del panel
        JPanel panelModificarInfo = new JPanel();
        panelModificarInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_FINAL, INSET_FINAL, INSET_FINAL, INSET_FINAL);
        gbc.anchor = GridBagConstraints.CENTER;

        //Creamos los elementos del panel
        JLabel userNameLabel = new JLabel("NOMBRE DE USUARIO:");
        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        JLabel mailLabel = new JLabel("CORREO ELECTRÓNICO:");
        JLabel preferenciasLabel = new JLabel("PREFERENCIAS:");

        JTextField userNameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField mailField = new JTextField();
        JTextArea preferenciasArea = new JTextArea();

        // Obtener información del usuario y establecerla en los campos de entrada
        userNameField.setText(usuario.getUserName());
        passwordField.setText(usuario.getPassword());
        mailField.setText(usuario.getMail());
        final StringBuilder[] preferenciasText = {new StringBuilder()};
        for (Categoria preferencia : usuario.getPreferencias()) {
            preferenciasText[0].append(preferencia).append("\n");
        }
        preferenciasArea.setText(preferenciasText[0].toString());

        JComboBox<Categoria> preferenciasComboBox = new JComboBox<>(Categoria.values());
        preferenciasComboBox.setBounds(200, 310, 150, 30);

        JButton agregarEliminarButton = new JButton("AGREGAR/ELIMINAR");
        JButton guardarButton = new JButton("GUARDAR");
        JButton salirButton = new JButton("SALIR");

        //Definimos las dimensiones de los elementos
        Dimension fieldSize = new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
        userNameField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);
        mailField.setPreferredSize(fieldSize);

        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        agregarEliminarButton.setPreferredSize(buttonSize);
        guardarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);
        preferenciasArea.setPreferredSize(new Dimension(BUTTON_WIDTH,120));

        // Posiciones de los elementos en el panel
        gbc.gridx = 0;gbc.gridy = 0;gbc.anchor = GridBagConstraints.WEST;panelModificarInfo.add(userNameLabel, gbc);

        gbc.gridx = 1;gbc.anchor = GridBagConstraints.CENTER;panelModificarInfo.add(userNameField, gbc);

        gbc.gridx = 0;gbc.gridy = 1;gbc.anchor = GridBagConstraints.WEST;panelModificarInfo.add(passwordLabel, gbc);

        gbc.gridx = 1;gbc.anchor = GridBagConstraints.CENTER;panelModificarInfo.add(passwordField, gbc);

        gbc.gridx = 0;gbc.gridy = 2;gbc.anchor = GridBagConstraints.WEST;panelModificarInfo.add(mailLabel, gbc);

        gbc.gridx = 1;gbc.gridy = 2;gbc.anchor = GridBagConstraints.CENTER;panelModificarInfo.add(mailField, gbc);

        gbc.gridx = 0;gbc.gridy = 3;gbc.anchor = GridBagConstraints.NORTHWEST;panelModificarInfo.add(preferenciasLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;panelModificarInfo.add(agregarEliminarButton, gbc);gbc.anchor = GridBagConstraints.SOUTHWEST;panelModificarInfo.add(preferenciasComboBox, gbc);

        gbc.gridx = 1;gbc.gridy = 3;gbc.anchor = GridBagConstraints.CENTER;panelModificarInfo.add(preferenciasArea, gbc);

        gbc.gridx = 0;gbc.gridy = 4;gbc.anchor = GridBagConstraints.WEST;panelModificarInfo.add(salirButton, gbc);

        gbc.gridx = 1;gbc.anchor = GridBagConstraints.EAST;panelModificarInfo.add(guardarButton, gbc);

        // Acción del botón Agregar
        agregarEliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Categoria preferenciaSeleccionada = (Categoria) preferenciasComboBox.getSelectedItem();
                if (!usuario.getPreferencias().contains(preferenciaSeleccionada)) {
                    gestor.agregarPreferencia(usuario,preferenciaSeleccionada);
                    preferenciasText[0].append(preferenciaSeleccionada).append("\n");
                    preferenciasArea.setText(preferenciasText[0].toString());
                }
                else{
                    Categoria preferenciaSeleccionada2 = (Categoria) preferenciasComboBox.getSelectedItem();
                    gestor.eliminarPreferencia(usuario, preferenciaSeleccionada2);
                    preferenciasText[0] = new StringBuilder();
                    for (Categoria preferencia : usuario.getPreferencias()) {
                        preferenciasText[0].append(preferencia).append("\n");
                    }
                    preferenciasArea.setText(preferenciasText[0].toString());
                }
            }
        });

        // Acción del botón Guardar
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los nuevos valores de los campos de entrada
                String nuevoUserName = userNameField.getText();
                String nuevaPassword = passwordField.getText();
                String nuevoMail = mailField.getText();
                // Actualizar los datos del usuario
                gestor.actualizarDatosUsuario(nuevoUserName, nuevaPassword, nuevoMail, usuario.getIdUsuario());
                // Volver al menú de perfil
                menuVerMiInfo();
            }
        });

        // Acción del botón Cancelar
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Volver al menú de perfil sin guardar cambios
                menuVerMiInfo();
            }
        });

        // Mostrar el panel
        setContentPane(panelModificarInfo);
        setVisible(true);
    }

    // Método para limitar la longitud de caracteres
    private void setCharacterLimit(JTextComponent textComponent, int limit) {
        ((AbstractDocument) textComponent.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) return;
                if ((fb.getDocument().getLength() + string.length()) <= limit) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) return;
                if ((fb.getDocument().getLength() + text.length() - length) <= limit) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
}