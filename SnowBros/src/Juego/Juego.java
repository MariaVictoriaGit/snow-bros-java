package SnowBros.src.Juego;

import SnowBros.src.Entidades.EntidadDinamica.Jugador.Jugador;
import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Fabricas.FabricaSprites;
import SnowBros.src.Grafica.ControladorGrafica;
import SnowBros.src.Grafica.ObserverJugador;
import SnowBros.src.Parser.ParserNivel;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected int vidas;
    protected Timer tiempo;
    protected boolean reiniciarJugador;
    protected Sonido sonido;
    protected Ranking ranking;
    protected ControladorGrafica controladorGrafica; 
    protected FabricaSprites fabricaSprites;
    protected FabricaEntidades fabricaEntidades;
    protected ParserNivel parser;

    public Juego(){
        vidas=3;
        reiniciarJugador=false;
        ranking=new Ranking();
    }

    //metodos de configuracion
     public void setControladorGrafica(ControladorGrafica miControlador) {
        controladorGrafica=miControlador;
    }


    public void iniciar(){
        if(vidas==0)
            reiniciarJugador=false;
            if(reiniciarJugador){
                int nivelActual= nivel.getNivelActual();
            }
    }

    public void reiniciarNivel(){
        reiniciarJugador=true;
        puntajeJugador=0;
        tiempo.setTiempoActual(0);
        //TODO terminar de implementar
    }
    public void superarNivel(){
        //TODO implimentar
        //como se maneja el signivel, por numeros? y se lo pasa al constructor?
        
    }
    //metodos de actualizacion
    public void actualizarVida(){
        //TODO implementar
    }
    public void actualizarPuntaje(int puntajeASumar){
        puntajeJugador+=puntajeASumar;
    }
    //metodos de obtencion(getters)
    public int getVidasJugador(){
        return vidas;
    }
    public int getPuntaje(){
        return puntajeJugador;
    }
    public Nivel getNivelActual(){
        return nivel;
    }
    public int getTiempoActual(){
        return tiempo.getTiempoActual();
    }
    public Ranking getRanking(){
        return ranking;
    }
    public int getModoDeJuego(){
        return puntajeJugador;
        //TODO implementar;
    }
    public ControladorGrafica getControladorGrafica(){
        //observar que tenemos que mejorar los nombres
        return controladorGrafica;
    }
   
    public void setDirection(int abajo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDirection'");
    }
    public void shootJugador() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shootJugador'");
    }
}