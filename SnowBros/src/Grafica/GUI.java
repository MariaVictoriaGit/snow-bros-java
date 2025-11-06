package SnowBros.src.Grafica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Juego.Sonido;
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
    private boolean movDerecha=false;
    private boolean movIzquierda=false;

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
                switch(keyCode){
                    case KeyEvent.VK_A:
                        direccionAnterior=ConstantesEntidades.IZQUIERDA;
                        movIzquierda=true;
                        break;
                    case KeyEvent.VK_D:
                        direccionAnterior=ConstantesEntidades.DERECHA;
                        movDerecha=true;
                        break;
                    case KeyEvent.VK_W:
                        if(!miJuego.getJugador().getSaltando() && miJuego.getJugador().getTocandoSuelo()==ConstantesEntidades.SUELO){
                            miJuego.getJugador().mover(ConstantesEntidades.ARRIBA);
                            miJuego.getJugador().setSaltando(true);
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(miJuego.getJugador().estaHabilitadoASubirEscalera()){
                            miJuego.getJugador().mover(ConstantesEntidades.ABAJO);
                            miJuego.getJugador().getObserver().actualizar(ConstantesEntidades.ABAJO, miJuego.getJugador().getTocandoSuelo());
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        miJuego.shootJugador();
                        break;
                        

                }
            }
                    //controladorDeSonido.reproducirSonidoDeSalto();
               
               //     } else if(keyCode == KeyEvent.VK_ESCAPE) {
              //           System.exit(0);
              //    } else if(keyCode == KeyEvent.VK_R) {
              //          miJuego.reiniciarNivel();
            public void keyReleased(KeyEvent event) {
                int keyCode=event.getKeyCode();
                if (keyCode==KeyEvent.VK_A || keyCode==KeyEvent.VK_D) {
                    if(keyCode==KeyEvent.VK_A){
                        movIzquierda=false;
                    }
                    else{
                        movDerecha=false;
                    }
                }
            }
        });
    }
    public void moverJugadorSegunInput(){
        if(movDerecha){
           miJuego.getJugador().mover(ConstantesEntidades.DERECHA);
        }
        else if(movIzquierda){
            miJuego.getJugador().mover(ConstantesEntidades.IZQUIERDA);
        }
        else{
            miJuego.getJugador().mover(ConstantesEntidades.QUIETO);
            movDerecha=false;
            movIzquierda=false;
        }
        if(miJuego.getJugador().getSaltando()){
           miJuego.getJugador().mover(ConstantesEntidades.ARRIBA);
            
        }
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
    public ObserverGrafico registrarEntidad(EntidadDinamica entidad){
        ObserverGrafico observerEntidad = panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
        return observerEntidad;
    }
    public ObserverPlataformaMovil registrarEntidad(PlataformaMovil entidad){
        ObserverPlataformaMovil observerPlataforma = panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
        return observerPlataforma;
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
