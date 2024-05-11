package clases;
import clases.clasesUsuarios.Usuario;
import clases.gestores.GestorRedSocial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGrafico extends JFrame {
    private JPanel menuActual;
    private int op = 0;
    private GestorRedSocial gestor;
    public PanelGrafico() {
        // Configurar la ventana principal
        setTitle("RED SOCIAL");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        this.gestor = new GestorRedSocial();
    }

    public void menuPrincipal() {
        JPanel panelMenuPrincipal = new JPanel();
        panelMenuPrincipal.setLayout(null); // Establecer el diseño del panel como null para usar coordenadas absolutas

        // Crear botones para las opciones del menú principal
        JButton registrarseButton = new JButton("REGISTRARSE");
        JButton ingresarButton = new JButton("INGRESAR");
        JButton salirButton = new JButton("SALIR");

        // Establecer posiciones y tamaños de los botones
        registrarseButton.setBounds(100, 50, 200, 30);
        ingresarButton.setBounds(100, 100, 200, 30);
        salirButton.setBounds(100, 150, 200, 30);

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
        usernameField.setBounds(200, 50, 150, 30);
        passwordLabel.setBounds(50, 100, 150, 30);
        passwordField.setBounds(200, 100, 150, 30);
        correoLabel.setBounds(50, 150, 150, 30);
        correoField.setBounds(200, 150, 150, 30);
        registrarButton.setBounds(150, 200, 100, 30);
        salirButton.setBounds(150, 250, 100, 30);

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
        usernameField.setBounds(200, 50, 150, 30);
        passwordLabel.setBounds(50, 100, 150, 30);
        passwordField.setBounds(200, 100, 150, 30);
        ingresarButton.setBounds(150, 200, 100, 30);
        salirButton.setBounds(150, 300, 100, 30);

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
        publicarButton.setBounds(100, 50, 200, 30);
        verPublicacionesButton.setBounds(100, 100, 200, 30);
        modificarInfoButton.setBounds(100, 150, 200, 30);
        miInfoButton.setBounds(100, 200, 200, 30);
        salirButton.setBounds(100, 250, 200, 30);

        // Agregar los botones al panel
        panelMenuPerfil.add(publicarButton);
        panelMenuPerfil.add(verPublicacionesButton);
        panelMenuPerfil.add(modificarInfoButton);
        panelMenuPerfil.add(miInfoButton);
        panelMenuPerfil.add(salirButton);

        // Definir ActionListener para cada botón
        publicarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para la opción de publicar
                System.out.println("Publicando...");
            }
        });

        verPublicacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para la opción de ver publicaciones
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

    public void menuPublicar(){

    }


}



