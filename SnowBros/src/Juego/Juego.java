package SnowBros.src.Juego;

import SnowBros.src.Jugador.Jugador;
import SnowBros.src.Fabricas.FabricaSprites;
import SnowBros.src.Grafica.ControladorGrafica;
import SnowBros.src.Grafica.ObserverJugador;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Timer tiempo;
    protected boolean reiniciarJugador;
    protected Jugador jugador;
    protected Sonido sonido;
    protected Ranking ranking;
    protected ControladorGrafica controladorGrafica; 
    protected FabricaSprites dominio;

    public Juego(ControladorGrafica controlador){
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
        ObserverJugador jugadorObserver= new ObserverJugador(jugador);
        jugador.registrarObserver(jugadorObserver);
        puntajeJugador=0;
        reiniciarJugador=false;
        if(tiempo==null){
            tiempo= new Timer();
        }
        if(controladorGrafica!=null){
            controladorGrafica.accionarInicioJuego();
            // tambien controladorGrafica.mostrarPantallaJuego(); ?
        }
    }
    public void reiniciarNivel(){
        reiniciarJugador=true;
        puntajeJugador=0;
        tiempo.setTiempoActual(0);
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
        return tiempo.getTiempoActual();
    }
    public Ranking getRanking(){
        return ranking;
    }
    public int getModoDeJuego(){
        return puntajeJugador;
        //TODO implementar;
    }
    public ControladorGrafica getControladorGrafica(){
        //observar que tenemos que mejorar los nombres
        return controladorGrafica;
    }
    public void setControladorGrafica(ControladorGrafica miControlador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setControladorGrafica'");
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