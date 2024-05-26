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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
        gbc.insets = new Insets(10, 10, 10, 10);
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
        gbc.insets = new Insets(10, 10, 10, 10);
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
        gbc.insets = new Insets(150, 10, 10, 10);

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
        gbc.insets = new Insets(10, 10, 10, 10);
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
        gbc.insets = new Insets(150, 10, 10, 10);

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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Crear botones para las opciones del menú de perfil
        JButton publicarButton = new JButton("PUBLICAR");
        JButton verPublicacionesButton = new JButton("VER PUBLICACIONES");
        JButton miInfoButton = new JButton("MI INFORMACIÓN");
        JButton salirButton = new JButton("SALIR");

        //Definimos las dimensiones de los botones del panel
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        publicarButton.setPreferredSize(buttonSize);
        verPublicacionesButton.setPreferredSize(buttonSize);
        miInfoButton.setPreferredSize(buttonSize);
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
        panelMenuPerfil.add(salirButton, gbc);

        // Definir ActionListener para cada botón
        publicarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPublicar();
                System.out.println("Publicando...");
            }
        });

        verPublicacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuVerContenido();
                System.out.println("Viendo publicaciones...");
            }
        });

        miInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuVerMiInfo();
                System.out.println("Viendo mi información...");
            }
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

    public void menuPublicar() { //Menu publicar
        //Creamos y seteamos la configuracion del panel del menuPublicar
        JPanel panelMenuPublicar = new JPanel();
        panelMenuPublicar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
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
        JScrollPane contenidoScrollPane = new JScrollPane(contenidoArea);

        Categoria[] categorias = {Categoria.ACTUALIDAD, Categoria.DEPORTE, Categoria.PELICULAS, Categoria.TECNOLOGIA, Categoria.VIDEOJUEGOS, Categoria.OTROS};
        JComboBox<Categoria> categoriaComboBox = new JComboBox<>(categorias);

        JButton publicarButton = new JButton("PUBLICAR");
        JButton salirButton = new JButton("SALIR");

        //Definimos las dimensiones de los elementos
        Dimension fieldSize = new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
        tituloField.setPreferredSize(fieldSize);
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        publicarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);
        contenidoScrollPane.setPreferredSize(new Dimension(FIELD_WIDTH, 150));

        //Agregamos al panel los elementos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuPublicar.add(tipoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelMenuPublicar.add(comunicadoRadioButton, gbc);
        gbc.gridx = 2;
        panelMenuPublicar.add(publicacionRadioButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelMenuPublicar.add(tituloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuPublicar.add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenuPublicar.add(contenidoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuPublicar.add(contenidoScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelMenuPublicar.add(categoriaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuPublicar.add(categoriaComboBox, gbc);

        //Espacio entre elemntos
        gbc.insets = new Insets(150, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuPublicar.add(publicarButton, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMenuPublicar.add(salirButton, gbc);


        // Definir ActionListener para el botón de publicar
        publicarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TipoContenido tipo = comunicadoRadioButton.isSelected() ? TipoContenido.COMUNICADO : TipoContenido.PUBLICACION;
                String titulo = tituloField.getText();
                String contenido = contenidoArea.getText();
                Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();
                Contenido publicacion = null;
                if(tipo == TipoContenido.PUBLICACION){ //Se va a crear un contenido u otro dependiendo la opcion elegida en los radioButton
                    publicacion = new ContenidoInteractivo(titulo, contenido, categoria, usuario.getIdUsuario());
                }else{
                    publicacion = new ContenidoNoInteractivo(titulo, contenido, categoria, usuario.getIdUsuario());
                }
                gestor.agregarContenido(publicacion);
                gestor.agregarPublicacionAUsuario(usuario, publicacion); //Se llaman metodos del gestor
                menuPerfil();
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
            Contenido contenidoActual = iterator.next();

            //Panel de comentarios que va a tener la opcion de crear comentario y un panel interno con cada comentario
            JPanel panelComentarios = new JPanel();
            panelComentarios.setLayout(new GridBagLayout());
            GridBagConstraints gbcComentarios = new GridBagConstraints();
            gbcComentarios.insets = new Insets(5, 5, 5, 5);
            gbcComentarios.anchor = GridBagConstraints.WEST;
            panelComentarios.setVisible(false);
            JButton comentarioComentar = new JButton("COMENTAR");

            //Panel donde se vera el contenido, que a su vez tendra el panel de comentarios (panelComentarios)
            JPanel panelContenido = new JPanel();
            panelContenido.setLayout(new GridBagLayout());
            GridBagConstraints gbcCont = new GridBagConstraints();
            gbcCont.insets = new Insets(10, 10, 10, 10);
            gbcCont.anchor = GridBagConstraints.CENTER;

            //Se crean los elementos del panelContenido
            JLabel nombreLabel = new JLabel(gestor.buscarPorId(contenidoActual.getIdUsuario(), Usuario.class).getUserName());
            JLabel tituloLabel = new JLabel(contenidoActual.getTitulo());

            JLabel likeLabel = new JLabel("LIKES: " + gestor.retornarCantidadLikes(contenidoActual));

            JTextArea contenidoArea = new JTextArea();
            contenidoArea.setLineWrap(true);  // Habilita el salto de línea automático
            contenidoArea.setWrapStyleWord(true);  // Hace que el salto de línea ocurra en los límites de palabras
            contenidoArea.setEditable(false);

            contenidoArea.setText(contenidoActual.getContenido());

            JButton comentariosButton = new JButton("COMENTARIOS");
            JButton likeButton = new JButton(":)");

            //Se crea un Runnable para poder actualizar los comentarios cuando se escriba uno nuevo
            Runnable actualizarComentarios = () -> {
                panelComentarios.removeAll();
                gbcComentarios.gridx = 0;
                gbcComentarios.gridy = 0;
                panelComentarios.add(comentarioComentar, gbcComentarios);
                for (Comentario comentario : gestor.devolverListaDeComentarioDeContenido((ContenidoInteractivo)contenidoActual)) {
                    if(comentario.getEstado() == Estado.ACTIVO) {
                        //Se crea un panel para almacenar individualmente los comentarios
                        //Se crea dentro de un bucle para poder generar un panel individual por cada comentario de cada contenido
                        JPanel panelComentarioIndividual = new JPanel(new GridBagLayout());
                        GridBagConstraints gbcComentario = new GridBagConstraints();
                        gbcComentario.insets = new Insets(5, 5, 5, 5);
                        gbcComentario.anchor = GridBagConstraints.WEST;

                        //Se crean los elementos del panel individual
                        JLabel comentarioUsuarioLabel = new JLabel(gestor.buscarPorId(comentario.getIdUsuario(), Usuario.class).getUserName() + ": ");
                        JTextArea comentarioArea = new JTextArea(comentario.getComentario());

                        comentarioArea.setLineWrap(true);  // Habilita el salto de línea automático
                        comentarioArea.setWrapStyleWord(true);  // Hace que el salto de línea ocurra en los límites de palabras
                        comentarioArea.setEditable(false);

                        comentarioArea.setColumns(40); // Esto establece el ancho preferido
                        comentarioArea.setRows(1); // Esto asegura que no tiene un alto fijo, el contenido lo ajustará

                        //Se agrega al panel indiivdual los elmentos
                        gbcComentario.gridx = 0;
                        gbcComentario.gridy = 0;
                        panelComentarioIndividual.add(comentarioUsuarioLabel, gbcComentario);

                        gbcComentario.gridx = 1;
                        gbcComentario.fill = GridBagConstraints.HORIZONTAL;  // Para que el JTextArea utilice el espacio horizontal disponible
                        gbcComentario.weightx = 1.0;  // Distribuye el espacio horizontalmente
                        panelComentarioIndividual.add(comentarioArea, gbcComentario);

                        gbcComentarios.gridy++;
                        panelComentarios.add(panelComentarioIndividual, gbcComentarios);
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

            //Se dimensionan los elementos del panel de contenido
            Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
            comentariosButton.setPreferredSize(buttonSize);
            likeButton.setPreferredSize(buttonSize);
            contenidoArea.setPreferredSize(new Dimension(600, 200));

            //Se agregan al panel de contenido los elementos
            gbcCont.gridx = 0;
            gbcCont.gridy = 0;
            gbcCont.gridwidth = 2;
            gbcCont.anchor = GridBagConstraints.CENTER;
            panelContenido.add(tituloLabel, gbcCont);

            gbcCont.gridx = 0;
            gbcCont.gridy = 1;
            panelContenido.add(nombreLabel, gbcCont);

            gbcCont.gridx = 0;
            gbcCont.gridy = 2;
            panelContenido.add(contenidoArea, gbcCont);

            gbcCont.anchor = GridBagConstraints.WEST;
            gbcCont.gridx = 0;
            gbcCont.gridy = 3;
            panelContenido.add(likeLabel, gbcCont);

            gbcCont.gridx = 1;
            gbcCont.gridy = 4;
            gbcCont.anchor = GridBagConstraints.EAST;
            panelContenido.add(comentariosButton, gbcCont);

            gbcCont.anchor = GridBagConstraints.WEST;
            gbcCont.gridx = 0;
            panelContenido.add(likeButton, gbcCont);

            //Si el contenido no es interactivo, se esconden los botones de comentario y likes
            if(!(contenidoActual instanceof ContenidoInteractivo)){
                comentariosButton.setVisible(false);
                likeButton.setVisible(false);
                likeLabel.setVisible(false);
            }

            //Action listener del boton de crear comentario
            comentarioComentar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String textoComentario = JOptionPane.showInputDialog("Ingrese comentario: ");
                    gestor.agregarComentario(usuario, contenidoActual, textoComentario);
                    actualizarComentarios.run();
                    System.out.println("crea comentario");
                }
            });

            //Se agregan al primer panel del menu los demas paneles, el panel de contenido y el panel de comentarios
            panelComentarios.setVisible(false);
            panelVerContenido.add(panelContenido);
            panelVerContenido.add(panelComentarios);
            panelVerContenido.add(Box.createRigidArea(new Dimension(0, 50))); //Separacion de elemtnos
            panelVerContenido.add(new JSeparator(SwingConstants.HORIZONTAL)); //Separador visual
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

        //agregamos el scrollPane con el panel principal a un contenedor para juntarlo con el botos de salir
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

    /*
    public void menuVerContenido() {
        JPanel panelVerContenido = new JPanel();
        panelVerContenido.setLayout(null);
        if(contenido == null) {
            contenido = gestor.obtenerContenido();
        }

        JLabel nombreLabel = new JLabel("USUARIO:");
        JLabel tituloLabel = new JLabel("TITULO:");
        JLabel contenidoLabel = new JLabel("CONTENIDO:");

        JLabel likeLabel = new JLabel("LIKES:");
        JLabel cantLikeLabel = new JLabel(" ");

        JTextArea nombreArea = new JTextArea();
        JTextArea tituloArea = new JTextArea();
        JTextArea contenidoArea = new JTextArea();
        contenidoArea.setLineWrap(true);  // Habilita el salto de línea automático
        contenidoArea.setWrapStyleWord(true);  // Hace que el salto de línea ocurra en los límites de palabras

        // Hacer las áreas de texto de solo lectura
        nombreArea.setEditable(false);
        tituloArea.setEditable(false);
        contenidoArea.setEditable(false);

        nombreArea.setText(contenido.getUsuario().getUserName());
        tituloArea.setText(contenido.getTitulo());
        contenidoArea.setText(contenido.getContenido());
        cantLikeLabel.setText(gestor.retornarCantidadLikes(contenido));

        JButton siguienteButton = new JButton("->");
        JButton anteriorButton = new JButton("<-");
        JButton salirButton = new JButton("SALIR");

        JButton comentariosButton = new JButton("COMMENTS");
        JButton likeButton = new JButton(":)");

        // Posiciones y tamaños de los elementos
        nombreLabel.setBounds(50, 50, 100, 30);
        nombreArea.setBounds(150, 50, 200, 30);
        tituloLabel.setBounds(50, 100, 100, 30);
        tituloArea.setBounds(150, 100, 200, 30);
        contenidoLabel.setBounds(50, 150, 100, 30);
        contenidoArea.setBounds(150, 150, 200, 100);

        siguienteButton.setBounds(340, 400, 110, 30);
        anteriorButton.setBounds(40, 400, 110, 30);
        salirButton.setBounds(200, 400, 100, 30);

        comentariosButton.setBounds(340, 300, 110, 30);
        likeButton.setBounds(40, 300, 110, 30);
        likeLabel.setBounds(200, 300, 50, 30);
        cantLikeLabel.setBounds(250, 300, 50, 30);

        // Agregar elementos al panel
        panelVerContenido.add(nombreLabel); panelVerContenido.add(nombreArea); panelVerContenido.add(tituloLabel); panelVerContenido.add(tituloArea);
        panelVerContenido.add(contenidoLabel); panelVerContenido.add(contenidoArea); panelVerContenido.add(siguienteButton); panelVerContenido.add(anteriorButton);
        panelVerContenido.add(salirButton); panelVerContenido.add(comentariosButton); panelVerContenido.add(likeButton); panelVerContenido.add(likeLabel);
        panelVerContenido.add(cantLikeLabel);

        if(!(contenido instanceof ContenidoInteractivo)){
            comentariosButton.setVisible(false);
            likeButton.setVisible(false);
            likeLabel.setVisible(false);
            cantLikeLabel.setVisible(false);
        }

        // Acción del botón Siguiente
        siguienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenido = gestor.avanzar(contenido);
                nombreArea.setText(contenido.getUsuario().getUserName());
                tituloArea.setText(contenido.getTitulo());
                contenidoArea.setText(contenido.getContenido());
                if(contenido instanceof ContenidoInteractivo){
                    comentariosButton.setVisible(true); likeButton.setVisible(true); likeLabel.setVisible(true); cantLikeLabel.setVisible(true);
                    cantLikeLabel.setText(gestor.retornarCantidadLikes(contenido));
                }else{
                    comentariosButton.setVisible(false); likeButton.setVisible(false);
                    likeLabel.setVisible(false); cantLikeLabel.setVisible(false);
                }
                System.out.println(contenido);
                System.out.println("siguiente");
            }
        });

        // Acción del botón Anterior
        anteriorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenido = gestor.retroceder(contenido);
                nombreArea.setText(contenido.getUsuario().getUserName());
                tituloArea.setText(contenido.getTitulo());
                contenidoArea.setText(contenido.getContenido());
                if(contenido instanceof ContenidoInteractivo){
                    comentariosButton.setVisible(true); likeButton.setVisible(true); likeLabel.setVisible(true); cantLikeLabel.setVisible(true);
                    cantLikeLabel.setText(gestor.retornarCantidadLikes(contenido));
                }else{
                    comentariosButton.setVisible(false); likeButton.setVisible(false);
                    likeLabel.setVisible(false); cantLikeLabel.setVisible(false);
                }
                System.out.println(contenido);
                System.out.println("anterior");
            }
        });

        // Acción del botón like
        likeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor.likeDisLikeContenido(usuario, contenido);
                cantLikeLabel.setText(gestor.retornarCantidadLikes(contenido));
                System.out.println("like");
            }
        });

        // Acción del botón comments
        comentariosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuComments();
                System.out.println("comentarios...");
            }
        });

        // Acción del botón Salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenido = null;
                menuPerfil();
            }
        });

        // Mostrar el panel
        setContentPane(panelVerContenido);
        setVisible(true);
    }

     */

    /*public void menuComments(){
        JPanel panelComments = new JPanel();
        panelComments.setLayout(null); // Establecer el diseño del panel como null para usar coordenadas absolutas

        // Crear botón para salir y agregar comentario
        JButton salirButton = new JButton("SALIR");
        JButton agregarComentarioButton = new JButton("AGREGAR COMENTARIO");

        // Establecer posiciones y tamaños de los botones
        salirButton.setBounds(50, 10, 180, 30);
        agregarComentarioButton.setBounds(270, 10, 180, 30);

        // Agregar los botones al panel
        panelComments.add(salirButton);
        panelComments.add(agregarComentarioButton);

        // Crear área de texto para mostrar los comentarios
        JTextArea comentariosArea = new JTextArea();
        comentariosArea.setEditable(false); // Hacer que el área de texto no sea editable

        // Llenar el área de texto con los comentarios
        StringBuilder sb = new StringBuilder();
        for (Comentario comentario : ((ContenidoInteractivo)contenido).getComentarios()) {
            sb.append(comentario.getUsuario().getUserName()).append(": ").append(comentario.getComentario()).append("\n");
        }
        comentariosArea.setText(sb.toString());

        // Crear un JScrollPane para el área de texto
        JScrollPane scrollPane = new JScrollPane(comentariosArea);
        scrollPane.setBounds(50, 50, 400, 400);

        // Agregar el JScrollPane al panel
        panelComments.add(scrollPane);

        // Definir ActionListener para el botón de salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuVerContenido();
            }
        });

        // Definir ActionListener para el botón de agregar comentario
        agregarComentarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuCrearComentario();
            }
        });

        // Mostrar el panel de comentarios en la ventana
        setContentPane(panelComments);
        setVisible(true);
    }

    public void menuCrearComentario(){
        JPanel panelMenuCrearComentario = new JPanel();
        panelMenuCrearComentario.setLayout(null);

        JLabel comentarioLabel = new JLabel("ESCRIBA SU COMENTARIO:");
        comentarioLabel.setBounds(50, 50, 150, 30);
        panelMenuCrearComentario.add(comentarioLabel);

        JTextArea comentarioArea = new JTextArea();
        JScrollPane comentarioScrollPane = new JScrollPane(comentarioArea);
        comentarioScrollPane.setBounds(50, 100, 400, 100);
        panelMenuCrearComentario.add(comentarioScrollPane);

        JButton enviarButton = new JButton("ENVIAR");
        enviarButton.setBounds(120, 300, 100, 30);
        panelMenuCrearComentario.add(enviarButton);

        JButton salirButton = new JButton("SALIR");
        salirButton.setBounds(270, 300, 100, 30);
        panelMenuCrearComentario.add(salirButton);

        // Definir ActionListener para el botón de enviar
        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textoComentario = comentarioArea.getText();
                gestor.agregarComentario(usuario, contenido, textoComentario);
                System.out.println("crea comentario");
                menuComments();
            }
        });

        // Definir ActionListener para el botón de salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuComments();
            }
        });

        setContentPane(panelMenuCrearComentario);
        setVisible(true);
    }

     */

    public void menuVerMiInfo() { //Menu para ver informacion como usuario
        //Creamos y seteamos la configuracion del panel
        JPanel panelVerMiInfo = new JPanel();
        panelVerMiInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
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
        gbc.insets = new Insets(10, 10, 10, 10);
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
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelModificarInfo.add(userNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelModificarInfo.add(userNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelModificarInfo.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelModificarInfo.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelModificarInfo.add(mailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelModificarInfo.add(mailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelModificarInfo.add(preferenciasLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        panelModificarInfo.add(agregarEliminarButton, gbc);
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        panelModificarInfo.add(preferenciasComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelModificarInfo.add(preferenciasArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelModificarInfo.add(salirButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panelModificarInfo.add(guardarButton, gbc);

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
}