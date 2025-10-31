package SnowBros.src.Grafica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Paneles.*;

public class GUI implements ControladorGrafico, ControladorDeVista {
    protected JFrame ventana;
    protected PanelPantallaPrincipal panelPantallaPrincipal;
    protected PanelPantallaRanking panelPantallaRanking;
    protected PanelPantallaDominio panelPantallaDominio;
    protected PanelPantallaModo panelPantallaModo;
    protected PanelPantallaJuego panelPantallaJuego;
    protected PanelPantallaFinDelJuego panelPantallaFinDelJuego;
    protected Juego miJuego;
    protected boolean dominio=true;//TODO ...............................................................................
    
    public GUI(Juego juego){
        this.miJuego=juego;
        panelPantallaPrincipal = new PanelPantallaPrincipal(this);
        panelPantallaDominio= new PanelPantallaDominio(this);
        panelPantallaRanking = new PanelPantallaRanking(this);
        panelPantallaJuego = new PanelPantallaJuego(this);
        panelPantallaModo = new PanelPantallaModo(this);
        panelPantallaFinDelJuego= new PanelPantallaFinDelJuego(this);
       // controladorDeSonido = new ControladorDeSonido()
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
            private int direccionAnterior;
            public void keyPressed(KeyEvent event) {
                int keyCode=event.getKeyCode();
                if (keyCode==KeyEvent.VK_A) {
                    direccionAnterior=ConstantesEntidades.IZQUIERDA;
                        miJuego.getJugador().mover(ConstantesEntidades.IZQUIERDA);
                        miJuego.getJugador().getObserver().actualizar(ConstantesEntidades.IZQUIERDA, miJuego.getJugador().getEstadoAereo());
                    
                }else if(keyCode==KeyEvent.VK_D){
                    direccionAnterior=ConstantesEntidades.DERECHA;
                        miJuego.getJugador().mover(ConstantesEntidades.DERECHA);
                        miJuego.getJugador().getObserver().actualizar(ConstantesEntidades.DERECHA, miJuego.getJugador().getEstadoAereo());   
                }else if(keyCode==KeyEvent.VK_W){
                    //if (miJuego.getJugador().getTocandoSuelo()) { poner una vez el salto este perfecto
                    if(direccionAnterior==ConstantesEntidades.IZQUIERDA){
                                miJuego.getJugador().mover(ConstantesEntidades.ARRIBA_IZQUIERDA);
                                miJuego.getJugador().getObserver().actualizar(ConstantesEntidades.ARRIBA_IZQUIERDA, miJuego.getJugador().getEstadoAereo());
                    }else if(direccionAnterior==ConstantesEntidades.DERECHA){
                                miJuego.getJugador().mover(ConstantesEntidades.ARRIBA_DERECHA);
                                miJuego.getJugador().getObserver().actualizar(ConstantesEntidades.ARRIBA_DERECHA,miJuego.getJugador().getEstadoAereo());
                    }
                    //}
                    //controladorDeSonido.reproducirSonidoDeSalto();
                } else if (keyCode== KeyEvent.VK_S) {//podria ser para bajar la escalera
                    //miJuego.setDirection(ConstantesEntidades.ABAJO);
                    miJuego.getJugador().verificarSuelo();     //puesto en esta tecla para testear la caida
                    
                }else if (keyCode == KeyEvent.VK_SPACE) {
                        miJuego.shootJugador();

                } else if(keyCode == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                } else if(keyCode == KeyEvent.VK_R) {
                        miJuego.reiniciarNivel();
                }
            }
            public void keyReleased(KeyEvent event) {
                int keyCode=event.getKeyCode();
                if (keyCode==KeyEvent.VK_A || keyCode==KeyEvent.VK_D) {
                    miJuego.getJugador().mover(ConstantesEntidades.QUIETO);
                    miJuego.getJugador().getObserver().actualizar(ConstantesEntidades.QUIETO, miJuego.getJugador().getEstadoAereo());
                }
            }
        });
    }
     public void mostrarPantallaPrincipal() {
        ventana.setContentPane(panelPantallaPrincipal);
        ventana.requestFocusInWindow();
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
    public void mostrarPantallaDominio() {
        ventana.setContentPane(panelPantallaDominio);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void mostrarPantallaModo() {
        ventana.setContentPane(panelPantallaModo);
        ventana.requestFocusInWindow();
        refrescar();
    }    
    public void mostrarPantallaRanking() {
        ventana.setContentPane(panelPantallaRanking);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void accionarInicioJuego() {
        miJuego.iniciar(dominio);
        this.getPantalla().mostrarPlataformas();
        this.getPantalla().mostrarObstaculos();
        this.getPantalla().incorporarSilueta(miJuego.getNivelActual().getSilueta());
        refrescar();
        //controladorDeSonido.reproducirSonidoDeFondo();
    }
    //accionarPantallaPrincipal
    public void accionarPantallaFinDelJuego(){
        ventana.setContentPane(panelPantallaFinDelJuego);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void accionarPantallaPrincipal(){
        ventana.setContentPane(panelPantallaPrincipal);
        ventana.requestFocusInWindow();
        refrescar();
    }
    //accionarPantallaFinDeJuego
    public void accionarPantallaModoJuego() {
       ventana.setContentPane(panelPantallaModo);
       ventana.requestFocusInWindow();
       refrescar();
    }
    public void accionarPantallaDominio(){
        ventana.setContentPane(panelPantallaDominio);
        ventana.requestFocusInWindow();
        refrescar();
    } 
    public void accionarPantallaRanking(){
        ventana.setContentPane(panelPantallaRanking);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void cambiarModoJuego(int modo) {
        // ToDo
        mostrarPantallaPrincipal();
    }
    public void pasarNivel(){
        miJuego.superarNivel();
    }
    public void reiniciarNivel(){
        miJuego.reiniciarNivel();
    }
    public Observer registrarEntidad(Entidad entidad){
        Observer observerEntidad = panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
        return observerEntidad;
    }
    public void registrarEntidadEstatica(EntidadEstatica e){
        panelPantallaJuego.incorporarEntidadEstatica(e);
    }
    public ObserverJugador registrarEntidad(Jugador jugador) {
        ObserverJugador observerJugador = panelPantallaJuego.incorporarEntidadJugador(jugador);
        refrescar();
        return observerJugador;
    }
   // public Observer registrarSilueta(Entidad silueta) {
        //Observer observer_silueta = panelPantallaJuego.incorporarSilueta(silueta);
    //    refrescar();
     //   return observer_silueta;
    //}
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
    public PanelPantallaJuego getPantalla(){
        return panelPantallaJuego;
    }
    public void mostrarPantallaGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarPantallaGameOver'");
    }
}
