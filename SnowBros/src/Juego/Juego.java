package SnowBros.src.Juego;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Fabricas.*;
import SnowBros.src.Grafica.*;
import SnowBros.src.Parser.ParserNivel;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Temporizador tiempo;
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
        tiempoAntesAparicionCalabaza= new Temporizador(ConstantesTiempos.TIEMPO_CALABAZA);
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
        iniciarNivel();
    }
    private void iniciarHilos(){
        hiloColision = new Thread(() -> {
            while(juegoEnEjecucion&&nivel!=null){
                //variables locales creadas para que cuando el juego termine en mitad de ejecucion y nivel cambie a null, no siga usando el nivel nulo
                Jugador jugadorActual=jugador;
                Nivel nivelActual=nivel;
                if(nivel==null||jugador==null)
                    break;
                for(Enemigo enemigo : new ArrayList<>(nivelActual.getEnemigos())){
                    if (jugadorActual.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                        enemigo.aceptar(jugadorActual);
                    if(!nivelActual.getDisparos().isEmpty())
                        for (DisparoJugador disparo:new ArrayList<>(nivelActual.getDisparos()))
                            if (disparo.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                                if (disparo.getObserver()!=null) 
                                    enemigo.aceptar(disparo);
                    for(Pared p: new ArrayList<>(nivelActual.getParedes()))
                        if (enemigo.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())) 
                            p.aceptar(enemigo);
                }
                if (!nivelActual.getFantasmas().isEmpty()){
                    for(Fantasma f : nivelActual.getFantasmas()){
                        if (jugadorActual.getHitbox().getRectangle().intersects(f.getHitbox().getRectangle())) {
                            f.aceptar(jugadorActual);
                            }
                        }
                    }
                    if (nivelActual.getCalabaza()!=null){
                        if (jugadorActual.getHitbox().getRectangle().intersects(nivelActual.getCalabaza().getHitbox().getRectangle())) {
                            nivelActual.getCalabaza().aceptar(jugadorActual);
                        }
                    }
                for(Pared p: new ArrayList<>(nivelActual.getParedes())){
                    if(jugadorActual.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())){
                                p.aceptar(jugadorActual);
                            }
                    else{
                        jugadorActual.setChocoPared(false);
                        }
                }
                for(PowerUp entidad : new ArrayList<>(nivelActual.getPowerUps()))
                    if (jugadorActual.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) 
                        entidad.aceptar(jugadorActual);
                for(Obstaculo entidad : new ArrayList<>(nivelActual.getObstaculos())){
                    if (jugadorActual.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) 
                        entidad.aceptar(jugadorActual);
                    if(nivelActual.getCalabaza()!=null && nivelActual.getCalabaza().getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())){
                            entidad.aceptar(nivelActual.getCalabaza());
                        }
                    for(Enemigo e:nivelActual.getEnemigos()){
                        if(e.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())){
                            entidad.aceptar(e);
                        }
                    }
                }
                for(Plataforma plataforma: nivelActual.getPlataformas()){
                    if (jugadorActual.getHitbox().getLeftHitBox().intersects(plataforma.getHitbox().getRectangle())){
                        jugadorActual.chocaLadoPlataforma(true);
                    } 
                    if (jugadorActual.getHitbox().getRightHitBox().intersects(plataforma.getHitbox().getRectangle())){
                        jugadorActual.chocaLadoPlataforma(false);
                    }

                    if(!nivelActual.getDisparos().isEmpty()){
                        for (DisparoJugador disparo: new ArrayList<>(nivelActual.getDisparos()))
                            if (disparo.getHitbox().getRectangle().intersects(plataforma.getHitbox().getRectangle())) 
                                plataforma.aceptar(disparo);
                    }
                    if(!nivelActual.getBolasDeFuego().isEmpty()){
                        for (BolaDeFuego bomba: new ArrayList<>(nivelActual.getBolasDeFuego()))
                            if (bomba!=null && bomba.kamakichiBomba() && bomba.getHitbox().getRectangle().intersects(plataforma.getHitbox().getRectangle())) 
                            //TODO tiro excepcion por eso chequa null
                                plataforma.aceptar(bomba);
                    }
                }
                for(PlataformaMovil movil:nivelActual.getPlataformasMovil()){
                    movil.mover();
                    if(jugadorActual.getHitbox().getRectangle().intersects(movil.getHitbox().getRectangle())) 
                        movil.aceptar(jugadorActual);
                }
                if(!nivelActual.getBolasDeFuego().isEmpty())
                    for(BolaDeFuego bola: nivelActual.getBolasDeFuego())
                        if (bola.getHitbox().getRectangle().intersects(jugadorActual.getHitbox().getRectangle())) 
                            jugadorActual.aceptar(bola);

                boolean colisionBola = false;
                boolean colisionSuelo = false;
                for (BolaDeNieve bola : nivelActual.getBolasDeNieve()) {
                    for(Enemigo enemigo:nivelActual.getEnemigos())
                        if (bola.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                                enemigo.aceptar(bola);
                    if (bola.getHitbox().getLeftHitBox().intersects(jugadorActual.getHitbox().getRectangle()) ||
                        bola.getHitbox().getRightHitBox().intersects(jugadorActual.getHitbox().getRectangle())) {
                        jugadorActual.aceptar(bola);
                        colisionBola = true;
                    }
                    for(Pared p:new ArrayList<>(nivelActual.getParedes())){
                            if(bola.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())){
                                p.aceptar(bola);
                            }
                    }
                }
                for (SueloResbaladizo suelo : nivelActual.getResbaladizos()) {
                    if (jugadorActual.getHitbox().getRectangle().intersects(suelo.getHitbox().getRectangle())) {
                        suelo.aceptar(jugadorActual);
                        colisionSuelo = true;
                    }
                    for(Enemigo enemigo:nivelActual.getEnemigos())
                        if(suelo.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                            suelo.aceptar(enemigo);
                    for(BolaDeNieve bola:nivelActual.getBolasDeNieve()){
                        if(suelo.getHitbox().getRectangle().intersects(bola.getHitbox().getRectangle())) 
                            suelo.aceptar(bola);
                    }
                    if(nivel.getCalabaza()!=null && suelo.getHitbox().getRectangle().intersects(nivel.getCalabaza().getHitbox().getRectangle())) 
                        suelo.aceptar(nivel.getCalabaza());
                }
                if (!colisionBola && !colisionSuelo) {
                    jugadorActual.setVelocidadBase();
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
            while (juegoEnEjecucion&&nivel!=null) {
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
                   enemigo.actualizarSalto();
                   if(!enemigo.getSaltando())
                        enemigo.verificarSuelo(); 
                   if(enemigo.getCongelado()){
                        enemigo.actualizarTiempoCongelado();
                   }
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
                Calabaza calabaza= nivelActual.getCalabaza();
                if (calabaza!=null){
                    calabaza.patronDeMovimiento();
                    calabaza.verificarSuelo();
                }
                if (!nivelActual.getFantasmas().isEmpty()){
                    for(Fantasma fantasma : nivelActual.getFantasmas()){
                        fantasma.patronDeMovimiento();
                    }
                }
                if(nivelActual.terminoElNivel()){
                    crearMoghera();
                }
                
                nivelActual.notificarObservers();
                long duracion = System.currentTimeMillis() - inicio;
                long dormir = frameTime - duracion;
                if (dormir > 0) 
                    try {
                        Thread.sleep(dormir);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
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
            ranking.setJugador(jugador.getNombre(),puntajeJugador,modoDeJuego);
            detenerJuego();
            nivel=null;
            jugador=null;
            tiempo.reiniciar();
            tiempoAntesAparicionCalabaza.reiniciar();
            controladorGrafica.cambiarPantallaGameOver(); // gameOver
        }
        else 
            controladorGrafica.cambiarInicioJuego();
        //reiniciarJugador=true;
        //puntajeJugador=0;
        //tiempo.setTiempoActual(0);
        //TODO terminar de implementar
    }
     
    public void iniciarNivel(){
        if (nivel == null){
            nivel = parseActual.generarNivel(modoDeJuego); // o el nivel que corresponda
            //nivel.setNivel(nivel.getNivelActual()+1);
        }
        if(jugador==null){
            jugador = nivel.getJugador();
            jugador.setJuego(this);
            jugador.setNombre(nombreJugador);
        }
        else{//si ya existe el jugador
            if(jugador.getVidas()==0)
                reiniciarJugador=false;
            if(reiniciarJugador)
                nivel=parseActual.generarNivel(modoDeJuego);
            else{
                jugador.setVidas();
                jugador.actualizarPuntaje(0);
                nivel= parseActual.generarNivel(modoDeJuego);
            }
        }

        this.registrarObservers();
        jugador.getObserver().actualizar();
        tiempoAntesAparicionCalabaza.setAccionAlFinalizar(()->crearCalabaza());
        tiempoAntesAparicionCalabaza.iniciar();
        juegoEnEjecucion=true;
        iniciarHilos();

    }
    
    public void superarNivel(){
        nivel.eliminarse();
        detenerJuego();
        nivel=null;
        iniciarNivel();

    }
    public void actualizarPuntaje(int puntajeASumar){
        puntajeJugador+=puntajeASumar;
        ranking.setJugador(jugador.getNombre(), puntajeJugador, modoDeJuego);
        controladorGrafica.getPantalla().actualizarLabelsPuntaje(puntajeJugador);
    }
    public void setReinicio(boolean t){
        reiniciarJugador=t;
    }
    public GUI getControlador(){
        return controladorGrafica;
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
        registrarObserversPlataformas(nivel.getPlataformasMovil());
    }
    public void registrarObserverJugador(){
    	controladorGrafica.registrarEntidad(jugador);
	}	
    public void registrarObserverEntidad(EntidadDinamica entidad){
        controladorGrafica.registrarEntidad(entidad);//observerEntidad?
    }
	protected void registrarObserversEntidades(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
            EntidadDinamica e=(EntidadDinamica) entidad;
			controladorGrafica.registrarEntidad(e);//observerEntidad?
		}
    }
    protected void registrarObserversPlataformas(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
            PlataformaMovil p=(PlataformaMovil) entidad;
			ObserverPlataformaMovil observer = controladorGrafica.registrarEntidad(p);//observerEntidad?
			p.registrarObserver(observer);
		}
    }
    public void crearPowerUp(PowerUp p){
            p.setLabel(controladorGrafica.getPantalla().incorporarEntidadEstatica(p));   
            p.getTiempoDeVida().iniciar(); //TODO cuando este implementada la muerte del enemigo considerar moverlo ahi
    }
    public void actualizarVidas(){
        controladorGrafica.getPantalla().actualizarLabelsVidas(jugador);
    }
    public void efectoVidaExtra(){
        controladorGrafica.getPantalla().efectoVidaExtra(jugador);
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
        Calabaza cala=fabricaEntidades.getCalabaza(ConstantesEntidades.X_CALABAZA, ConstantesEntidades.Y_CALABAZA);
        controladorGrafica.registrarEntidad(cala);
        if(nivel!=null)
            nivel.agregarCalabaza(cala);
    }
    public void crearMoghera(){
        Moghera moghera=fabricaEntidades.getMoghera(ConstantesEntidades.X_MOGHERA,ConstantesEntidades.Y_MOGHERA);
        controladorGrafica.registrarEntidad(moghera);
        moghera.setActivo(true);
        nivel.agregarEnemigo(moghera);
    }
    public void crearBolaDeNieve(int posX, int posY, int puntajeEmpujado){
        BolaDeNieve bola=fabricaEntidades.getBolaDeNieve(posX, posY);
        bola.setPuntajeEmpujado(puntajeEmpujado);
        bola.setNivel(nivel);
        nivel.agregarBolaDeNieve(bola);
        controladorGrafica.registrarEntidad(bola);
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
    public void empujarBolaDeNieve(){
        if(!jugador.estaEmpujando().getEstado()){
            int puntajeASumar = jugador.estaEmpujando().disparar();
            actualizarPuntaje(puntajeASumar);
        }
    }
    public void invencibilidad(){
        Thread hiloInv = new Thread(() -> {
            long tiempoUltimoParpadeo = 0;
            while(jugador.getInvencibilidad()){
                long tiempoHilo = System.currentTimeMillis();
                if(tiempoHilo - tiempoUltimoParpadeo > ConstantesTiempos.TIEMPO_LAPSO_INVENCIBILIDAD){
                    controladorGrafica.setJugadorInvisibleGraficamente();
                    tiempoUltimoParpadeo=tiempoHilo;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }       
                controladorGrafica.setJugadorVisibleGraficamente();
            }
        });
        hiloInv.start();
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
