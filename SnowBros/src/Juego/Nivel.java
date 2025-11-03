package SnowBros.src.Juego;

import java.util.ArrayList;
import java.util.LinkedList;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeFuego;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeNieve;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Obstaculo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Grafica.Sprite;

public class Nivel {
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
    protected LinkedList<Pared> listaParedes;

    public Nivel(Silueta silueta, int numero){
        this.silueta=silueta;
        numeroNivel=numero;
        listaEnemigos= new LinkedList<>();
        listaPowerUps= new LinkedList<>();
        listaObstaculos= new LinkedList<>();
        listaParedes= new LinkedList<>();
        listaPlataformas= new LinkedList<>();
        listaDisparoJugador= new LinkedList<>();
        listaBolaDeFuego= new LinkedList<>();
        listaBolaDeNieve= new LinkedList<>();
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
    public LinkedList<Pared> getParedes(){
        return listaParedes;
    }
    public LinkedList<Plataforma> getPlataformas(){
        return listaPlataformas;
    }
    public LinkedList<DisparoJugador> getDisparos(){
        return listaDisparoJugador;
    }
    public LinkedList<BolaDeFuego> getBolasDeFuego(){
        return listaBolaDeFuego;
    }
    public LinkedList<BolaDeNieve> getBolaDeNieves(){
        return listaBolaDeNieve;
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
         e.getObserver().actualizar();
        listaEnemigos.remove(e);
    }
    public void eliminarPowerUp(PowerUp p){
        listaPowerUps.remove(p);
    }
    public void eliminarObstaculo(Obstaculo o){
        listaObstaculos.remove(o);
    }
    public void eliminarPared(Pared p){
        listaParedes.remove(p);
    }
    public void eliminarSilueta(Silueta s){
        silueta=null;
    }
    public void eliminarBolaDeFuego(BolaDeFuego b){
        listaBolaDeFuego.remove(b); 
        b.getObserver().actualizar();
    }
    public void eliminarDisparo(DisparoJugador b){
        b.getObserver().actualizar();
        listaDisparoJugador.remove(b);
        
    }
    public void eliminarBolaDeNieve(BolaDeNieve b){
        listaBolaDeNieve.remove(b);
        b.getObserver().actualizar();
    }
    // métodos que agregan
    public void agregarJugador(Jugador j){
        jugador=j;
        j.setNivel(this);
    }
    public void agregarPlataforma(Plataforma p){
        listaPlataformas.addLast(p);
        p.setNivel(this);
    }
    public void agregarObstaculo(Obstaculo o){
        listaObstaculos.addLast(o);
        o.setNivel(this);
    }
    public void agregarPared(Pared p){
        listaParedes.addLast(p);
        p.setNivel(this);
    }
    public void agregarPowerUp(PowerUp p){
        listaPowerUps.addLast(p);
        p.setNivel(this);
    }
    public void agregarEnemigo(Enemigo e){
        listaEnemigos.addLast(e);
        e.setNivel(this);
        //enemigo.registrarObserver(new ObserverEntidad(e));
        e.getObserver().actualizar();
    }
    public void agregarDisparo(DisparoJugador b){
        listaDisparoJugador.addLast(b);
        b.setNivel(this);
    }
    public void agregarBolaDeNieve(BolaDeNieve b){
        listaBolaDeNieve.addLast(b);
        b.setNivel(this);
        b.getObserver().actualizar();
    }
    public void agregarBolaDeFuego(BolaDeFuego b){
        listaBolaDeFuego.addLast(b);
        b.setNivel(this);
        b.getObserver().actualizar();
    }
    public void efectoBotellaVerde(boolean efecto){
        if(efecto){
            for(Enemigo enemigo : new ArrayList<>(listaEnemigos)){
                enemigo.detener();
            }
        }
        else{
            for(Enemigo enemigo : new ArrayList<>(listaEnemigos)){
                enemigo.avanzar();
            }
        }
    }
    public Juego getJuego(){
        return jugador.getJuego();
    }
    public Jugador getJugador(){
        return jugador;
    }
    public Silueta getSilueta(){
        return silueta;
    }
    public void notificarObservers(){
        for(Enemigo e:new ArrayList<>(this.getEnemigos())){
            e.getObserver().actualizar(e.getDireccion(),e.getTocandoSuelo());
        }
      /*  for(DisparoJugador d:this.getDisparos()){
            d.getObserver().actualizar();
        }
        for(BolaDeNieve b:this.getBolaDeNieves()){
            b.getObserver().actualizar();
        }
        */
        for(BolaDeFuego b : new ArrayList<>(this.getBolasDeFuego())){
            if(b!=null){
                b.getObserver().actualizar(b.getDireccion(),b.getTocandoSuelo());
            }
        }
    }
}
