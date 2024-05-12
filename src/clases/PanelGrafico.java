package clases;
import clases.clasesContenido.Comunicado;
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
    private GestorRedSocial gestor;
    public PanelGrafico() {
        // Configurar la ventana principal
        setTitle("RED SOCIAL");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        this.gestor = new GestorRedSocial();
    }

    public void menuPrincipal() {
        JPanel panelMenuPrincipal = new JPanel();
        panelMenuPrincipal.setLayout(null); // Establecer el diseño del panel como null para usar coordenadas absolutas
        panelMenuPrincipal.setBackground(Color.YELLOW);
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
        Usuario usuario;
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
                  //  menuUsuario(gestor.encontrarUsuario(username, password));
                    menuPerfil(gestor.encontrarUsuario(username, password) );
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

    public void menuPerfil(Usuario usuario){
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
                menuPublicar(usuario);
                System.out.println("Publicando...");
            }
        });

        verPublicacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuVerContenido(usuario);
                System.out.println("Viendo publicaciones...");
            }
        });

        modificarInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para la opción de modificar información
                System.out.println("Modificando información...");
            }
        });

        miInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para la opción de mi información
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

    public void menuPublicar(Usuario usuario) {
        JPanel panelMenuPublicar = new JPanel();
        panelMenuPublicar.setLayout(null);

        JLabel tipoLabel = new JLabel("TIPO DE CONTENIDO:");
        tipoLabel.setBounds(50, 50, 150, 30);
        panelMenuPublicar.add(tipoLabel);

        JRadioButton comunicadoRadioButton = new JRadioButton("COMUNICADO");
        JRadioButton publicacionRadioButton = new JRadioButton("PUBLICACIÓN");
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

                if(tipo == TipoContenido.PUBLICACION){
                    gestor.agregarContenido(new ContenidoInteractivo(titulo, contenido, categoria, usuario.getUserName()));
                }else{
                    gestor.agregarContenido(new Comunicado(titulo, contenido, categoria, usuario.getUserName()));
                }

                // Aquí puedes implementar la lógica para la publicación
                System.out.println("Tipo: " + tipo);
                System.out.println("Título: " + titulo);
                System.out.println("Contenido: " + contenido);
                System.out.println("Categoría: " + categoria);
                menuPerfil(usuario);
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil(usuario);
            }
        });

        setContentPane(panelMenuPublicar);
        setVisible(true);
    }

    public void menuVerContenido(Usuario usuario) {
        JPanel panelVerContenido = new JPanel();
        panelVerContenido.setLayout(null);
        contenido = gestor.obtenerContenido();

        JLabel nombreLabel = new JLabel("USUARIO:");
        JLabel tituloLabel = new JLabel("TITULO:");
        JLabel contenidoLabel = new JLabel("CONTENIDO:");

        JTextArea nombreArea = new JTextArea();
        JTextArea tituloArea = new JTextArea();
        JTextArea contenidoArea = new JTextArea();

        // Hacer las áreas de texto de solo lectura
            nombreArea.setEditable(false);
            tituloArea.setEditable(false);
            contenidoArea.setEditable(false);

        nombreArea.setText(contenido.getUsuario());
        tituloArea.setText(contenido.getTitulo());
        contenidoArea.setText(contenido.getContenido());

        JButton siguienteButton = new JButton("->");
        JButton anteriorButton = new JButton("<-");
        JButton salirButton = new JButton("SALIR");

        // Posiciones y tamaños de los elementos
        nombreLabel.setBounds(50, 50, 100, 30);
        nombreArea.setBounds(150, 50, 200, 30);
        tituloLabel.setBounds(50, 100, 100, 30);
        tituloArea.setBounds(150, 100, 200, 30);
        contenidoLabel.setBounds(50, 150, 100, 30);
        contenidoArea.setBounds(150, 150, 200, 100);

        siguienteButton.setBounds(350, 300, 100, 30);
        anteriorButton.setBounds(50, 300, 100, 30);
        salirButton.setBounds(200, 400, 100, 30);

        // Agregar elementos al panel
        panelVerContenido.add(nombreLabel);
        panelVerContenido.add(nombreArea);
        panelVerContenido.add(tituloLabel);
        panelVerContenido.add(tituloArea);
        panelVerContenido.add(contenidoLabel);
        panelVerContenido.add(contenidoArea);
        panelVerContenido.add(siguienteButton);
        panelVerContenido.add(anteriorButton);
        panelVerContenido.add(salirButton);

        // Acción del botón Siguiente
        siguienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenido = gestor.avanzar(contenido);
                nombreArea.setText(contenido.getUsuario());
                tituloArea.setText(contenido.getTitulo());
                contenidoArea.setText(contenido.getContenido());
                System.out.println(contenido);
                System.out.println("siguiente");
            }
        });

        // Acción del botón Anterior
        anteriorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenido = gestor.retroceder(contenido);
                nombreArea.setText(contenido.getUsuario());
                tituloArea.setText(contenido.getTitulo());
                contenidoArea.setText(contenido.getContenido());
                System.out.println(contenido);
                System.out.println("anterior");
            }
        });

        // Acción del botón Salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPerfil(usuario);            }
        });

        // Mostrar el panel
        setContentPane(panelVerContenido);
        setVisible(true);
    }

}



