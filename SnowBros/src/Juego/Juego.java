package SnowBros.src.Juego;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.*;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Fabricas.*;
import SnowBros.src.Grafica.*;
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
    private Temporizador tiempoAntesAparicionCalabaza;
    protected Thread hiloColision;
    protected Thread hiloGlobal;
    protected Boolean juegoEnEjecucion;
    protected String nombreJugador;
    protected String modoDeJuego;
    private long ultimoDisparo;
    protected String dominio;

    public Juego(){
        reiniciarJugador=false;
        ranking=new Ranking();
        tiempo= new Temporizador(); 
        tiempoAntesAparicionCalabaza= new Temporizador(60);
        juegoEnEjecucion=false;
        ultimoDisparo=0;
    }
    //metodos de configuracionaa
    public void setControladorGrafica(GUI miControlador) {
        controladorGrafica=miControlador;
    }
    public void iniciar(){
        if(dominio.equals("Original"))
            fabricaSprites= new Original();
        else if (dominio.equals("Alternativo"))
            fabricaSprites= new Alternativo();
        tiempo.iniciar();
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
            jugador.setNombre(nombreJugador);
        }
        else{
            //si ya existe el jugador
            if(jugador.getVidas()==0)
                reiniciarJugador=false;
            if(reiniciarJugador){
                int nivelActual=nivel.getNivelActual();
                nivel=parseActual.generarNivel(nivelActual);
            }
            else{
                jugador.setVidas();
                jugador.actualizarPuntaje(0);
                nivel= parseActual.generarNivel(1);
            }
        }
        //TODO -..................................................................................
        this.registrarObservers();
        this.crearPowerUps();
        jugador.getObserver().actualizar();
        tiempoAntesAparicionCalabaza.setAccionAlFinalizar(()->crearCalabaza());
        tiempoAntesAparicionCalabaza.iniciar();
        juegoEnEjecucion=true;
        iniciarHilos();
    }
    private void iniciarHilos(){
            hiloColision = new Thread(() -> {
                while(juegoEnEjecucion&&nivel!=null&&!nivel.getEnemigos().isEmpty()){
                    //variables locales creadas para que cuando el juego termine en mitad de ejecucion y nivel cambie a null, no siga usando el nivel nulo
                    Jugador jugadorActual=jugador;
                    Nivel nivelActual=nivel;
                    if(nivel==null||jugador==null)
                        break;
                    for(Enemigo enemigo : new ArrayList<>(nivelActual.getEnemigos())){
                        if (jugadorActual.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) {
                            enemigo.aceptar(jugador);
                        }
                        if(!nivelActual.getDisparos().isEmpty()){
                            for (DisparoJugador disparo:new ArrayList<>(nivelActual.getDisparos())){
                                if (disparo.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) {
                                    if (disparo.getObserver()!=null) 
                                        enemigo.aceptar(disparo);
                                }
                            }
                        }
                        for(Pared p: new ArrayList<>(nivelActual.getParedes())){
                            if (enemigo.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())) {
                                p.aceptar(enemigo);
                            }
                        }
                    }
                    for(Pared p: new ArrayList<>(nivelActual.getParedes())){
                        if (jugadorActual.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())) {
                            p.aceptar(jugadorActual);
                        }
                    }
                   for(PowerUp entidad : new ArrayList<>(nivelActual.getPowerUps())){
                        if (jugadorActual.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                            entidad.aceptar(jugadorActual);
                        }
                    }
                    for(Obstaculo entidad : new ArrayList<>(nivelActual.getObstaculos())){
                        if (jugadorActual.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) {
                            entidad.aceptar(jugadorActual);
                        }
                    } 
                    for(Plataforma plataforma: nivelActual.getPlataformas()){
                        if(!nivelActual.getDisparos().isEmpty()){
                            for (DisparoJugador disparo: new ArrayList<>(nivelActual.getDisparos())){
                                if (disparo.getHitbox().getRectangle().intersects(plataforma.getHitbox().getRectangle())) {
                                    plataforma.aceptar(disparo);
                                }
                            }
                            for (BolaDeFuego bomba: nivelActual.getBolasDeFuego()){
                                if (bomba.kamakichiBomba() && bomba.getHitbox().getRectangle().intersects(plataforma.getHitbox().getRectangle())) {
                                    plataforma.aceptar(bomba);
                                }
                            }
                            
                        }
                    }
                    for(PlataformaMovil movil:nivelActual.getPlataformasMovil()){
                        movil.mover();
                        if(jugadorActual.getHitbox().getRectangle().intersects(movil.getHitbox().getRectangle())) {
                            movil.aceptar(jugadorActual);
                        }
                    }
                    if(!nivelActual.getBolasDeFuego().isEmpty()){
                        for(BolaDeFuego bola: nivelActual.getBolasDeFuego()){
                            if (bola.getHitbox().getRectangle().intersects(jugadorActual.getHitbox().getRectangle())) {
                                jugadorActual.aceptar(bola);
                            }
                        }
                    }
                    if(!nivelActual.getBolasDeNieve().isEmpty()){
                        for(BolaDeNieve bola: nivelActual.getBolasDeNieve()){
                            if (bola.getHitbox().getRectangle().intersects(jugadorActual.getHitbox().getRectangle())) {
                                jugadorActual.aceptar(bola);
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
        hiloGlobal = new Thread(() -> {
            final int FPS = 60;
            final long frameTime = 1000 / FPS;
            while (juegoEnEjecucion&&nivel!=null&&!nivel.getEnemigos().isEmpty()) {
                //variables locales creadas para que cuando el juego termine en mitad de ejecucion y nivel cambie a null, no siga usando el nivel nulo
                Nivel nivelActual=nivel;
                Jugador jugadorActual=jugador;
                if(nivel==null||jugador==null)  break;
                long inicio = System.currentTimeMillis();
                controladorGrafica.moverJugadorSegunInput();
                jugador.actualizarSalto();
                if(!jugadorActual.getSaltando())
                    jugadorActual.verificarSuelo();
                for (Enemigo enemigo : nivelActual.getEnemigos()) {
                   enemigo.patronDeMovimiento();        
                   enemigo.verificarSuelo(); 
                }
                if(!nivelActual.getDisparos().isEmpty())
                    for (DisparoJugador disparo: nivelActual.getDisparos())
                        disparo.mover();
                if(!nivelActual.getBolasDeFuego().isEmpty())
                    for (BolaDeFuego bola:new ArrayList<>(nivel.getBolasDeFuego()))
                        bola.mover();
                for(BolaDeNieve bolaDeNieve:nivelActual.getBolasDeNieve()){
                    bolaDeNieve.verificarSuelo();
                    bolaDeNieve.mover();
                }
                nivelActual.notificarObservers();
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
    public void detenerJuego(){
        juegoEnEjecucion=false;
        if(hiloGlobal!=null&& Thread.currentThread() != hiloGlobal)
            hiloGlobal.interrupt();
        if(hiloColision!=null&& Thread.currentThread() != hiloColision)
            hiloColision.interrupt();
    }
    public void reiniciarNivel(){
        if(jugador.getVidas()==0){
            ranking.setJugador(jugador.getNombre(),jugador.getPuntaje(),modoDeJuego);
            detenerJuego();
            nivel=null;
            jugador=null;
            tiempo.reiniciar();
            controladorGrafica.cambiarPantallaFinDelJuego(); // gameOver
        }
        else 
            controladorGrafica.cambiarInicioJuego();
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
    public void setNombreJugador(String nombre){
        nombreJugador=nombre;
    }
    public void setDominio(String dominio){
        this.dominio=dominio;
    }
    public FabricaEntidades getFabricaEntidades(){
        return fabricaEntidades;
    }
    public void registrarObservers(){
        registrarObserverJugador();
        registrarObserversEntidades(nivel.getEnemigos());
        registrarObserversEntidades(nivel.getDisparos());
        registrarObserversPlataformas(nivel.getPlataformasMovil());
    }
    public void registrarObserverJugador(){
    	ObserverJugador observerJugador = controladorGrafica.registrarEntidad(jugador);
		jugador.registrarObserver(observerJugador);
	}	
    public void registrarObserverEntidad(EntidadDinamica entidad){
        ObserverGrafico observer = controladorGrafica.registrarEntidad(entidad);//observerEntidad?
        EntidadDinamica e=(EntidadDinamica) entidad;
		e.registrarObserver(observer);
    }
    public void registrarObserverEntidad(PlataformaMovil entidad){
        ObserverPlataformaMovil observer = controladorGrafica.registrarEntidad(entidad);//observerEntidad?
        PlataformaMovil e= entidad;
		e.registrarObserver(observer);
    }
	protected void registrarObserversEntidades(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
            EntidadDinamica e=(EntidadDinamica) entidad;
			ObserverGrafico observer = controladorGrafica.registrarEntidad(e);//observerEntidad?
			e.registrarObserver(observer);
		}
    }
    protected void registrarObserversPlataformas(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
            PlataformaMovil p=(PlataformaMovil) entidad;
			ObserverPlataformaMovil observer = controladorGrafica.registrarEntidad(p);//observerEntidad?
			p.registrarObserver(observer);
		}
    }
    public void crearPowerUps(){
        for (PowerUp e: nivel.getPowerUps()){
            e.getTiempoDeVida().iniciar(); //TODO cuando este implementada la muerte del enemigo considerar moverlo ahi
            e.setLabel(controladorGrafica.getPantalla().incorporarEntidadEstatica(e));   
        }
    }
    public void actualizarVidas(){
        controladorGrafica.getPantalla().actualizarLabelsVidas(jugador);
    }
    public void efectoVidaExtra(){
        controladorGrafica.getPantalla().efectoVidaExtra(jugador);
    }
    public void crearNuevaPowerUp(){
        EntidadEstatica e= nivel.getPowerUps().getLast();
        controladorGrafica.getPantalla().incorporarEntidadEstatica(e); 
    }
    public void eliminarPowerUp(PowerUp p){
        controladorGrafica.getPantalla().eliminarLabel(p.getLabel());   
        if(nivel!=null) 
            nivel.eliminarPowerUp(p);
    }
    public void eliminarEntidadGraficamente(EntidadDinamica d){
        controladorGrafica.getPantalla().eliminarLabel((JLabel) d.getObserver());
    }
    public void eliminarPlataformaQuebradiza(PlataformaQuebradiza p){
        nivel.eliminarPlataforma(p);
        controladorGrafica.getPantalla().eliminarLabel(p.getMiLabel());
    }
    public String getModoDeJuego(){
        return modoDeJuego;
    } 
    public void setModoDeJuego(String modo){
        this.modoDeJuego=modo;
    }  
    private void crearCalabaza(){
        Calabaza cala=fabricaEntidades.getCalabaza(320, 120);
        cala.setNivel(nivel);
        nivel.agregarEnemigo(cala);
        ObserverGrafico observer = controladorGrafica.registrarEntidad(cala);
        cala.registrarObserver(observer);  
    }
    public void crearBolaDeNieve(int posX, int posY){
        BolaDeNieve bola=fabricaEntidades.getBolaDeNieve(posX, posY);
        bola.setNivel(nivel);
        nivel.agregarBolaDeNieve(bola);
        ObserverGrafico observer=controladorGrafica.registrarEntidad(bola);
        bola.registrarObserver(observer);
    }
    public void shootJugador() {
        long tiempoHilo=System.currentTimeMillis();
        if(tiempoHilo-ultimoDisparo>=ConstantesTiempos.COOLDOWN_DISPARO){
            DisparoJugador disparo = jugador.disparar();
            controladorGrafica.getPantalla().incorporarEntidad(disparo);
            nivel.agregarDisparo(disparo);
            ultimoDisparo=tiempoHilo;
        }
    }
    public boolean estaCerca(Entidad entidadPrincipal, Entidad entidadEvaluada, double umbralDeRango){
        Rectangle hitboxEntidadPrincipal = entidadPrincipal.getHitbox().getRectangle();
        Rectangle hitboxEntidadEvaluada = entidadEvaluada.getHitbox().getRectangle();
        double distanciaEnX = hitboxEntidadPrincipal.getCenterX() - hitboxEntidadEvaluada.getCenterX();
        double distanciaEnY = hitboxEntidadPrincipal.getCenterY() - hitboxEntidadEvaluada.getCenterY();
        double distancia = (distanciaEnX*distanciaEnX) + (distanciaEnY*distanciaEnY);
        return distancia <= umbralDeRango*umbralDeRango;
    }
}
