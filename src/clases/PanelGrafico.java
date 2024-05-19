package clases;
import clases.clasesContenido.ContenidoNoInteractivo;
import clases.clasesContenido.Contenido;
import clases.clasesContenido.ContenidoInteractivo;
import clases.clasesUsuarios.Usuario;
import clases.gestores.GestorRedSocial;
import enumeradores.Categoria;
import enumeradores.TipoContenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelGrafico extends JFrame {
    private JPanel menuActual;
    private int op = 0;
    private Contenido contenido;
    private Usuario usuario;
    private GestorRedSocial gestor;
    public PanelGrafico() {
        // Configurar la ventana principal
        setTitle("RED SOCIAL");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        this.gestor = new GestorRedSocial();
        this.contenido = null;
        this.usuario = null;
    }

    public void menuPrincipal() {
        JPanel panelMenuPrincipal = new JPanel();
        panelMenuPrincipal.setLayout(null); // Establecer el diseño del panel como null para usar coordenadas absolutas
        // Crear botones para las opciones del menú principal
        JButton registrarseButton = new JButton("REGISTRARSE");
        JButton ingresarButton = new JButton("INGRESAR");
        JButton salirButton = new JButton("SALIR");

        // Establecer posiciones y tamaños de los botones
        registrarseButton.setBounds(150, 50, 200, 30);
        ingresarButton.setBounds(150, 100, 200, 30);
        salirButton.setBounds(150, 150, 200, 30);

        // Agregar los botones al panel
        panelMenuPrincipal.add(registrarseButton);
        panelMenuPrincipal.add(ingresarButton);
        panelMenuPrincipal.add(salirButton);

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
                dispose();
            }
        });

        // Mostrar el panel del menú principal en la ventana
        setContentPane(panelMenuPrincipal);
        setVisible(true);
    }

    public void menuRegistro() {
        JPanel panelMenuRegistro = new JPanel();
        panelMenuRegistro.setLayout(null); // Establecer el diseño del panel como null para usar coordenadas absolutas

        // Crear elementos para el menú de registro
        JLabel usernameLabel = new JLabel("NOMBRE DE USUARIO:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        JPasswordField passwordField = new JPasswordField();
        JLabel correoLabel = new JLabel("CORREO:");
        JTextField correoField = new JTextField();
        JButton registrarButton = new JButton("REGISTRAR");
        JButton salirButton = new JButton("SALIR");

        // Establecer posiciones y tamaños de los elementos
        usernameLabel.setBounds(50, 50, 150, 30);
        usernameField.setBounds(250, 50, 150, 30);
        passwordLabel.setBounds(50, 100, 150, 30);
        passwordField.setBounds(250, 100, 150, 30);
        correoLabel.setBounds(50, 150, 150, 30);
        correoField.setBounds(250, 150, 150, 30);
        registrarButton.setBounds(200, 250, 100, 30);
        salirButton.setBounds(200, 300, 100, 30);

        // Agregar los elementos al panel
        panelMenuRegistro.add(usernameLabel);
        panelMenuRegistro.add(usernameField);
        panelMenuRegistro.add(passwordLabel);
        panelMenuRegistro.add(passwordField);
        panelMenuRegistro.add(correoLabel);
        panelMenuRegistro.add(correoField);
        panelMenuRegistro.add(registrarButton);
        panelMenuRegistro.add(salirButton);

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

    public void menuIngreso() {
        JPanel panelMenuIngreso = new JPanel();
        panelMenuIngreso.setLayout(null); // Establecer el diseño del panel como null para usar coordenadas absolutas

        // Crear elementos para el menú de registro
        JLabel usernameLabel = new JLabel("NOMBRE DE USUARIO:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        JPasswordField passwordField = new JPasswordField();
        JButton ingresarButton = new JButton("INGRESAR");
        JButton salirButton = new JButton("SALIR");

        // Establecer posiciones y tamaños de los elementos
        usernameLabel.setBounds(50, 50, 150, 30);
        usernameField.setBounds(250, 50, 150, 30);
        passwordLabel.setBounds(50, 100, 150, 30);
        passwordField.setBounds(250, 100, 150, 30);
        ingresarButton.setBounds(200, 200, 100, 30);
        salirButton.setBounds(200, 250, 100, 30);

        // Agregar los elementos al panel
        panelMenuIngreso.add(usernameLabel);
        panelMenuIngreso.add(usernameField);
        panelMenuIngreso.add(passwordLabel);
        panelMenuIngreso.add(passwordField);
        panelMenuIngreso.add(ingresarButton);
        panelMenuIngreso.add(salirButton);

        // Definir ActionListener para el botón de registrar
        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto y almacenarlos en variables
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // La contraseña se obtiene de manera diferente debido al uso de JPasswordField

                if(gestor.encontrarUsuario(username, password) != null){
                    usuario = gestor.encontrarUsuario(username, password);
                    menuPerfil();
                    // menuPerfil(gestor.encontrarUsuario(username, password));
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

    public void menuPerfil(){
        JPanel panelMenuPerfil = new JPanel();
        panelMenuPerfil.setLayout(null); // Establecer el diseño del panel como null para usar coordenadas absolutas

        // Crear botones para las opciones del menú de perfil
        JButton publicarButton = new JButton("PUBLICAR");
        JButton verPublicacionesButton = new JButton("VER PUBLICACIONES");
        JButton modificarInfoButton = new JButton("MODIFICAR INFORMACIÓN");
        JButton miInfoButton = new JButton("MI INFORMACIÓN");
        JButton salirButton = new JButton("SALIR");

        // Establecer posiciones y tamaños de los botones
        publicarButton.setBounds(150, 50, 200, 30);
        verPublicacionesButton.setBounds(150, 100, 200, 30);
        modificarInfoButton.setBounds(150, 150, 200, 30);
        miInfoButton.setBounds(150, 200, 200, 30);
        salirButton.setBounds(150, 300, 200, 30);

        // Agregar los botones al panel
        panelMenuPerfil.add(publicarButton);
        panelMenuPerfil.add(verPublicacionesButton);
        panelMenuPerfil.add(modificarInfoButton);
        panelMenuPerfil.add(miInfoButton);
        panelMenuPerfil.add(salirButton);

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

        modificarInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuModifInfo();
                System.out.println("Modificando información...");
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

    public void menuPublicar() {
        JPanel panelMenuPublicar = new JPanel();
        panelMenuPublicar.setLayout(null);

        JLabel tipoLabel = new JLabel("INTERACCIONES:");
        tipoLabel.setBounds(50, 50, 150, 30);
        panelMenuPublicar.add(tipoLabel);

        JRadioButton comunicadoRadioButton = new JRadioButton("NO");
        JRadioButton publicacionRadioButton = new JRadioButton("SI");
        ButtonGroup tipoGroup = new ButtonGroup();
        comunicadoRadioButton.setBounds(200, 50, 150, 30);
        publicacionRadioButton.setBounds(350, 50, 150, 30);
        tipoGroup.add(comunicadoRadioButton);
        tipoGroup.add(publicacionRadioButton);
        panelMenuPublicar.add(comunicadoRadioButton);
        panelMenuPublicar.add(publicacionRadioButton);

        JLabel tituloLabel = new JLabel("TÍTULO:");
        tituloLabel.setBounds(50, 100, 150, 30);
        panelMenuPublicar.add(tituloLabel);

        JTextField tituloField = new JTextField();
        tituloField.setBounds(200, 100, 250, 30);
        panelMenuPublicar.add(tituloField);

        JLabel contenidoLabel = new JLabel("CONTENIDO:");
        contenidoLabel.setBounds(50, 150, 150, 30);
        panelMenuPublicar.add(contenidoLabel);

        JTextArea contenidoArea = new JTextArea();
        JScrollPane contenidoScrollPane = new JScrollPane(contenidoArea);
        contenidoScrollPane.setBounds(200, 150, 250, 100);
        panelMenuPublicar.add(contenidoScrollPane);

        JLabel categoriaLabel = new JLabel("CATEGORIA:");
        categoriaLabel.setBounds(50, 270, 150, 30);
        panelMenuPublicar.add(categoriaLabel);

        Categoria[] categorias = {Categoria.ACTUALIDAD, Categoria.DEPORTE, Categoria.PELICULAS, Categoria.TECNOLOGIA, Categoria.VIDEOJUEGOS, Categoria.OTROS};
        JComboBox<Categoria> categoriaComboBox = new JComboBox<>(categorias);
        categoriaComboBox.setBounds(200, 270, 150, 30);
        panelMenuPublicar.add(categoriaComboBox);

        JButton publicarButton = new JButton("PUBLICAR");
        publicarButton.setBounds(150, 350, 150, 30);
        panelMenuPublicar.add(publicarButton);

        JButton salirButton = new JButton("SALIR");
        salirButton.setBounds(150, 400, 150, 30);
        panelMenuPublicar.add(salirButton);

        // Definir ActionListener para el botón de publicar
        publicarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TipoContenido tipo = comunicadoRadioButton.isSelected() ? TipoContenido.COMUNICADO : TipoContenido.PUBLICACION;
                String titulo = tituloField.getText();
                String contenido = contenidoArea.getText();
                Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();
                Contenido publicacion = null;
                if(tipo == TipoContenido.PUBLICACION){
                    publicacion = new ContenidoInteractivo(titulo, contenido, categoria, usuario);
                }else{
                    publicacion = new ContenidoNoInteractivo(titulo, contenido, categoria, usuario);
                }
                gestor.agregarContenido(publicacion);
                gestor.agregarPublicacionAUsuario(usuario, publicacion);
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

    public void menuComments(){
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

    public void menuVerMiInfo() {
        JPanel panelVerMiInfo = new JPanel();
        panelVerMiInfo.setLayout(null);

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

        // Posiciones y tamaños de los elementos
        userNameLabel.setBounds(50, 50, 150, 30);
        userNameArea.setBounds(200, 50, 150, 30);
        passwordLabel.setBounds(50, 100, 150, 30);
        passwordArea.setBounds(200, 100, 150, 30);
        mailLabel.setBounds(50, 150, 150, 30);
        mailArea.setBounds(200, 150, 150, 30);
        preferenciasLabel.setBounds(50, 200, 150, 30);
        preferenciasArea.setBounds(200, 200, 150, 100);
        salirButton.setBounds(150, 350, 100, 30);

        // Acción del botón Salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil();
            }
        });

        // Agregar elementos al panel
        panelVerMiInfo.add(userNameLabel);
        panelVerMiInfo.add(userNameArea);
        panelVerMiInfo.add(passwordLabel);
        panelVerMiInfo.add(passwordArea);
        panelVerMiInfo.add(mailLabel);
        panelVerMiInfo.add(mailArea);
        panelVerMiInfo.add(preferenciasLabel);
        panelVerMiInfo.add(preferenciasArea);
        panelVerMiInfo.add(salirButton);

        // Mostrar el panel
        setContentPane(panelVerMiInfo);
        setVisible(true);
    }

    public void menuModifInfo() {
        JPanel panelModificarInfo = new JPanel();
        panelModificarInfo.setLayout(null);

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

        JButton agregarButton = new JButton("AGREGAR");
        JButton eliminarButton = new JButton("ELIMINAR");
        JButton guardarButton = new JButton("GUARDAR");
        JButton cancelarButton = new JButton("CANCELAR");

        // Posiciones y tamaños de los elementos
        userNameLabel.setBounds(50, 50, 150, 30);
        userNameField.setBounds(200, 50, 150, 30);
        passwordLabel.setBounds(50, 100, 150, 30);
        passwordField.setBounds(200, 100, 150, 30);
        mailLabel.setBounds(50, 150, 150, 30);
        mailField.setBounds(200, 150, 150, 30);
        preferenciasLabel.setBounds(50, 200, 150, 30);
        preferenciasArea.setBounds(200, 200, 150, 100);
        preferenciasComboBox.setBounds(200, 310, 150, 30);
        agregarButton.setBounds(50, 350, 100, 30);
        eliminarButton.setBounds(200, 350, 100, 30);
        guardarButton.setBounds(50, 400, 100, 30);
        cancelarButton.setBounds(200, 400, 100, 30);

        // Acción del botón Agregar
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Categoria preferenciaSeleccionada = (Categoria) preferenciasComboBox.getSelectedItem();
                if (!usuario.getPreferencias().contains(preferenciaSeleccionada)) {
                    usuario.getPreferencias().add(preferenciaSeleccionada);
                    preferenciasText[0].append(preferenciaSeleccionada).append("\n");
                    preferenciasArea.setText(preferenciasText[0].toString());
                }
            }
        });

        // Acción del botón Eliminar
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Categoria preferenciaSeleccionada = (Categoria) preferenciasComboBox.getSelectedItem();
                if (usuario.getPreferencias().contains(preferenciaSeleccionada)) {
                    usuario.getPreferencias().remove(preferenciaSeleccionada);
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
                usuario.setUserName(nuevoUserName);
                usuario.setPassword(nuevaPassword);
                usuario.setMail(nuevoMail);

                // Mostrar mensaje de éxito o realizar alguna otra acción después de guardar los cambios

                // Volver al menú de perfil
                menuPerfil();
            }
        });

        // Acción del botón Cancelar
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Volver al menú de perfil sin guardar cambios
                menuPerfil();
            }
        });

        // Agregar elementos al panel
        panelModificarInfo.add(userNameLabel);
        panelModificarInfo.add(userNameField);
        panelModificarInfo.add(passwordLabel);
        panelModificarInfo.add(passwordField);
        panelModificarInfo.add(mailLabel);
        panelModificarInfo.add(mailField);
        panelModificarInfo.add(preferenciasLabel);
        panelModificarInfo.add(preferenciasArea);
        panelModificarInfo.add(preferenciasComboBox);
        panelModificarInfo.add(agregarButton);
        panelModificarInfo.add(eliminarButton);
        panelModificarInfo.add(guardarButton);
        panelModificarInfo.add(cancelarButton);

        // Mostrar el panel
        setContentPane(panelModificarInfo);
        setVisible(true);
    }
}




