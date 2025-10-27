package SnowBros.src.Paneles;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Grafica.GUI;

public class PanelPantallaPrincipal extends JPanel{
    public static final long serialVersionUID=1L;//ayuda a evitar warnings
    protected GUI miControladorGrafico;
    protected JLabel imagenFondo;
    protected JButton botonIniciar;
    protected JButton botonRanking;

    public PanelPantallaPrincipal(GUI controlador){
        this.miControladorGrafico=controlador;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarBotonIniciar();
        agregarBotonRanking();
        agregarImagenFondo();
    }
    protected void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/PantallaPrincipal.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);
        imagenFondo.setOpaque(false);
    }
    protected void agregarBotonIniciar(){
        botonIniciar = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonJugar.png"));
        botonIniciar.setIcon(iconoBoton);
        botonIniciar.setBounds(0,300,iconoBoton.getIconWidth(),iconoBoton.getIconHeight());
        transparentarBoton(botonIniciar);
        registrarOyenteBotonIniciar();
        add(botonIniciar);
    }
    private void agregarBotonRanking(){
        botonRanking= new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonRanking.png"));
        botonRanking.setIcon(iconoBoton);
        botonRanking.setBounds(13,450,iconoBoton.getIconWidth(),iconoBoton.getIconHeight());
        transparentarBoton(botonRanking);
        registrarOyenteBotonRanking();
        add(botonRanking);
    }
    private void registrarOyenteBotonIniciar(){
        botonIniciar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //System.out.println("click iniciar");
                miControladorGrafico.accionarPantallaDominio();
                miControladorGrafico.mostrarPantallaDominio();
            }
        });
    }
    private void registrarOyenteBotonRanking(){
        botonRanking.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //System.out.println("click ranking");
                miControladorGrafico.accionarPantallaRanking();
                miControladorGrafico.mostrarPantallaRanking();
            }
        });
    }
    private void transparentarBoton(JButton boton){
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
    }
}
