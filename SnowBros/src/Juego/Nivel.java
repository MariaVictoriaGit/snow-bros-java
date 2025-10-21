package SnowBros.src.Juego;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadDinamica.Enemigos.BolaDeFuego;
import SnowBros.src.Entidades.EntidadDinamica.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadDinamica.Jugador.DisparoJugador;
import SnowBros.src.Entidades.EntidadDinamica.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadEstatica.Obstaculos.Obstaculo;
import SnowBros.src.Entidades.EntidadEstatica.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadEstatica.PowerUps.PowerUp;
import SnowBros.src.Grafica.Sprite;

public class Nivel {
    protected Sprite sprite;
    protected Jugador jugador;
    protected int numeroNivel;
    protected Silueta silueta;
    protected LinkedList<Enemigo> listaEnemigos;
    protected LinkedList<PowerUp> listaPowerUps;
    protected LinkedList<Obstaculo> listaObstaculos;
    protected LinkedList<Plataforma> listaPlataformas;
    protected LinkedList<DisparoJugador> listaDisparoJugador;

    public Nivel(Silueta silueta, int numero){
        this.silueta=silueta;
        numeroNivel=numero;
        listaEnemigos= new LinkedList<>();
        listaPowerUps= new LinkedList<>();
        listaObstaculos= new LinkedList<>();
        listaPlataformas= new LinkedList<>();
        listaDisparoJugador= new LinkedList<>();
    }

    //metodos getters
    public LinkedList<Enemigo> getEnemigos(){
        return listaEnemigos;
    }
    public LinkedList<PowerUp> getPowerUps(){
        return listaPowerUps;
    }
    public LinkedList<Obstaculo> getObstaculos(){
        return listaObstaculos;
    }
    public LinkedList<Plataforma> getPlataformas(){
        return listaPlataformas;
    }
    public LinkedList<DisparoJugador> getDisparos(){
        return listaDisparoJugador;
    }
    public int getNivelActual(){
        return numeroNivel;
    }
    //metodos de eliminar
    public void eliminarPlataforma(Entidad p){
        listaPlataformas.remove(p);
    }
    public void eliminarJugador(Entidad j){
        //TODO implementar
    }
    public void eliminarEnemigo(Entidad e){
        listaEnemigos.remove(e);
        Enemigo enemigo=(Enemigo) e;
        enemigo.getObserver().actualizar();
    }
    public void eliminarPowerUp(Entidad p){
        listaPowerUps.remove(p);
    }
    public void eliminarBolaDeFuego(Entidad b){
        listaEnemigos.remove(b);  //asumiendo que guardamos la bola de fuego en enemigos
        BolaDeFuego fuego=(BolaDeFuego) b;
        fuego.getObserver().actualizar();
    }
    public void eliminarBolaDeNieve(Entidad b){
        listaDisparoJugador.remove(b);
        DisparoJugador tiro=(DisparoJugador) b;
        tiro.getObserver().actualizar();
    }
    public void agregarJugador(Entidad j){
        jugador=(Jugador)j;
        jugador.getObserver().actualizar(jugador.getDireccion(), jugador.getEstadoAereo());
    }
    public void agregarPlataforma(Entidad p){
        listaPlataformas.linkLast(p);
    }
    public void agregarPowerUp(Entidad p){
        listaPowerUps.addLast(p);
    }
    public void agregarEnemigo(Entidad e){
        listaEnemigos.addLast(e);
        Enemigo enemigo=(Enemigo) e;
        enemigo.getObserver().actualizar();
    }
    public void agregarBolaDeNieve(Entidad b){
        listaDisparoJugador.addLast(b);
        DisparoJugador tiro=(DisparoJugador) b;
        tiro.getObserver().actualizar();
    }
    public void efectoBotellaVerde(){
        //TODO implemetar
    }
}
