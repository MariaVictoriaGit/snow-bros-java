package SnowBros.src.Juego;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
    private Sonido instancia;
    private Map<String, Clip> mapeo;

    private Sonido(){
        mapeo = new HashMap<String,Clip>();
    }
    public Sonido getInstancia(){
        if(instancia == null){
            instancia = new Sonido();
        }
        return instancia;
    }
    public void reproducir(String s){
        Clip clip = mapeo.get(s);
        if(clip == null){
            clip = cargarSonido(s);
            mapeo.put(s,clip);
        }
        else{
            clip.setFramePosition(0);
            clip.start();
        }
    }
    private Clip cargarSonido(String r){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(r));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            return clip;
        } catch (Exception e) {
            System.err.println("Error al cargar el sonido: " + r);
            e.printStackTrace();
            return null;
        } 
    }
}
