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
    protected int tiempoCaida;
    protected int aumentos;
    protected boolean efectoBotella;
    
    public DisparoJugador(int x, int y, Sprite sprite){
        super(x,y,sprite);
        ancho= ancho/2;
        alto= alto/2;
        hitbox.actualizarHitbox(x, y, ancho, alto);
        //ajustar posicion inicial del disparo segun direccion del jugador
        //ajustar pos inicial para que no salga desde el pj
        //direccion= jugador.getDireccion();--->que tan necesaria es la direcion;
        velocidad=ConstantesDificultad.VELOCIDAD_DISPARO; // a definir
        potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_NORMAL; //a definir
        tiempoEfecto=new Temporizador(ConstantesTiempos.TIEMPO_DISPARO_JUGADOR);
    }
    public void setDireccion(int direccion, int dirAnt){
        this.direccion = direccion;
        if(dirAnt!=ConstantesEntidades.QUIETO && dirAnt!=ConstantesEntidades.ARRIBA_DERECHA && dirAnt!=ConstantesEntidades.ARRIBA_IZQUIERDA){
            this.dirAnterior = dirAnt;
        }
    }
    public void mover(){
        switch (direccion) {
            case ConstantesEntidades.QUIETO:
                if(dirAnterior==ConstantesEntidades.DERECHA){
                    posX += 4;
                    caida();
                    if(tiempoCaida==800){
                        posY += 2;
                        aumentos++;
                        incremetarY();
                    }
                    getObserver().actualizar(dirAnterior, getTocandoSuelo());
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                }
                else {
                    posX -= 4;
                    caida();
                    if(tiempoCaida==800){
                        posY += 2;
                        aumentos++;
                        incremetarY();
                    }
                    getObserver().actualizar(dirAnterior, getTocandoSuelo());
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                }
                break;
                case ConstantesEntidades.DERECHA:
                    posX += 4;
                    caida();
                    if(tiempoCaida==800){
                        posY += 2;
                        aumentos++;
                        incremetarY();
                    }
                    getObserver().actualizar(direccion, getTocandoSuelo());
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    posX -= 4;
                    caida();
                    if(tiempoCaida==800){
                        posY += 2;
                        aumentos++;
                        incremetarY();
                    }
                    getObserver().actualizar(direccion, getTocandoSuelo());
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.ARRIBA_IZQUIERDA:
                    posX -= 4;
                    caida();
                    if(tiempoCaida==800){
                        posY += 2;
                        aumentos++;
                        incremetarY();
                    }
                    getObserver().actualizar(direccion, getTocandoSuelo());
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
                    getObserver().actualizar(direccion, getTocandoSuelo());
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
    public String generarClaveEstado(){
        String estado=null;
        switch (direccion) {
            case ConstantesEntidades.QUIETO:
                if(efectoBotella){
                    estado="disparoAumentado1";
                }
                else{
                    estado="idleIzquierda";
                }
            case ConstantesEntidades.DERECHA:
                if(efectoBotella){
                    estado="disparoAumentado1";
                }
                else{
                    estado="idleIzquierda";
                }
                break;
            case ConstantesEntidades.IZQUIERDA:
                if(efectoBotella){
                    estado="disparoAumentado1";
                }
                else{
                    estado="idleIzquierda";
                }
                break;
            case ConstantesEntidades.ARRIBA_DERECHA:
                if(efectoBotella){
                    estado="disparoAumentado1";
                }
                else{
                    estado="idleIzquierda";
                }
                break;
            case ConstantesEntidades.ARRIBA_IZQUIERDA:
                if(efectoBotella){
                    estado="disparoAumentado1";
                }
                else{
                    estado="idleIzquierda";
                }
                break;
        }
        return estado;
    }
    public void setPotenciaAumentada(boolean efectoActivo){
        efectoBotella=efectoActivo;
        if(efectoActivo){
            potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_AUMENTADA;
        }
        else{
            potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_NORMAL;
        }
    }
    public void visitar(Enemigo e) {
        e.recibirDaño(1);
    }
    public void visitar(Plataforma p){
        eliminar();
    }
    public void eliminar(){
        nivel.eliminarDisparo(this);
        nivel.getJuego().eliminarEntidadGraficamente(this);
    }
    
}