package org.clases;

import javax.swing.*;
import java.awt.*;

public class PanelConImagen extends JPanel {
    private Image imagen;
    private ImageIcon fondoPanelPrincipal;
    private ImageIcon fondoPanel;

    public PanelConImagen(boolean usarPNG) {
        if (usarPNG) {
            fondoPanelPrincipal = new ImageIcon("FONDO2.png");
        } else {
            fondoPanel = new ImageIcon("FONDO2.jfif");
        }
        this.imagen = usarPNG ? fondoPanelPrincipal.getImage() : fondoPanel.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}
