package SnowBros.src.Juego;
import java.util.LinkedHashMap;
//import java.security.KeyStore.Entry;
import java.util.LinkedList;
import java.util.Map;

import SnowBros.src.Datos.ConstantesNiveles;

public class Ranking{
    protected LinkedHashMap<String, Integer> topClasico;
    protected LinkedHashMap<String, Integer> topSupervivencia;
    protected LinkedHashMap<String, Integer> topContrarreloj;

    public Ranking(){
        topClasico=new LinkedHashMap<String, Integer>();
        topSupervivencia=new LinkedHashMap<String, Integer>();  
        topContrarreloj=new LinkedHashMap<String, Integer>();
    }
    /*public void setNivel(String s, int i){
        //TODO implementar
    }*/
    public void setJugador(String nombre, int puntaje, String modo){//para agregar al respectivo modo
        if((modo==ConstantesNiveles.CLASICO1) || (modo==ConstantesNiveles.CLASICO2)){
            topClasico.put(nombre,puntaje);
            ordenarTopClasico();
        }else if((modo==ConstantesNiveles.SUPERVIVENCIA1) || (modo==ConstantesNiveles.SUPERVIVENCIA2)){
            topSupervivencia.put(nombre,puntaje);
            ordenarTopSupervivencia();
        }else if((modo==ConstantesNiveles.CONTRARRELOJ1) || (modo==ConstantesNiveles.CONTRARRELOJ2)){
            topContrarreloj.put(nombre,puntaje);
            ordenarTopContrreloj();
        }
    }
    public void ordenarTopClasico(){
        ordenar(topClasico);
    }
    public void ordenarTopSupervivencia(){
        ordenar(topSupervivencia);
    }
    public void ordenarTopContrreloj(){
        ordenar(topContrarreloj);
    }
    public LinkedHashMap<String, Integer> getTopClasico(){
        return topClasico;
    }
    public LinkedHashMap<String, Integer> getTopSupervivencia(){
        return topSupervivencia;
    }
    public LinkedHashMap<String, Integer> getTopContrarreloj(){
        return topContrarreloj;
    }
    private void ordenar(LinkedHashMap<String,Integer> mapeo){
        LinkedList<Map.Entry<String, Integer>> lista=new LinkedList<>(mapeo.entrySet());
        for(int i=0;i<lista.size()-1;i++){//burbuja
            for(int j=0;j<lista.size()-i-1;j++){
                if(lista.get(j).getValue()<lista.get(j+1).getValue()){
                    Map.Entry<String,Integer> aux=lista.get(j);
                    lista.set(j,lista.get(j+1));
                    lista.set(j+1,aux);
                }
            }
        }
        while(lista.size()>5){
            lista.removeLast();
        }
        mapeo.clear();
        for(Map.Entry<String,Integer> jugador:lista){
            mapeo.put(jugador.getKey(),jugador.getValue());
        }
    }
    /*public Entry<String, Integer> getTop(){
        //TODO implementar
    }*/
}