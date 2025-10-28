package SnowBros.src.Juego;

import SnowBros.src.Entidades.Entidad;

public interface ListaEliminables {

    public void eliminarPlataforma(Entidad p);
    public void eliminarJugador(Entidad j);
    public void eliminarEnemigo(Entidad e);
    public void eliminarPowerUp(Entidad p);
    public void eliminarBolaDeFuego(Entidad b);
    public void eliminarBolaDeNieve(Entidad b);
    
}
