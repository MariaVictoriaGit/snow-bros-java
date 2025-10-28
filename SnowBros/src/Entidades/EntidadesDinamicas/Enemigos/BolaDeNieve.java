package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;
    private Temporizador tiempo;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_NIEVE);
        estaActiva=false;
    }
    public boolean getEstado(){
        return estaActiva;
    }
    public Temporizador getTimer(){
        return tiempo;
    }
    public void mover(){
        //TODO determinar movimientos
    }
    private void rebotar(){
        //TODO cada vez que choca un una pared 
    }
    public void visitar(Enemigo e){
        e.eliminar();
        //TDOO a los indestructibles se les aturde no elimina(calabaza), al fantasma ni lo toca
        rebotar();
    }
    public void eliminar(){
        miNivel.eliminarBolaDeNieve(this);
    }
}
