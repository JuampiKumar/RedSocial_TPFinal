package org.clases;

import org.clases.clasesUsuarios.Usuario;
import org.clases.gestores.GestorRedSocial;

public class RedSocial {
    //Atributos
    private PanelGrafico panelGrafico;
    private GestorRedSocial gestorRedSocial;
    //Constructor
    public RedSocial(){
        gestorRedSocial = new GestorRedSocial();
        gestorRedSocial.cargarDatosEnJSON();
        //gestorRedSocial.cargarDatosARedSocial();
        //gestorRedSocial.guardarDatosEnJSON();
        this.panelGrafico = new PanelGrafico(gestorRedSocial);
    }

    //Metodos
    public void inicio(){
        panelGrafico.menuPrincipal();
    }

}
