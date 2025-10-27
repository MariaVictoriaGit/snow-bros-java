package SnowBros.src.Juego;

import java.util.LinkedList;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeFuego;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.*;
import SnowBros.src.Grafica.Observer;
import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.Sprite;

public class Nivel{
    protected Sprite sprite;
    //protected String nombreNivel;
    /*podriamos cambiar el nombre de nivel en lugar de numero, como un nombre para identificarlos desde ConstantesNiveles*/
    protected Jugador jugador;
    protected int numeroNivel;
    protected Silueta silueta;
    protected LinkedList<Entidad> listaEnemigos;
    protected LinkedList<Entidad> listaPowerUps;
    protected LinkedList<Entidad> listaObstaculos;
    protected LinkedList<Entidad> listaPlataformas;
    protected LinkedList<Entidad> listaDisparoJugador;

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
    public LinkedList<Entidad> getEnemigos(){
        return listaEnemigos;
    }
    public LinkedList<Entidad> getPowerUps(){
        return listaPowerUps;
    }
    public LinkedList<Entidad> getObstaculos(){
        return listaObstaculos;
    }
    public LinkedList<Entidad> getPlataformas(){
        return listaPlataformas;
    }
    public LinkedList<Entidad> getDisparos(){
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
    }
    public void agregarPlataforma(Entidad p){
        listaPlataformas.addLast(p);
    }
    public void agregarPowerUp(Entidad p){
        listaPowerUps.addLast(p);
    }
    public void agregarEnemigo(Entidad e){
        listaEnemigos.addLast(e);
        Enemigo enemigo=(Enemigo) e;
        //enemigo.registrarObserver(new ObserverEntidad(e));
        enemigo.getObserver().actualizar();
        enemigo.mover();
    }
    public void agregarBolaDeNieve(Entidad b){
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
