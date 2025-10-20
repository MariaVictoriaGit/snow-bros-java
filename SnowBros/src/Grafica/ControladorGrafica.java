package SnowBros.src.Grafica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadDinamica.Jugador.Jugador;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Paneles.*;

public class ControladorGrafica implements ControladorGrafico, ControladorDeVista {
    protected JFrame ventana;
    protected PanelPantallaPrincipal panelPantallaPrincipal;
    protected PanelPantallaRanking panelPantallaRanking;
    protected PanelPantallaDominio panelPantallaDominio;
    protected PanelPantallaModo panelPantallaModo;
    protected PanelPantallaJuego panelPantallaJuego;
    protected PanelPantallaFinDelJuego panelPantallaFinDelJuego;
    protected Juego miJuego;
    
    public ControladorGrafica(Juego miJuego){
        this.miJuego=miJuego;
        panelPantallaPrincipal = new PanelPantallaPrincipal(this);
        panelPantallaDominio= new PanelPantallaDominio(this);
        panelPantallaJuego = new PanelPantallaJuego(this);
        panelPantallaModo = new PanelPantallaModo(this);
        panelPantallaFinDelJuego= new PanelPantallaFinDelJuego(this);
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
     public void mostrarPantallaInicial() {
        ventana.setContentPane(panelPantallaPrincipal);
        refrescar();
    }
    public void mostrarPantallaJuego() {
        ventana.setContentPane(panelPantallaJuego);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void mostrarPantallaFinDelJuego() {
        ventana.setContentPane(panelPantallaFinDelJuego);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void accionarInicioJuego() {
        this.panelPantallaJuego = new PanelPantallaJuego(this);
        miJuego.iniciar();
        //controladorDeSonido.reproducirSonidoDeFondo();
    }
    public void accionarPantallaModoJuego() {
       this.panelPantallaModo = new PanelPantallaModo(this);
       ventana.setContentPane(panelPantallaModo);
       ventana.requestFocusInWindow();
       refrescar();
    }
    public void accionarPantallaDominio(){
        this.panelPantallaDominio= new PanelPantallaDominio(this);
        ventana.setContentPane(panelPantallaDominio);
        ventana.requestFocusInWindow();
        refrescar();
    } 
    public void accionarPantallaRanking(){
        //despues
    }
    public void cambiarModoJuego(int modo) {
        // ToDo
        mostrarPantallaInicial();
    }
    public void pasarNivel(){
        miJuego.superarNivel();
    }
    public void reiniciarNivel(){
        miJuego.reiniciarNivel();
    }
    public Observer registrarEntidad(Entidad entidad) {
        Observer observerEntidad = panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
        return observerEntidad;
    }
    public Observer registrarEntidad(Jugador jugador) {
        Observer observerJugador = panelPantallaJuego.incorporarEntidadJugador(jugador);
        refrescar();
        return observerJugador;
    }
    public Observer registrarSilueta(Entidad silueta) {
        Observer observer_silueta = panelPantallaJuego.incorporarSilueta(silueta);
        refrescar();
        return observer_silueta;
    }
    public void registrarControladorJuego(Juego juego){
        this.miJuego=juego;
    }
   /* public Observer registrarSprite(Entidad e){
        return null;
        
    }*/
    public void refrescar() {
        ventana.revalidate();
        ventana.repaint();
    }
   /* public ControladorDeSonido getControladorDeSonido() {
        return controladorDeSonido;
    }*/
    public Juego getJuego() {
        return miJuego;
    }
    @Override
    public void mostrarPantallaFinJuego() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarPantallaFinJuego'");
    }
}
