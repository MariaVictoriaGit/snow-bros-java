package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import java.util.Random;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;
    private Temporizador tiempo;
    private int velocidadMaxima=8;
    private boolean reboto;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_NIEVE);
        estaActiva=false;
        reboto=false;
        velocidad=ConstantesDificultad.VELOCIDAD_BOLADENIEVE;
    }
    public boolean getEstado(){
        return estaActiva;
    }
    public Temporizador getTimer(){
        return tiempo;
    }
    public void mover(){
        if(estaActiva){
            switch(direccion){
                case ConstantesEntidades.DERECHA:
                posX=posX+velocidadMaxima;
                this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                if(!reboto && posX>ConstantesVistas.PANEL_ANCHO-20){
                        direccion=ConstantesEntidades.IZQUIERDA;
                        reboto=true;
                }

                break;
                case ConstantesEntidades.IZQUIERDA:
                    posX=posX-velocidad;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                    if(!reboto && posX<20){
                    direccion=ConstantesEntidades.DERECHA;
                    reboto=true;
                    }
                break;
            }
            terminaRebotar();
        }
    }
    private void terminaRebotar(){
        if(reboto && posX<=0 || posX>=ConstantesVistas.PANEL_ANCHO)
            this.eliminar(); 
    }
    public void visitar(Enemigo e){
        aparecerPowerUp(e);
        e.eliminar();
        this.eliminar();
        
    }
    private void aparecerPowerUp(Enemigo e){
        Random random=new Random();
        int probabilidad= random.nextInt(100);
        if(probabilidad<50){
            PowerUp p=nivel.getJuego().getFabricaEntidades().getFruta(this.getPosX(), this.getPosY());
            nivel.agregarPowerUp(p);
            nivel.getJuego().crearPowerUp(p);
        }
        else if(probabilidad<65){
            PowerUp p=nivel.getJuego().getFabricaEntidades().getBotellaAzul(this.getPosX(), this.getPosY());
            nivel.agregarPowerUp(p);
            nivel.getJuego().crearPowerUp(p);

        }
        else if(probabilidad<80){
            PowerUp p=nivel.getJuego().getFabricaEntidades().getBotellaRoja(this.getPosX(), this.getPosY());
            nivel.agregarPowerUp(p);
            nivel.getJuego().crearPowerUp(p);

        }
        else if(probabilidad<95){
            PowerUp p=nivel.getJuego().getFabricaEntidades().getBotellaVerde(this.getPosX(), this.getPosY());
            nivel.agregarPowerUp(p);
            nivel.getJuego().crearPowerUp(p);

        }
        else{
            PowerUp p=nivel.getJuego().getFabricaEntidades().getVidaExtra(this.getPosX(), this.getPosY());
            nivel.agregarPowerUp(p);
            nivel.getJuego().crearPowerUp(p);

        }

    }
    public void visitar(ParedDestructible p){
        //TODO creo q las paredes se destruian con bolas de nieve
        p.destruirse();
    }
    public void eliminar(){
        if(observer!=null){
            observer.borrarObserver();
            nivel.eliminarBolaDeNieve(this);
            observer=null;
        }
        
    }
    public void visitar(Jugador j){
        /* 
        direccion=j.getDireccion();
        empujar();
        j.setVelocidad(velocidad);
        */
          
        // Detectar desde qué lado el jugador empuja
        if (j.getPosX() < this.posX) {
            // El jugador está a la izquierda → empuja hacia la derecha
            direccion = ConstantesEntidades.DERECHA;
        } else {
            // El jugador está a la derecha → empuja hacia la izquierda
            direccion = ConstantesEntidades.IZQUIERDA;
        }

        empujar();
        j.setVelocidad(velocidad);
    }
    
    public void empujar(){
        switch(direccion){
                case ConstantesEntidades.DERECHA:
                    if(!(posX>ConstantesVistas.PANEL_ANCHO-5)){
                    posX=posX+velocidad;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
                case ConstantesEntidades.IZQUIERDA:
                if(posX>-5){
                    posX=posX-velocidad;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            }
    }
}
