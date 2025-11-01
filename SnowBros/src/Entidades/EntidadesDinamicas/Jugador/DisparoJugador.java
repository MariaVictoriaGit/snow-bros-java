package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class DisparoJugador extends EntidadDinamica {
    protected Temporizador tiempoEfecto;
    protected int potenciaDisparo;
    protected int velocidad;
    protected boolean efectoBotella;
    
    public DisparoJugador(int x, int y, Sprite sprite){
        super(x,y,sprite);
        //ajustar posicion inicial del disparo segun direccion del jugador
        //ajustar pos inicial para que no salga desde el pj
        //direccion= jugador.getDireccion();--->que tan necesaria es la direcion;
        velocidad=ConstantesDificultad.VELOCIDAD_DISPARO; // a definir
        potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_NORMAL; //a definir
        tiempoEfecto=new Temporizador(ConstantesTiempos.TIEMPO_DISPARO_JUGADOR);
    }
    public void setDireccion(int direccion, int dirAnterior){
        this.direccion = direccion;
        this.dirAnterior = dirAnterior;
    }
    public void mover(){
        Thread hilo = new Thread(() -> {
            while(this!=null){
                switch (direccion) {
                    case ConstantesEntidades.QUIETO:
                        if(dirAnterior==ConstantesEntidades.DERECHA){
                            posX += 4;
                            //posY += 2;
                            //incremetarY();
                            getObserver().actualizar(dirAnterior, getTocandoSuelo());
                            getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                        }
                        else if(dirAnterior==ConstantesEntidades.IZQUIERDA){
                            posX -= 4;
                            //posY += 2;
                            //incremetarY();
                            getObserver().actualizar(dirAnterior, getTocandoSuelo());
                            getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                        }
                        break;
                    case ConstantesEntidades.DERECHA:
                        posX += 4;
                        //posY += 2;
                        //incremetarY();
                        getObserver().actualizar(direccion, getTocandoSuelo());
                        getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                        break;
                    case ConstantesEntidades.IZQUIERDA:
                        posX -= 4;
                        //posY += 2;
                        //incremetarY();
                        getObserver().actualizar(direccion, getTocandoSuelo());
                        getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                        break;
                }
                try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
            }
        });
        hilo.start();
    }
    private int incremetarY(){
        return posY+1;
    }

    public void setEfectoBotellaRoja(boolean efecto){
        //TODO  revisar  timer del power ups
        efectoBotella=true;
    }
    public void activarDisparo(){ //TODO para?
        if(efectoBotella){
            potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_AUMENTADA;
        }
    }
    public void visitar(Enemigo e) {
        e.recibirDaño(potenciaDisparo);
        eliminar();
        observer=null;
    }
    public void visitar(Plataforma p){
        eliminar();
        observer=null;
    }
    public void eliminar(){
        nivel.eliminarDisparoJugador(this);
    }
}
