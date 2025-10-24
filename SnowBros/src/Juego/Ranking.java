package SnowBros.src.Juego;
//import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.LinkedList;

import SnowBros.src.Datos.ConstantesNiveles;

public class Ranking{
    protected HashMap<String, Integer> topClasico;
    protected HashMap<String, Integer> topSupervivencia;
    protected HashMap<String, Integer> topContrarreloj;

    public Ranking(){
        topClasico= new HashMap<String, Integer>();
        topSupervivencia= new HashMap<String, Integer>();  
        topContrarreloj= new HashMap<String, Integer>();
    }
    /*public void setNivel(String s, int i){
        //TODO implementar
    }*/
    public void setJugador(String nombre, int puntaje, String modo){//para agregar al respectivo modo
        if((modo==ConstantesNiveles.CLASICO1) || (modo==ConstantesNiveles.CLASICO2)){
            topClasico.put(nombre,puntaje);
        }else if((modo==ConstantesNiveles.SUPERVIVENCIA1) || (modo==ConstantesNiveles.SUPERVIVENCIA2)){
            topSupervivencia.put(nombre,puntaje);
        }else if((modo==ConstantesNiveles.SUPERVIVENCIA1) || (modo==ConstantesNiveles.SUPERVIVENCIA2)){
            topContrarreloj.put(nombre,puntaje);
        }
    }
    public void ordenarTopClasico(){
        ordenar(topClasico);
    }
    public void ordenarTopSupervivenci(){
        ordenar(topSupervivencia);
    }
    public void ordenarTopContrreloj(){
        ordenar(topSupervivencia);
    }
    private void ordenar(HashMap<String,Integer> mapeo){
        LinkedList<HashMap.Entry<String, Integer>> lista=new LinkedList<>(mapeo.entrySet());        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        mapeo.clear();
        for(HashMap.Entry<String, Integer> jugador:lista){
            mapeo.put(jugador.getKey(),jugador.getValue());
        }
    }
    /*public Entry<String, Integer> getTop(){
        //TODO implementar
    }*/
}