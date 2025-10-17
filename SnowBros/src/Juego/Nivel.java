package SnowBros.src.Juego;
import java.util.LinkedList;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Jugador.Jugador;
public class Nivel {
    protected Sprite sprite;
    protected Jugador jugador;
    protected int piso;
    protected LinkedList<Entidad> listaEnemigos;
    protected LinkedList<Entidad> listaPowerUps;
    protected LinkedList<Entidad> listaObstaculos;
    protected LinkedList<Entidad> listaPlataformas;
    protected LinkedList<Entidad> listaDisparoJugador;

    public void eliminarPlataforma(Entidad p){
        //TODO implementar
    }
    public void eliminarJugador(Entidad j){
        //TODO implementar
    }
    public void eliminarEnemigo(Entidad e){
        //TODO implementar
    }
    public void eliminarPowerUp(Entidad p){
        //TODO implementar
    }
    public void eliminarBolaDeFuego(Entidad b){
        //TODO implementar
    }
    public void eliminarBolaDeNieve(Entidad b){
        //TODO implementar
    }
    public void agregarJugador(Entidad j){
        //TODO implementar
    }
    public void agregarPlataforma(Entidad p){
        //TODO impleemntar
    }
    public void agregarPowerUp(Entidad p){
        //TODO implementar
    }
    public void agregarEnemigo(Entidad e){
        //TODO implementar
    }
    public void agregarBolaDeNieve(Entidad b){
        //TODO implementar
    }
    public void efectoBotellaVerde(){
        //TODO implemetar
    }
}
