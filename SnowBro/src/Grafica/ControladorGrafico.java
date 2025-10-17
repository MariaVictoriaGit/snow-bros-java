package SnowBro.src.Grafica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import SnowBro.src.Datos.ConstantesEntidades;
import SnowBro.src.Datos.ConstantesVistas;
import SnowBro.src.Juego.Juego;
import SnowBro.src.Paneles.*;

public class ControladorGrafico implements ControladoraGrafica, ControladorDeVista {
    protected JFrame ventana;
    protected PanelPantallaPrincipal panelPantallaPrincipal;
    protected PanelPantallaRanking panelPantallaRanking;
    protected PanelPantallaDominio panelPantallaDominio;
    protected PanelPantallaModo panelPantallaModo;
    protected PanelPantallaJuego panelPantallaJuego;
    protected PanelPantallaFinDeJuego panelPantallaFinDeJuego;
    protected Juego miJuego;


    public ControladorGrafico(Juego miJuego){
        this.miJuego=miJuego;
        panelPantallaPrincipal = new PanelPantallaPrincipal(this);
        panelPantallaDominio= new PanelPantallaDominio(this);
        panelPantallaJuego = new PanelPantallaJuego(this);
        panelPantallaModo = new PanelPantallaModo(this);
        panelPantallaFinDeJuego= new PanelPantallaFinDeJuego(this);
       // controladorDeSonido = new ControladorDeSonido();
        configurarVentana();
        registrarOyenteVentana();
    }


    private void configurarVentana() {
        ventana = new JFrame("SnowBros TDP 14");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setSize(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }


    protected void registrarOyenteVentana() {
        addWindowKeyListener();
    }

      private void addWindowKeyListener() {
        ventana.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {

                int keyCode = event.getKeyCode();
                if (keyCode == KeyEvent.VK_A) {
                    miJuego.setDirection(ConstantesEntidades.IZQUIERDA);
                    
                } else if (keyCode == KeyEvent.VK_D) {
                    miJuego.setDirection(ConstantesEntidades.DERECHA);
                    
                } else if (keyCode == KeyEvent.VK_W) {
                    /*if (miJuego.getNivel().getJugador().getTocandoSuelo()) {
                        miJuego.getNivel().getJugador().iniciarSalto();
                    }*/
                    //controladorDeSonido.reproducirSonidoDeSalto();
                    
                } else if (keyCode== KeyEvent.VK_S) {
                    miJuego.setDirection(ConstantesEntidades.ABAJO);
                    
                }else if (keyCode == KeyEvent.VK_SPACE) {
                    miJuego.shootJugador();

                } else if(keyCode == KeyEvent.VK_ESCAPE) {
                	System.exit(0);
                } else if(keyCode == KeyEvent.VK_R) {
                	miJuego.reiniciarNivel();
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
                int keyCode = event.getKeyCode();

                if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D) {
                    miJuego.setDirection(ConstantesEntidades.QUIETO);
                }
                }
            }
        );
    }

}
