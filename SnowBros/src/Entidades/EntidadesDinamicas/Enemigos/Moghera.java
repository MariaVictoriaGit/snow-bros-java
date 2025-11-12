package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Moghera extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;
    protected boolean congelado;
    private boolean bolaLanzada;
    public Moghera(int x, int y, Sprite sprite){
        super(x, y, sprite);
        ancho=76;
        alto=79;
        salud=ConstantesDificultad.SALUD_MOGHERA;
        velocidad=ConstantesDificultad.VELOCIDAD_MOGHERA;
        alturaMax=ConstantesDificultad.ALTURA_MAX_SALTO*4;
    }
    public void mover(){
        if(!detenido)
            switch (direccion) {
                case ConstantesEntidades.QUIETO:
                    lanzarFuego(nivel.getJugador().getPosX()); 
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        direccion = ConstantesEntidades.IZQUIERDA;
                    else
                        direccion = ConstantesEntidades.DERECHA;
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    dirAnterior=ConstantesEntidades.IZQUIERDA;
                    posX-=1;
                    this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.DERECHA:
                    dirAnterior=ConstantesEntidades.DERECHA;
                    if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                        posX+=1;
                        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    }
                    break;
                case ConstantesEntidades.ARRIBA:
                    iniciarSalto();
                    break;
            } 
    }
    public void patronDeMovimiento(){
        long ahora = System.currentTimeMillis();
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                direccion = ConstantesEntidades.DERECHA;
                mover();
                bolaLanzada=false;
                if (posX > ConstantesVistas.PANEL_JUEGO_ANCHO - 100) {
                    estado = EstadosDePatron.ESPERAR;
                    tiempoEstado = ahora;
                }
                break;
    
        case EstadosDePatron.MOVERIZQUIERDA:
            direccion = ConstantesEntidades.IZQUIERDA;
            mover();
            bolaLanzada=false;
            estado = EstadosDePatron.ESPERAR;
            tiempoEstado = ahora;
            break;
        case EstadosDePatron.ESPERAR:
                direccion=ConstantesEntidades.QUIETO;
                mover();
                if (ahora - tiempoEstado > 3000) { // espera 2 segundos
                    mover();
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        estado = EstadosDePatron.MOVERDERECHA;
                    else
                        estado = EstadosDePatron.MOVERDERECHA;
                }
            break;
        case EstadosDePatron.SALTAR:
            direccion=ConstantesEntidades.ARRIBA;
            mover();
            estado=EstadosDePatron.ESPERAR;
            break;
        }
    }
    public BolaDeFuego lanzarFuego(int posXJugador){
        BolaDeFuego bola=null;
        if(direccion==ConstantesEntidades.QUIETO){
            int posicionXBola;
            if(posXJugador>this.getPosX())
                direccion=ConstantesEntidades.DERECHA;
            else
                direccion=ConstantesEntidades.IZQUIERDA;
            if (direccion==ConstantesEntidades.DERECHA) 
                posicionXBola= this.getPosX()+15;
            else
                posicionXBola=this.getPosX()-15;
            if(!bolaLanzada){
                bola=nivel.getJuego().getFabricaEntidades().getBolaDeFuego(posicionXBola, this.posY);
                bola.setDireccion(direccion);
                bola.setNivel(nivel);
                nivel.agregarBolaDeFuego(bola);
                nivel.getJuego().registrarObserverEntidad(bola);
                bolaLanzada=true;
            }

        }
        return bola;
    }
    public void setActivo(boolean t){
        activarJefe=t;
    }

    public void convertirEnBola(){
       congelado=true;
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public String generarClaveEstado(){
        String clave=null;
        switch (direccion) {
            case ConstantesEntidades.IZQUIERDA:
                if(congelado){
                    clave="congeladoIzquierda";
                    hitbox.actualizarHitbox(posX,posY,42,32);
                }else{
                    clave="idleIzquierda";
                    hitbox.actualizarHitbox(posX, posY, 76, 90);
                }
                break;
            case ConstantesEntidades.DERECHA:
                if(congelado){
                    clave="congeladoDerecha";
                    hitbox.actualizarHitbox(posX, posY, 42, 32);
                }
                else{
                    clave="idleDerecha";
                    hitbox.actualizarHitbox(posX, posY, 76, 90);
                }
                break;
            case ConstantesEntidades.QUIETO:
                if(congelado){
                    clave="congeladoDerecha";
                    hitbox.actualizarHitbox(posX, posY, 42, 32);
                }
                else{
                    clave="idleDerecha";
                    hitbox.actualizarHitbox(posX, posY, 76, 90);
                }
        }
        return clave;
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
    public int getSalud(){
        salud=ConstantesDificultad.SALUD_MOGHERA;
        return salud;
    }
}