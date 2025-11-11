package SnowBros.src.Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Obstaculo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.SueloResbaladizo;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Grafica.*;

public class PanelPantallaJuego extends JPanel{
    protected JPanel panelPantallaJuego;
    protected JPanel panelInformacion;
    protected JLabel imagenFondoJuego;
    protected JLabel labelNivel;
    protected JLabel labelPuntaje;
    protected JLabel labelTiempo;
    protected JPanel panelVidas;
    protected GUI miControladorGrafico;

    public PanelPantallaJuego(GUI miControladorGrafico){
         this.miControladorGrafico=miControladorGrafico;
         setPreferredSize(new Dimension(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO));
         setLayout(null);//         setLayout(new BorderLayout());
         agregarPanelInformacion();
         agregarPanelJuego();
    }
    protected void agregarPanelInformacion() {
        panelInformacion= new JPanel();
        panelInformacion.setOpaque(true); //true lo deja en blanco
        panelInformacion.setLayout(new GridLayout(1, 4, 20,0)); 
		panelInformacion.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, 42);//40         //Defino manualmente el tamaño y pos del panel info
        Font fuente = new Font("Arial", Font.BOLD, 16);
        String stringModo=miControladorGrafico.getJuego().getModoDeJuego();
        labelNivel= new JLabel(stringModo);
        labelNivel.setForeground(Color.BLACK);
        labelNivel.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto horizontalmente dentro del JLabel
        labelNivel.setFont(fuente);
        String puntaje=String.valueOf(0);
        labelPuntaje= new JLabel(puntaje);
        labelPuntaje.setForeground(Color.BLACK);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        labelPuntaje.setFont(fuente);
        String tiempoString;
        labelTiempo = new JLabel();
        labelTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTiempo.setFont(fuente);
        switch (miControladorGrafico.getJuego().getModoDeJuego()) {
            case "Contrarreloj":
                tiempoString=miControladorGrafico.getJuego().getTimer().getStringTiempoContrarreloj();
                labelTiempo.setText(tiempoString);
                labelTiempo.setForeground(Color.BLACK);
                Timer tiempoActualizador = new Timer(0, e -> {
                    actualizarLabelTiempoContrarreloj();
                });
                tiempoActualizador.start();
                break;
            default://si es clasico o supervivencia mimso reloj en pantalla
                tiempoString=miControladorGrafico.getJuego().getTimer().getStringDeTiempo();
                labelTiempo.setText(tiempoString);
                labelTiempo.setForeground(Color.BLACK);
                Timer actualizadorTiempo = new Timer(0, e -> {
                    actualizarLabelTiempo();
                });
                actualizadorTiempo.start();
                labelTiempo.setForeground(Color.BLACK);
                break;
            }
        panelVidas= new JPanel();
        //ArrayList<JLabel> corazones= new ArrayList<>();
        panelVidas.setOpaque(false);//transparente
        panelVidas.setLayout(new FlowLayout(FlowLayout.CENTER,5,15));// espacio de 5 pixeles horiz y vert entre cada componente
        ImageIcon iconoCorazon= new ImageIcon("SnowBros/src/Imagenes/Original/PowerUps/vidaJugador.png");
        for(int i=0;i<ConstantesDificultad.VIDAS_INICIO;i++){
            JLabel vidaLabel=new JLabel(iconoCorazon);
            //corazones.add(vidaLabel);
            panelVidas.add(vidaLabel);
        }
        panelInformacion.add(labelNivel);
        panelInformacion.add(labelPuntaje);
        panelInformacion.add(labelTiempo);
        panelInformacion.add(panelVidas);
        this.add(panelInformacion,BorderLayout.NORTH);
    }
    protected void agregarPanelJuego() {
        imagenFondoJuego= new JLabel();
        imagenFondoJuego.setLayout(null); //null para posicionar sprites manualmente
        imagenFondoJuego.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);//Panel del Juego donde se van a agregar los objetos
        panelPantallaJuego= new JPanel();
        panelPantallaJuego.setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO));
        panelPantallaJuego.add(imagenFondoJuego);//voy a agregar el panel de juevo al PanelPantallaJuego
        this.add(panelPantallaJuego,BorderLayout.CENTER);
        panelPantallaJuego.setOpaque(false);
    }
    public void mostrarPlataformas(){
        for(Plataforma plataforma:miControladorGrafico.getJuego().getNivelActual().getPlataformas()){
            String ruta = plataforma.getSprite().getRutaImagen("normal");
            ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta));//plataforma.getSprite().getRutaImagen("estatico.png"
            JLabel plataformaVisible = new JLabel(icono);
            plataformaVisible.setBounds(plataforma.getPosX(), plataforma.getPosY(), plataforma.getHitbox().getAncho(), plataforma.getHitbox().getAlto());
            this.add(plataformaVisible);  //TODO revisar, a veces se mueven las plataformas
            this.setComponentZOrder(plataformaVisible,0);
            plataforma.setMiLabel(plataformaVisible);//imagenFondoJuego.add(plataformaVisible);
        }
        this.revalidate();
        this.repaint();
    }
    public void mostrarObstaculos(){
        for(Obstaculo obstaculo:miControladorGrafico.getJuego().getNivelActual().getObstaculos()){
            String ruta = obstaculo.getSprite().getRutaImagen("normal");
            ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta));
            JLabel obstaculoVisible = new JLabel(icono);
            obstaculoVisible.setBounds(obstaculo.getPosX(), obstaculo.getPosY(), obstaculo.getHitbox().getAncho(), obstaculo.getHitbox().getAlto());
            this.add(obstaculoVisible);  
            this.setComponentZOrder(obstaculoVisible,0);
            obstaculo.setMiLabel(obstaculoVisible);  
        }
        for(SueloResbaladizo suelo:miControladorGrafico.getJuego().getNivelActual().getResbaladizos()){
            String ruta = suelo.getSprite().getRutaImagen("normal");
            ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta));
            JLabel obstaculoVisible = new JLabel(icono);
            obstaculoVisible.setBounds(suelo.getPosX(), suelo.getPosY(), suelo.getHitbox().getAncho(), suelo.getHitbox().getAlto());
            this.add(obstaculoVisible);  
            suelo.setMiLabel(obstaculoVisible);  
        this.revalidate();
        this.repaint();
    }
}
    public void incorporarEntidad(EntidadDinamica entidad) {
        imagenFondoJuego.add((JLabel) entidad.getObserver());
    }
    public ObserverPlataformaMovil incorporarEntidad(PlataformaMovil entidad) {
        PlataformaMovil aux= entidad;
        imagenFondoJuego.add((JLabel)aux.getObserver());
        return aux.getObserver();
    }
    public JLabel incorporarEntidadEstatica(EntidadEstatica e) {
        String ruta = e.getSprite().getRutaImagen("normal");
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta));//plataforma.getSprite().getRutaImagen("estatico.png"
        JLabel estatica = new JLabel(icono);
        estatica.setBounds(e.getPosX(), e.getPosY(), e.getHitbox().getAncho(), e.getHitbox().getAlto());
        this.add(estatica);
        this.setComponentZOrder(estatica, 0);
        revalidate();
        repaint();
        return estatica;
    }
    public void incorporarEntidadJugador(Jugador jugador) {
        //ObserverJugador observerJugador= new ObserverJugador((jugador));
        imagenFondoJuego.add((JLabel)jugador.getObserver());
        actualizarInformacionJugador(jugador);
    }
    public void incorporarSilueta(Entidad silueta) {
        ImageIcon imagenOriginal= new ImageIcon( getClass().getClassLoader().getResource(silueta.getSprite().getRutaImagen("normal")));
        Image imagenRedimensionada= imagenOriginal.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        imagenFondoJuego.setIcon(new ImageIcon(imagenRedimensionada));
        imagenFondoJuego.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        //imagenFondoJuego.setBounds(0,30, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO); // el 30 en y es para repetar el tamaño del panel fin
        this.add(imagenFondoJuego);
        //imagenFondoJuego.setIcon(new ImageIcon(getClass().getClassLoader().getResource(silueta.getSprite().getRutaImagen("normal"))));
        //imagenFondoJuego.setBounds(0, 0, imagenFondoJuego.getIcon().getIconWidth(), imagenFondoJuego.getIcon().getIconHeight());
       // panelPantallaJuego.setPreferredSize(new Dimension(imagenFondoJuego.getIcon().getIconWidth(), imagenFondoJuego.getIcon().getIconHeight()));
        //this.add(imagenFondoJuego);
    }
    public void actualizarInformacionJugador(Jugador jugador) {
        //DESPUES TODO
    }
    public void actualizarLabelsVidas(Jugador jugador) {
        int indice = jugador.getVidas();
        if (indice >= 0 && indice < panelVidas.getComponentCount())
            panelVidas.remove(indice);
        ImageIcon iconoVida = new ImageIcon("SnowBros/src/Imagenes/Original/PowerUps/vidaPerdida.png");
        JLabel label= new JLabel(iconoVida);
        panelVidas.add(label, jugador.getVidas());
        revalidate();
        repaint();
    }   
    public void actualizarLabelTiempo() {
        String tiempoString = miControladorGrafico.getJuego().getTimer().getStringDeTiempo();
        labelTiempo.setText(tiempoString);
    }
    public void actualizarLabelTiempoContrarreloj() {
        String tiempoString = miControladorGrafico.getJuego().getTimer().getStringTiempoContrarreloj();
        labelTiempo.setText(tiempoString);
    }
    public void efectoVidaExtra(Jugador jugador){
        int indice = jugador.getVidas() - 1;
        if (indice >= 0 && indice < panelVidas.getComponentCount()) 
            panelVidas.remove(indice);
        ImageIcon iconoVidaRoja = new ImageIcon("SnowBros/src/Imagenes/Original/PowerUps/vidaJugador.png");
        JLabel labelVida = new JLabel(iconoVidaRoja);
        panelVidas.add(labelVida, indice);
        revalidate();
        repaint();        
    }
    public void actualizarLabelsPuntaje(int puntajeASumar) {
        Component componenteActual = panelInformacion.getComponent(1);
        panelInformacion.remove(componenteActual);
        Font fuente = new Font("Arial", Font.BOLD, 16);
        String puntaje=String.valueOf(puntajeASumar);
        labelPuntaje= new JLabel(puntaje);
        labelPuntaje.setFont(fuente);
        labelPuntaje.setForeground(Color.BLACK);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelInformacion.add(labelPuntaje,1);
        revalidate();
        repaint();
    }
    public void eliminarLabel(JLabel l){
        if(l!=null){
            remove(l);
            imagenFondoJuego.remove(l);
            this.revalidate();
            this.repaint();
        }
    }
}
