package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class BolaDeFuego extends EntidadDinamica{
    protected Temporizador tiempo;
    private int tiempoCaida;
    private int aumentos;
    private boolean spriteJefe=false;

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
            case ConstantesEntidades.ARRIBA_IZQUIERDA:
                    posX -= 4;
                    caida();
                    if(tiempoCaida==800){
                        posY += 2;
                        aumentos++;
                        incremetarY();
                    }
                    else{
                        posY-=20;
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.ARRIBA_DERECHA:
                    posX += 4;
                    caida();
                    if(tiempoCaida==800){
                        posY += 2;
                        aumentos++;
                        incremetarY();
                    }
                    else{
                        posY-=20;
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
        }
    }
    private void caida(){
        if(tiempoCaida<800){
            tiempoCaida+=100;
        }
    }
    public void incremetarY(){
        if(aumentos>=3){
            posY += 6;
        }
    }
    public void setDireccion(int direccionDisparo) {
        direccion=direccionDisparo;
    }
    public void eliminar(){
        nivel.eliminarBolaDeFuego(this);
        nivel.getJuego().eliminarEntidadGraficamente(this);
    }
    public void visitar(Plataforma p){
        eliminar();
    }
    public boolean kamakichiBomba(){
        return direccion==ConstantesEntidades.ARRIBA_DERECHA || direccion==ConstantesEntidades.ARRIBA_IZQUIERDA;
    }
    public String generarClaveEstado(){
        String clave=null;
        switch (direccion) {
            case ConstantesEntidades.IZQUIERDA:
                if(spriteJefe){
                    clave="fuegoMoghera";
                }
                else{   
                    clave="completa";
                }
                    break;
            case ConstantesEntidades.DERECHA:
                if(spriteJefe){
                    clave="bomba";
                }
                else{

                    clave="derretida";
                }
                    break;
                }
            return clave;
    }
    public void setSpriteJefe(boolean b){
        spriteJefe=b;
    }
        
}
