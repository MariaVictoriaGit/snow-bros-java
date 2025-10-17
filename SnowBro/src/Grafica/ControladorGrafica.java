package SnowBro.src.Grafica;

import javax.swing.JFrame;

public class ControladorGrafica {
    protected JFrame ventana;
    protected PanelPantallaPrincipal panelPantallaPrincipal;
    protected PanelPantallaJuego panelPantallaJuego;
    protected PanelPantallaModo panelPantallaModos;
    protected PanelPantallaDominio panelPantallaDominio;
    protected PanelPantallaFinDelJuego panelPantallaFinDelJuego;
    protected PanelPantallaRanking panelPantallaRanking;
    protected Juego miJuego;

    
 
    private void configurarVentana(){
        //TODO implementar
    }
    protected void registrarOyenteVentana(){
        //TODO implementar

    }
    private void addWindowKeyListener(){
        //TODO implementar

    }
    public void mostrarPantallaInicial(){
        //TODO implementar
    }
    public void mostrarPantallaJuego(){
        //TODO implementar
    }
    public void mostrarPantallaFinDeJuego(){
        //TODO implementar
    }
    public void accionarInicioJuego(){
        //TODO implementar
    }
    public void accionarPantallaModoJuego(){
        //TODO implementar
    }
    public void accionarPantallaDominio(){
        //TODO implementar
    }
    public void cambiarModoJuego(){
        //TODO implementar
    }
    public void pasarNivel(){
        //TODO implementar
    }
    public void reiniciarNivel(){
        //TODO implementar
    }
    public Observer registrarEntidad(Entidad e){
        //TODO implementar
    }
    public Observer registrarEntidad(Jugador j){
        //TODO implementar
    }
    public Observer registrarSprite(Entidad e){
        //TODO implementar
    }
    public Juego getJuego(){
        return miJuego;
    }







}
