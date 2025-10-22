package SnowBros.src.Juego;

import java.util.List;

import javax.swing.ImageIcon;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Fabricas.FabricaSprites;
import SnowBros.src.Grafica.GUI;
import SnowBros.src.Grafica.Observer;
import SnowBros.src.Grafica.ObserverGrafico;
import SnowBros.src.Grafica.ObserverJugador;
import SnowBros.src.Parser.ParserNivel;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Timer tiempo;
    protected boolean reiniciarJugador;
    protected Jugador jugador;
    protected Sonido sonido;
    protected Ranking ranking;
    protected GUI controladorGrafica; 
    protected FabricaSprites fabricaSprites;
    protected FabricaEntidades fabricaEntidades;
    protected ParserNivel parser;

    public Juego(){
        reiniciarJugador=false;
        
        //ranking=new Ranking();
    }

    //metodos de configuracion
     public void setControladorGrafica(GUI miControlador) {
        controladorGrafica=miControlador;
    }

    public void iniciar(){
        ParserNivel parseActual= new ParserNivel(true);
        nivel= parseActual.generarNivel(1);
        this.jugador=nivel.getJugador();
        this.registrarObservers();
        

        
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
    public void actualizarPuntaje(int puntajeASumar){
        puntajeJugador+=puntajeASumar;
    }
    //metodos de obtencion(getters)
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
    public Jugador getJugador(){
        return jugador;
    }
    public void registrarObservers(){
        registrarObserverJugador();
        registrarObserversEntidades(nivel.getEnemigos());
        registrarObserversEntidades(nivel.getDisparos());

    }
    public void registrarObserverJugador(){
    	Observer observerJugador = controladorGrafica.registrarEntidad(jugador);
		jugador.registrarObserver(observerJugador);
	}
	
	
	protected void registrarObserversEntidades(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
			Observer observer = controladorGrafica.registrarEntidad(entidad);
            EntidadDinamica e=(EntidadDinamica) entidad;
			e.registrarObserver(observer);
		}
    }
    
    public int getModoDeJuego(){
        return puntajeJugador;
        //TODO implementar;
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