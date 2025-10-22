package SnowBros.src.Juego;
//import java.security.KeyStore.Entry;
import java.util.HashMap;

public class Ranking{
    protected HashMap<String, Integer> topClasico;
    protected HashMap<String, Integer> topSupervivencia;
    protected HashMap<String, Integer> topContratiempo;

    public Ranking(){
        topClasico= new HashMap<>();
        topSupervivencia= new HashMap<>();  
        topContratiempo= new HashMap<>();
    }
    public void setNivel(String s, int i){
        //TODO implementar
    }
    protected void ordenarTop(){
        //TODO implementar
    }
   /* public Entry<String, Integer> getTop(){
        //TODO implementar
    }*/
}