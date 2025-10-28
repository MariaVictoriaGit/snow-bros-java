package SnowBros.src.Juego;

import java.awt.Rectangle;
import java.util.List;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Fabricas.FabricaSprites;
import SnowBros.src.Grafica.GUI;
import SnowBros.src.Grafica.Observer;
//import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.ObserverJugador;
import SnowBros.src.Parser.ParserNivel;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Timer tiempo;//TODO revisar control del tiempo del juego 
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
        ranking=new Ranking();
    }
    //metodos de configuracionaa
     public void setControladorGrafica(GUI miControlador) {
        controladorGrafica=miControlador;
    }
    public void iniciar(){
        ParserNivel parseActual= new ParserNivel(true);
        nivel= parseActual.generarNivel(1);//TODO
        this.jugador=nivel.getJugador();
        this.jugador.setJuego(this);
        this.registrarObservers();
        jugador.getObserver().actualizar();
        Thread hilo = new Thread(() -> {
            while(!nivel.getEnemigos().isEmpty()){
                try {
                    Thread.sleep(100);
                    for(Entidad ent : nivel.getEnemigos()){
                        if(estaCercaEntidad(jugador, ent, 20) && jugador.getHitbox().getRectangle().intersects(ent.getHitbox().getRectangle())){
                            Enemigo enemigo = (Enemigo) ent;
                            enemigo.aceptar(jugador);
                        }
                    }
                    for(Entidad entidad : nivel.getPowerUps()){
                        if(estaCercaEntidad(jugador, entidad, 16) && jugador.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())){
                            PowerUp powerUp = (PowerUp) entidad;
                            powerUp.aceptar(jugador);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        hilo.start();

        
    }

    public void reiniciarNivel(){
        reiniciarJugador=true;
        puntajeJugador=0;
        //tiempo.setTiempoActual(0);
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
    public Timer getTimer(){
        return tiempo;
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
    	ObserverJugador observerJugador = controladorGrafica.registrarEntidad(jugador);
		jugador.registrarObserver(observerJugador);
	}	
	protected void registrarObserversEntidades(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
			Observer observer = controladorGrafica.registrarEntidad(entidad);//observerEntidad?
            EntidadDinamica e=(EntidadDinamica) entidad;
			e.registrarObserver(observer);
		}
    }
    public int getModoDeJuego(){
        return puntajeJugador;
        //TODO implementar;
    }   
    public void shootJugador() {
        jugador.disparar();
    }
    public boolean estaCercaEntidad(Entidad entidadPrincipal, Entidad entidadEvaluada, double umbralDeRango){
        Rectangle hitboxEntidadPrincipal = entidadPrincipal.getHitbox().getRectangle();
        Rectangle hitboxEntidadEvaluada = entidadEvaluada.getHitbox().getRectangle();
        double distanciaEnX = hitboxEntidadPrincipal.getCenterX() - hitboxEntidadEvaluada.getCenterX();
        double distanciaEnY = hitboxEntidadPrincipal.getCenterY() - hitboxEntidadEvaluada.getCenterY();
        double distancia = (distanciaEnX*distanciaEnX) + (distanciaEnY*distanciaEnY);
        return distancia <= umbralDeRango*umbralDeRango;
    }
    public boolean estaCercaPlataforma(Entidad entidadPrincipal, Entidad entidadEvaluada, double umbralDeRango){
        Rectangle hitboxEntidadPrincipal = entidadPrincipal.getHitbox().getRectangle();
        Rectangle hitboxEntidadEvaluada = entidadEvaluada.getHitbox().getRectangle();
        double distanciaEnX = hitboxEntidadPrincipal.getCenterX() - hitboxEntidadEvaluada.getCenterX();
        double distanciaEnY = hitboxEntidadPrincipal.getCenterY() - hitboxEntidadEvaluada.getCenterY();
        double distancia = (distanciaEnX*distanciaEnX) + (distanciaEnY*distanciaEnY);
        return distancia <= umbralDeRango*umbralDeRango;
    }
}