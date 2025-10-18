package SnowBros.src.Juego;

import SnowBros.src.Grafica.ControladorGrafico;
import SnowBros.src.Jugador.Jugador;
import SnowBros.src.Fabricas.FabricaSprite;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Timer timer;
    protected boolean reiniciarJugador;
    protected Jugador jugador;
    protected Sonido sonido;
    protected Ranking ranking;
    protected ControladorGrafico controladorGrafica; 
    //controladorgraficoooooo o controladorgraficaaaaaaaaaa??
    protected FabricaSprite dominio;

    public Juego(ControladorGrafico controlador){
        //dominio se define?
        ranking=new Ranking();
        controladorGrafica= controlador; 
    }
    public void iniciar(){
        if(nivel==null){
            nivel= new Nivel();
        }
        jugador= new Jugador();
        nivel.agregarJugador(jugador);
        puntajeJugador=0;
        reiniciarJugador=false;
        if(timer==null){
            timer= new Timer();
        }
        if(controladorGrafica!=null){
            controladorGrafica.accionarInicioJuego();
            // tambien controladorGrafica.mostrarPantallaJuego(); ?
        }

    }
    public void reiniciarNivel(){
        reiniciarJugador=true;
        puntajeJugador=0;
        timer.setTiempoActual(0);
        //TODO terminar de implementar
    }
    public void superarNivel(){
        //TODO implimentar
        //como se maneja el signivel, por numeros? y se lo pasa al constructor?
        
    }
    public void actualizarVida(){
        //TODO implementar
    }
    public void actualizarPuntaje(int puntajeASumar){
        puntajeJugador+=puntajeASumar;
    }
    public int getVidasJugador(){
        return jugador.getVidas();
    }
    public int getPuntaje(){
        return puntajeJugador;
    }
    public Nivel getNivelActual(){
        return nivel;
    }
    public int getTiempoActual(){
        return timer.getTiempoActual();
    }
    public Ranking getRanking(){
        return ranking;
    }
    public int getModoDeJuego(){
        //TODO implementar;
    }
    public ControladorGrafica/o getControladorGrafico(){
        //observar que tenemos que mejorar los nombres
        return controladorGrafica;
    }
}