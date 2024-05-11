package clases;

import clases.clasesUsuarios.Usuario;
import clases.gestores.GestorRedSocial;

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
