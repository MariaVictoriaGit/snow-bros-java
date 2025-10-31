package SnowBros.src.Juego;

import java.awt.Rectangle;
import java.util.List;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.DisparoJugador;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Fabricas.Alternativo;
import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Fabricas.FabricaSprites;
import SnowBros.src.Fabricas.Original;
import SnowBros.src.Grafica.GUI;
import SnowBros.src.Grafica.Observer;
//import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.ObserverJugador;
import SnowBros.src.Parser.ParserNivel;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Temporizador tiempo;//TODO revisar control del tiempo del juego 
    protected boolean reiniciarJugador;
    protected Jugador jugador;
    protected Sonido sonido;
    protected Ranking ranking;
    protected GUI controladorGrafica; 
    protected FabricaSprites fabricaSprites;
    protected FabricaEntidades fabricaEntidades;
    protected ParserNivel parseActual;

    public Juego(){
        reiniciarJugador=false;
        ranking=new Ranking();
    }
    //metodos de configuracionaa
     public void setControladorGrafica(GUI miControlador) {
        controladorGrafica=miControlador;
    }
    public void iniciar(boolean dominio){
        if(dominio){
            fabricaSprites = new Original();
        }
        else{
            fabricaSprites = new Alternativo();
        }
        fabricaEntidades = new FabricaEntidades(fabricaSprites);
        parseActual= new ParserNivel(fabricaSprites);
          // Si el nivel todavía no fue creado, crealo antes de pedir el jugador
        if (nivel == null) {
             nivel = parseActual.generarNivel(1); // o el nivel que corresponda
        }
        this.jugador=nivel.getJugador();
        this.jugador.setJuego(this);
        if(jugador.getVidas()==0)
            reiniciarJugador=false;
        if(reiniciarJugador){
            int nivelActual=nivel.getNivelActual();
            nivel=parseActual.generarNivel(nivelActual);
        } else{
            jugador.setVidas();
            jugador.setPuntaje(0);
            nivel= parseActual.generarNivel(1);
        }

        //TODO -..................................................................................
        this.registrarObservers();
        this.crearPowerUps();
        jugador.getObserver().actualizar();
        Thread hilo = new Thread(() -> {
            while(!nivel.getEnemigos().isEmpty()){
                    
                    for(Enemigo entidad : nivel.getEnemigos()){
                        if (jugador.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                        entidad.aceptar(jugador);
                        }
                    }
                    for(PowerUp entidad : nivel.getPowerUps()){
                        if (jugador.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                        entidad.aceptar(jugador);
                        }
                    }
            }
        });
        hilo.start();
    
        
    }

    public void reiniciarNivel(){
        jugador.perderVida();
        if(jugador.getVidas()==0)
            controladorGrafica.mostrarPantallaPrincipal(); //hasta hacer la gameOver
        else 
            controladorGrafica.accionarInicioJuego();
        //reiniciarJugador=true;
        //puntajeJugador=0;
        //tiempo.setTiempoActual(0);
        //TODO terminar de implementar
    }
    public void superarNivel(){

        //TODO implimentar
        //como se maneja el signivel, por numeros? y se lo pasa al constructor?
        
    }
    public void actualizarPuntaje(int puntajeASumar){
        controladorGrafica.getPantalla().actualizarLabelsPuntaje(jugador);
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
    public Temporizador getTimer(){
        return tiempo;
    }
    public Ranking getRanking(){
        return ranking;
    }
    public Jugador getJugador(){
        return jugador;
    }
    public FabricaEntidades getFabricaEntidades(){
        return fabricaEntidades;
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
    public void crearPowerUps(){
        for (PowerUp e: nivel.getPowerUps()){
        e.setLabel(controladorGrafica.getPantalla().incorporarEntidadEstatica(e));   
        }
        
    }
    public void quitarVida(){
        controladorGrafica.getPantalla().actualizarLabelsVidas(jugador);
    }
    public void crearNuevaPowerUp(){
        EntidadEstatica e= nivel.getPowerUps().getLast();
        controladorGrafica.getPantalla().incorporarEntidadEstatica(e); 
    }
    public void eliminarPowerUp(PowerUp p){
        controladorGrafica.getPantalla().eliminarLabel(p.getLabel());
    }
    public int getModoDeJuego(){
        return puntajeJugador;
        //TODO implementar;
    }   
    public void shootJugador() {
        DisparoJugador disparo = jugador.disparar();
        controladorGrafica.getPantalla().incorporarEntidad(disparo);
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