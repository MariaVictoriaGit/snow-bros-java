package SnowBros.src.Juego;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
    private static Sonido instancia;
    private Map<String, Clip> mapeo;

    private Sonido(){
        mapeo=new HashMap<String,Clip>();
    }
    public static Sonido getInstancia(){
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
            clip.setFramePosition(0);
            clip.start();
        }
        else{
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    private Clip cargarSonido(String r){
        try {
            File file = new File("SnowBros/src/Sonidos/" + r);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
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
