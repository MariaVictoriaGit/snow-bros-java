package SnowBros.src.Juego;

import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Fabricas.FabricaEntidades;

public class GestorEntidad {

    private Juego juego;
    private Nivel nivel;

    public GestorEntidad(Juego j){
        juego= j;
    }
    public FabricaEntidades getFabrica(){
        return juego.getFabricaEntidades();
    }
    public void agregarPowerUp(PowerUp p){
        nivel.agregarPowerUp(p);
        juego.crearPowerUp(p);
    }
    public void actualizarPuntaje(int i){
        juego.actualizarPuntaje(i);
    }
    public void crearBolaDeNieve(int posX, int posY, int puntaje){
        juego.crearBolaDeNieve(posX, posY, puntaje);
    }
    public void crearBolaDeFuego(int posX, int posY, int direccion){
        juego.crearBolaDeFuego(posX, posY, direccion);
    }
    public void crearFantasma(int posX, int posY){
        juego.crearFantasma(posX, posY);
    }
    public void setNivel(Nivel n){
        nivel=n;
    }
}
