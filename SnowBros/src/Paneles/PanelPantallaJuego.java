package SnowBros.src.Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
//import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Grafica.GUI;
import SnowBros.src.Grafica.Observer;
import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.ObserverJugador;

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
         //tamaño fijo de la pantalla
         setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO));
         setLayout(new BorderLayout());
         //agrega la informacion del jugador
         agregarPanelInformacion();
         //agrega el panel donde estara el juego
         agregarPanelJuego();
    }
    protected void agregarPanelInformacion() {
        //TODO
        panelInformacion= new JPanel();
        panelInformacion.setOpaque(false); //Esto hace que sea transparente
        panelInformacion.setLayout(new GridLayout(1, 4)); //UNA FILA Y 5 COLUM PARA MOSTRAR VALORES ALINEADOS
		panelInformacion.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, 50);//Defino manualmente el tamaño y pos del panel info
        //nivel
        String stringNivel="NombreNivel";
        labelNivel= new JLabel(stringNivel);
        labelNivel.setForeground(Color.BLACK);
        labelNivel.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto horizontalmente dentro del JLabel
        //PUNTAJE
        //String puntaje=String.valueOf(miControladorGrafico.getJuego().getJugador().getPuntaje());
        String puntaje=String.valueOf(0);
        labelPuntaje= new JLabel(puntaje);
        labelPuntaje.setForeground(Color.BLACK);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        //TIEMPO
        //String tiempoString=miControladorGrafico.getJuego().getTimer().stringDeTiempo();
        String tiempoString="tiempo";
        labelTiempo= new JLabel(tiempoString);
        labelTiempo.setForeground(Color.BLACK);
        labelTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        //panel para las vidas con corazones
        panelVidas= new JPanel();
        panelVidas.setOpaque(false);//transparente
        panelVidas.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));// espacio de 5 pixeles horiz y vert entre cada componente
        ImageIcon iconoCorazon= new ImageIcon("SnowBros/src/Imagenes/PowerUps/vidaJugador.png");
        int vidas=3;
        for(int i=0;i<vidas;i++){
            JLabel vidaLabel=new JLabel(iconoCorazon);
            panelVidas.add(vidaLabel);
        }
        //AGREGAMOS TODO AL PANEL INFORMACION
        panelInformacion.add(labelNivel);
        panelInformacion.add(labelPuntaje);
        panelInformacion.add(labelTiempo);
        panelInformacion.add(panelVidas);

        this.add(panelInformacion,BorderLayout.NORTH);
    }
    protected void agregarPanelJuego() {
       imagenFondoJuego= new JLabel();
       imagenFondoJuego.setLayout(null); //null para posicionar sprites manualmente
       imagenFondoJuego.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
       //Panel del Juego donde se van a agregar los objetos
       panelPantallaJuego= new JPanel();
       panelPantallaJuego.setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO));
       panelPantallaJuego.add(imagenFondoJuego);
       //voy a agregar el panel de juevo al PanelPantallaJuego
       this.add(panelPantallaJuego,BorderLayout.CENTER);
    
    }
    public void mostrarPlataformas(){
        for (Entidad plataforma:miControladorGrafico.getJuego().getNivelActual().getPlataformas()){
            String ruta = plataforma.getSprite().getRutaImagen("normal");
            ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta));//plataforma.getSprite().getRutaImagen("estatico.png"
            JLabel plataformaVisible = new JLabel(icono);
            plataformaVisible.setBounds(plataforma.getPosX(), plataforma.getPosY(), icono.getIconWidth(), icono.getIconHeight());
            this.add(plataformaVisible);
        }
    }
    //tengo que agregar metodos actualizar
    public Observer incorporarEntidad(Entidad entidad) {
        //TODO pq crear otro observer si al crear cada entidad se le crea su respectivo observer?
        ObserverEntidad observerEntidad= new ObserverEntidad(entidad);
        imagenFondoJuego.add(observerEntidad);
        return observerEntidad;
    }
    public ObserverJugador incorporarEntidadJugador(Jugador jugador) {
        ObserverJugador observerJugador= new ObserverJugador((jugador));
        imagenFondoJuego.add(observerJugador);
        actualizarInformacionJugador(jugador);
        return observerJugador;
    }
    public void incorporarSilueta(Entidad silueta) {
        ImageIcon imagenOriginal= new ImageIcon( getClass().getClassLoader().getResource(silueta.getSprite().getRutaImagen("normal")));
        Image imagenRedimensionada= imagenOriginal.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        imagenFondoJuego.setIcon(new ImageIcon(imagenRedimensionada));
        imagenFondoJuego.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        this.add(imagenFondoJuego);
        //imagenFondoJuego.setIcon(new ImageIcon(getClass().getClassLoader().getResource(silueta.getSprite().getRutaImagen("normal"))));
        //imagenFondoJuego.setBounds(0, 0, imagenFondoJuego.getIcon().getIconWidth(), imagenFondoJuego.getIcon().getIconHeight());
       // panelPantallaJuego.setPreferredSize(new Dimension(imagenFondoJuego.getIcon().getIconWidth(), imagenFondoJuego.getIcon().getIconHeight()));
        //this.add(imagenFondoJuego);
    }
    public void actualizarInformacionJugador(Jugador jugador) {
        //DESPUES
    }
    public void actualizarLabelsVidas(Jugador jugador) {
        //TODO implementar
    }   
    public void actualizarLabelsPuntaje(Jugador jugador) {
        //TODO implementar
    }
    public void actualizarLabelsTiempo(Jugador jugador) {
        //TODO implementar
    }    
    public void centrarEnJugador(int posXJugador) {
        //TODO implementar
    }   
    public void agregarLabelsEditablesTiempo(int posXJugador) {
        //TODO implementar
    }
    public void agregarLabelsEditablesPuntaje(){
        //TODO implementar
    }
    public void agregarLabelsEditablesVidas(){
        //TODO implementar
    }
    public void decorarLabelsTiempo(){
        //TODO implementar
    }
    public void decorarLabelsPuntaje(){
        //TODO implementar
    }
    public void decorarLabelsVidas(){
        //TODO implementar
    }
}
