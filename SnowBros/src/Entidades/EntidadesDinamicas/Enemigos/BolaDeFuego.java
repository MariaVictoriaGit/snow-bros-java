package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class BolaDeFuego extends EntidadDinamica{
    protected Temporizador tiempo;
    public BolaDeFuego(int posX, int posY,Sprite miSprite){
        super(posX, posY, miSprite);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_FUEGO);
    }
    public Temporizador getTimer(){
        return tiempo;
    }
    public void visitar(Jugador jugador){
        jugador.perderVida();
    }
    public void mover(){
        //TODO determinar movimientos
    }
    public void setDireccion(int direccionDisparo) {
        direccion=direccionDisparo;
    }
    public void eliminar(){
        miNivel.eliminarBolaDeFuego(this);
    }
}
