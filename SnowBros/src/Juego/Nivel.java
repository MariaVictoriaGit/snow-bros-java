package SnowBros.src.Juego;

import java.util.LinkedList;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeFuego;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeNieve;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Obstaculo;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Grafica.Sprite;

public class Nivel implements InterfazEliminar{
    protected Sprite sprite;
    //protected String nombreNivel;
    /*podriamos cambiar el nombre de nivel en lugar de numero, como un nombre para identificarlos desde ConstantesNiveles*/
    protected Jugador jugador;
    protected int numeroNivel;
    protected Silueta silueta;
    protected LinkedList<Enemigo> listaEnemigos;
    protected LinkedList<PowerUp> listaPowerUps;
    protected LinkedList<Obstaculo> listaObstaculos;
    protected LinkedList<Plataforma> listaPlataformas;
    protected LinkedList<DisparoJugador> listaDisparoJugador;
    protected LinkedList<BolaDeNieve> listaBolaDeNieve;
    protected LinkedList<BolaDeFuego> listaBolaDeFuego;

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
    public void eliminarPlataforma(Plataforma p){
        listaPlataformas.remove(p);
    }
    public void eliminarJugador(Jugador j){
        jugador=null;
    }
    public void eliminarEnemigo(Enemigo e){
        listaEnemigos.remove(e);
        e.getObserver().actualizar();
    }
    public void eliminarPowerUp(PowerUp p){
        listaPowerUps.remove(p);
    }
    public void eliminarObstaculo(Obstaculo o){
        listaObstaculos.remove(o);
    }
    public void eliminarSilueta(Silueta s){
        silueta=null;
    }
    public void eliminarBolaDeFuego(BolaDeFuego b){
        listaBolaDeFuego.remove(b);  //asumiendo que guardamos la bola de fuego en enemigos
        b.getObserver().actualizar();
    }
    public void eliminarDisparo(DisparoJugador b){
        listaDisparoJugador.remove(b);
        b.getObserver().actualizar();
    }
    public void eliminarBolaDeNieve(BolaDeNieve b){
        listaBolaDeNieve.remove(b);
        b.getObserver().actualizar();
    }
    public void eliminarDisparoJugador(DisparoJugador d){
        listaDisparoJugador.remove(d);
        d.getObserver().actualizar();
    }
    // métodos que agregan
    public void agregarJugador(Jugador j){
        jugador=(Jugador)j;
    }
    public void agregarPlataforma(Plataforma p){
        listaPlataformas.addLast(p);
    }
    public void agregarPowerUp(PowerUp p){
        listaPowerUps.addLast(p);
    }
    public void agregarEnemigo(Enemigo e){
        listaEnemigos.addLast(e);
        Enemigo enemigo=(Enemigo) e;
        //enemigo.registrarObserver(new ObserverEntidad(e));
        enemigo.getObserver().actualizar();
        enemigo.setNivel(this);
        enemigo.mover();
    }
    public void agregarDisparo(DisparoJugador b){
        listaDisparoJugador.addLast(b);
        DisparoJugador tiro=(DisparoJugador) b;
        tiro.getObserver().actualizar();
    }
    public void efectoBotellaVerde(){
        //TODO implemetar
    }
    public Jugador getJugador(){
        return jugador;
    }
    public Silueta getSilueta(){
        return silueta;
    }
}
