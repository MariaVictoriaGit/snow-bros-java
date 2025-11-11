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
    private int decrementos;
    private boolean caida;
    private boolean spriteJefe=false;

    public BolaDeFuego(int posX, int posY,Sprite miSprite){
        super(posX, posY, miSprite);
        observer.getImagen().setLocation(posX, posY);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_FUEGO);
        caida=false;
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
                    posX -= 6;
                    subida();
                    if(tiempoCaida<=1000 && !caida){
                        posY -= 4;
                    }
                    if(tiempoCaida==1000)
                        caida=true;
                    if(caida){
                        posY += 2;
                        decrementos++;
                        decrementarY();
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.ARRIBA_DERECHA:
                    posX += 6;
                    subida();
                    if(tiempoCaida<=1000 && !caida){
                        posY -= 4;
                    }
                    if(tiempoCaida==1000)
                        caida=true;
                    if(caida){
                        posY += 2;
                        decrementos++;
                        decrementarY();
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
        }
    }
    private void subida(){
        if(tiempoCaida<1000 && !caida){
            tiempoCaida+=100;
        }
    }
    private void decrementarY(){
        if(decrementos>=3){
            posY += 3;
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
