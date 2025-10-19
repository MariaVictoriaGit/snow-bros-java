package SnowBros.src.Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Grafica.ControladorGrafica;
import SnowBros.src.Grafica.Observer;
import SnowBros.src.Juego.Entidad;
import SnowBros.src.Jugador.Jugador;

public class PanelPantallaJuego extends JPanel{
    protected JPanel panelPantallaJuego;
    protected JPanel panelInformacion;
    protected JLabel imagenFondoJuego;
    protected JLabel labelNivel;
    protected JLabel labelPuntaje;
    protected JLabel labelTiempo;
    protected JPanel panelVidas;
    protected ControladorGrafica miControladorGrafico;

    public PanelPantallaJuego(ControladorGrafica miControladorGrafico){
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
        panelInformacion= new JPanel();
        panelInformacion.setOpaque(false); //Esto hace que sea transparente
        panelInformacion.setLayout(new GridLayout(1, 4)); //UNA FILA Y 5 COLUM PARA MOSTRAR VALORES ALINEADOS
		panelInformacion.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, 50);//Defino manualmente el tamaño y pos del panel info
        //nivel
        labelNivel= new JLabel("00");
        labelNivel.setForeground(Color.WHITE);
        labelNivel.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto horizontalmente dentro del JLabel
        //PUNTAJE
        labelPuntaje= new JLabel("0000");
        labelPuntaje.setForeground(Color.WHITE);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        //TIEMPO
        labelTiempo= new JLabel("0000");
        labelTiempo.setForeground(Color.WHITE);
        labelTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        //panel para las vidas con corazones
        panelVidas= new JPanel();
        panelVidas.setOpaque(false);//transparente
        panelVidas.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));// espacio de 5 pixeles horiz y vert entre cada componente
        ImageIcon iconoCorazon= new ImageIcon("imagenes/VidasCorazon.png");
        int vidas= miControladorGrafico.getJuego().getVidasJugador();
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
       imagenFondoJuego.setBounds(0,0, ConstantesVistas.PANEL_JUEGO_ANCHO, ConstantesVistas.PANEL_ALTO);
       //Panel del Juego donde se van a agregar los objetos
       panelPantallaJuego= new JPanel();
       panelPantallaJuego.setPreferredSize(new Dimension(ConstantesVistas.PANEL_JUEGO_ANCHO,ConstantesVistas.PANEL_ALTO));
       panelPantallaJuego.add(imagenFondoJuego);
       //voy a agregar el panel de juevo al PanelPantallaJuego
       add(panelPantallaJuego,BorderLayout.CENTER);
    
    }
    //tengo que agregar metodos actualizar
    public Observer incorporarEntidad(Entidad entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incorporarEntidad'");
    }
    public Observer incorporarEntidadJugador(Jugador jugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incorporarEntidadJugador'");
    }
    public Observer incorporarSilueta(Entidad silueta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incorporarSilueta'");
    }
    public void actualizarInformacionJugador(Jugador jugador) {
        //TODO implementar
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
    public void actualizarScrollHaciaJugador(Jugador jugador) {
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
