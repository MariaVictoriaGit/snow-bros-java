package SnowBros.src.Juego;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.DisparoJugador;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Obstaculo;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
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
        if(jugador==null){
            nivel = parseActual.generarNivel(1);
            jugador = nivel.getJugador();
            jugador.setJuego(this);
        }else{
            //si ya existe el jugador
            if(jugador.getVidas()==0)
                reiniciarJugador=false;
            if(reiniciarJugador){
                int nivelActual=nivel.getNivelActual();
                nivel=parseActual.generarNivel(nivelActual);
             } else{
                jugador.setVidas();
                jugador.actualizarPuntaje(0);
                nivel= parseActual.generarNivel(1);
            }
        }
        //TODO -..................................................................................
        this.registrarObservers();
        this.crearPowerUps();
        jugador.getObserver().actualizar();
        Thread hiloColision = new Thread(() -> {
            while(!nivel.getEnemigos().isEmpty()){
                    for(Enemigo entidad : new ArrayList<>(nivel.getEnemigos())){
                        if (jugador.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                            entidad.aceptar(jugador);
                        }
                    }
                    for(PowerUp entidad : new ArrayList<>(nivel.getPowerUps())){
                        if (jugador.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                            entidad.aceptar(jugador);
                        }
                    }
                    for(Obstaculo entidad : new ArrayList<>(nivel.getObstaculos())){
                        if (jugador.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                        entidad.aceptar(jugador);
                        }
                    }
                    for(Enemigo entidad : new ArrayList<>(nivel.getEnemigos())){
                        while(!nivel.getDisparos().isEmpty()){
                            DisparoJugador disparo = nivel.getDisparos().getFirst();
                        if (disparo.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                            entidad.aceptar(disparo);
                            }
                        }
                    }
                    for(Plataforma plataforma: nivel.getPlataformas()){
                        while(!nivel.getDisparos().isEmpty()){
                            DisparoJugador disparo = nivel.getDisparos().getFirst();
                        if (disparo.getHitbox().getRectangle().intersects(plataforma.getHitbox().getRectangle())) {
                            plataforma.aceptar(disparo);
                            }
                        }
                    }
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
            }
        });
        Thread hiloGlobal = new Thread(() -> {
            final int FPS = 60;
            final long frameTime = 1000 / FPS;

            while (!nivel.getEnemigos().isEmpty()) {
                long inicio = System.currentTimeMillis();

                //jugador.moverSegunInput() por si movemos el movimiento a booleanos
                jugador.verificarSuelo();
                for (Enemigo enemigo : nivel.getEnemigos()) {
                   enemigo.patronDeMovimiento();        
                   enemigo.verificarSuelo(); 
                }
                SwingUtilities.invokeLater(() -> {
                    nivel.notificarObservers();
                });

                long duracion = System.currentTimeMillis() - inicio;
                long dormir = frameTime - duracion;
                if (dormir > 0) {
                    try {
                        Thread.sleep(dormir);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }

        });
        hiloGlobal.start();
        hiloColision.start();

    }

    public void reiniciarNivel(){
        if(jugador.getVidas()==0)
            controladorGrafica.accionarPantallaFinDelJuego(); //hasta hacer la gameOver
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
        puntajeJugador+=puntajeASumar;
        controladorGrafica.getPantalla().actualizarLabelsPuntaje(jugador);
    }
    public void setReinicio(boolean t){
        reiniciarJugador=t;
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
        nivel.eliminarPowerUp(p);
    }
    public int getModoDeJuego(){
        return puntajeJugador;
        //TODO implementar;
    }   
    public void shootJugador() {
        DisparoJugador disparo = jugador.disparar();
        controladorGrafica.getPantalla().incorporarEntidad(disparo);
        nivel.agregarDisparo(disparo);
    }
    public boolean estaCercaEntidad(Entidad entidadPrincipal, Entidad entidadEvaluada, double umbralDeRango){
        Rectangle hitboxEntidadPrincipal = entidadPrincipal.getHitbox().getRectangle();
        Rectangle hitboxEntidadEvaluada = entidadEvaluada.getHitbox().getRectangle();
        double distanciaEnX = hitboxEntidadPrincipal.getCenterX() - hitboxEntidadEvaluada.getCenterX();
        double distanciaEnY = hitboxEntidadPrincipal.getCenterY() - hitboxEntidadEvaluada.getCenterY();
        double distancia = (distanciaEnX*distanciaEnX) + (distanciaEnY*distanciaEnY);
        return distancia <= umbralDeRango*umbralDeRango;
    }

}