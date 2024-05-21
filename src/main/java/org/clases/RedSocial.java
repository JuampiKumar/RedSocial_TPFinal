package org.clases;

import clases.clasesUsuarios.Usuario;

public class RedSocial {
    //Atributos
    PanelGrafico panelGrafico;


    //Constructor
    public RedSocial(){
        this.panelGrafico = new PanelGrafico();
    }

    //Metodos
    public void inicio(){
        Usuario usuario;
        panelGrafico.menuPrincipal();
    }

}
