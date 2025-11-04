package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.ConstantesVistas;
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
        this.eliminar();
    }
    public void mover(){
        switch(direccion){
            case ConstantesEntidades.DERECHA:
                if(!((posX+=5)>ConstantesVistas.PANEL_ANCHO-10)){
                    posX=posX+5;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                else{
                    this.eliminar();
                }
            break;
            case ConstantesEntidades.IZQUIERDA:
                if(!((posX-=5)<10)){
                    posX=posX-5;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                else{
                    this.eliminar();
                }
            break;
        }
    }
    public void setDireccion(int direccionDisparo) {
        direccion=direccionDisparo;
    }
    public void eliminar(){
        nivel.eliminarBolaDeFuego(this);
        nivel.getJuego().eliminarEntidadGraficamente(this);
     //   nivel.getJuego().eliminarBolaDeFuego(); TODO hacer que se elimine graficamente tambien
    }
    public String generarClaveEstado(){
        String clave=null;
        switch (direccion) {
            case ConstantesEntidades.IZQUIERDA:
                    clave="completa";
                    break;
            case ConstantesEntidades.DERECHA:
                    clave="derretida";
                    break;
                }
            return clave;
    }
        
}
