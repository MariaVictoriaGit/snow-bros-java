package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;
import SnowBros.src.Datos.ConstantesEnemigos;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Grafica.Sprite;
public class DisparoJugador extends EntidadDinamica {
    protected int potenciaDisparo;
    protected int velocidad;
    protected boolean efectoBotella;
    
    public DisparoJugador(int x, int y, Sprite sprite){
        super(x,y,sprite);
        //ajustar posicion inicial del disparo segun direccion del jugador
        //ajustar pos inicial para que no salga desde el pj
        //direccion= jugador.getDireccion();--->que tan necesaria es la direcion;
        velocidad=ConstantesEnemigos.VELOCIDAD_DISPARO; // a definir
        potenciaDisparo=ConstantesEnemigos.POTENCIA_DISPARO_NORMAL; //a definir
    }
    public void setEfectoBotellaAzul(boolean efecto){
        //TODO  revisar  timer del power ups
        efectoBotella=true;
    }
    public void activarDisparo(){ //TODO para?
        if(efectoBotella){
            potenciaDisparo=ConstantesEnemigos.POTENCIA_DISPARO_AUMENTADA;
        }
    }
    public void visitar(Enemigo e) {
        e.recibirDaño(potenciaDisparo);
    }
}
