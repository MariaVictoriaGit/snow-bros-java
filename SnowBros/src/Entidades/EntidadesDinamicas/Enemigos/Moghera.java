package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.HitBox;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Visitor.Visitador;

public class Moghera extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;
    private int altoJefe;
    protected boolean congelado;
    private boolean bolaLanzada;

    public Moghera(int x, int y, Sprite sprite){
    super(x, y, sprite);
    ancho = 32;      // Ancho de la hitbox
    alto = 32;       // Alto de la hitbox  // Ancho real de la entidad
    altoJefe = 79;    // Alto real de la entidad
    // Ajustamos la posición Y para que la hitbox esté alineada con la parte inferior de la entidad
    // Alineamos la hitbox a la parte inferior de la entidad
    posY= posicionarHitbox(posY);
    // Creamos la hitbox con el tamaño de 32x32 y la posición ajustada
    hitbox = new HitBox(posX,posY, ancho, alto);  // Centramos la hitbox horizontalmente en la entidad
    tocandoSuelo=ConstantesEntidades.SUELO;
    salud = ConstantesDificultad.SALUD_MOGHERA;
    velocidad = ConstantesDificultad.VELOCIDAD_MOGHERA;
    alturaMax = ConstantesDificultad.ALTURA_MAX_SALTO*3;
}
    public void mover(){
        if(!detenido)
            switch (direccion) {
                case ConstantesEntidades.QUIETO:
                    lanzarFuego(); 
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        direccion = ConstantesEntidades.IZQUIERDA;
                    else
                        direccion = ConstantesEntidades.DERECHA;
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    dirAnterior=ConstantesEntidades.IZQUIERDA;
                    posX-=1;
                    this.getHitbox().actualizarHitbox(this.posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.DERECHA:
                    dirAnterior=ConstantesEntidades.DERECHA;
                    if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                        posX+=1;
                        this.getHitbox().actualizarHitbox(this.posX, posY, ancho, alto);
                    }
                    break;
                case ConstantesEntidades.ARRIBA:
                    tocandoSuelo=ConstantesEntidades.SUELO;
                    iniciarSalto();
                    break;
                case ConstantesEntidades.ABAJO:
                    this.setBajarPlataforma(true);
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
                if (ahora - tiempoEstado > 2000) { // espera 2 segundos
                    mover();
                    estado = EstadosDePatron.MOVERABAJO;
                    tiempoEstado=ahora;
                }
            break;
            case EstadosDePatron.SALTAR:
                direccion=ConstantesEntidades.ARRIBA;
                mover();
                estado=EstadosDePatron.ESPERAR;
            break;
            case EstadosDePatron.MOVERABAJO:
                direccion=ConstantesEntidades.ABAJO;
                mover();
                if(ahora-tiempoEstado>1000){
                    this.setBajarPlataforma(false);
                    estado=EstadosDePatron.SALTAR;
                    tiempoEstado=ahora;
                }
            break;
        } 
        }
    public BolaDeFuego lanzarFuego(){
        BolaDeFuego bola=null;
        if(direccion==ConstantesEntidades.QUIETO){
            int posicionXBola= posX+15;
            if(!bolaLanzada){
                bola=gestor.crearBolaDeFuego(posicionXBola, posY, ConstantesEntidades.IZQUIERDA);
                bola.setSpriteMoghera(true);
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
                    hitbox.actualizarHitbox(posX,posY,32,32);
                }else{
                    clave="quietoIzquierda";
                    hitbox.actualizarHitbox(posX, posY, 32, 32);
                }
                break;
            case ConstantesEntidades.DERECHA:
                if(congelado){
                    clave="congeladoDerecha";
                    hitbox.actualizarHitbox(posX, posY, 32, 32);
                }
                else{
                    clave="quietoDerecha";
                    hitbox.actualizarHitbox(posX, posY, 32, 32);
                }
                break;
            case ConstantesEntidades.QUIETO:
                if(congelado){
                    clave="congeladoDerecha";
                    hitbox.actualizarHitbox(posX,posY, 32, 32);
                }
                else{
                    clave="quietoDerecha";
                    hitbox.actualizarHitbox(posX, posY, 32, 32);
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
    public void setNivel(Nivel n){
        super.setNivel(n);
    }
    public int posicionarHitbox(int y){
        return (y+altoJefe-alto);
    }
    @Override
    public void seguirCaminando(Entidad e){
    if(this.getHitbox().getRectangle().intersects(e.getHitbox().getTopHitBox())){
        this.hitbox.actualizarHitbox(posX, posicionarHitbox(posY)-32, ancho, alto);
        this.setPosY(e.getPosY()-altoJefe);
    }
    }
}