package SnowBros.src.Juego;

import SnowBros.src.Grafica.ControladorGrafico;
import SnowBros.src.Jugador.Jugador;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Timer timer;
    protected boolean reiniciarJugador;
    protected Jugador jugador;
    protected Sonido sonido;
    protected Ranking ranking;
    protected ControladorGrafico/a controladorGrafica; 
    //controladorgraficoooooo o controladorgraficaaaaaaaaaa??
    protected FabricaSprite dominio;

    public Juego(){
        //TODO implementar
    }
    public void iniciar(){
        //TODO implementar
    }
    public void reiniciarNivel(){
        //TODO implimentar
    }
    public void superarNivel(){
        //TODO implimentar
    }
    public void actualizarVida(){
        //TODO implimentar
    }
    public void actualizarPuntaje(int puntajeASumar){
        //TODO implimentar
    }
    public int getVidasJugador(){
        //TODO implimentar
    }
    public int getPuntaje(){
        //TODO implimentar
    }
    public Nivel getNivelActual(){
        //TODO implimentar
    }
    public int getTiempoActual(){
        //TODO implimentar
    }
    public Ranking getRanking(){
        //TODO implimentar
    }
    public int getModoDeJuego(){
        //TODO implimentar
    }
    public ControladorGrafica/o getControladorGrafico(){
        //observar que tenemos que mejorar los nombres
        //TODO implimentar
    }


    
    public Object getNivel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNivel'");
    }

    public void setDirection(int abajo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDirection'");
    }

    public void shootJugador() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shootJugador'");
    }
}
