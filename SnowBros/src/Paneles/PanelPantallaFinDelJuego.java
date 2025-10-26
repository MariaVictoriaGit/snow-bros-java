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

public class PanelPantallaFinDelJuego extends JPanel {
    protected JLabel imagenFondo;
    protected JButton botonReinicio;
    protected JButton botonSalir;
    protected JButton botonNuevaPartida;
    protected GUI miControladorGrafico;
    
    public PanelPantallaFinDelJuego(GUI miControladorGrafico){
        this.miControladorGrafico=miControladorGrafico;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonReinicio();
        agregarBotonSalir();
        agregarBotonNuevaPartida();
    }
    public void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/PantallaPrincipal.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);
    }
    public void agregarBotonReinicio(){
        botonReinicio = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonJugar.png"));
        botonReinicio.setIcon(iconoBoton);
        botonReinicio.setBounds(100, 350, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonReinicio);
        registrarOyenteBotonReinicio();
        add(botonReinicio,0);
    }
    public void agregarBotonSalir(){
        botonSalir = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonSalir.png"));
        botonSalir.setIcon(iconoBoton);
        botonSalir.setBounds(100, 350, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonSalir);
        registrarOyenteBotonSalir();
        add(botonSalir,0);
    }
    public void agregarBotonNuevaPartida(){
        botonNuevaPartida = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonJugar.png"));
        botonNuevaPartida.setIcon(iconoBoton);
        botonNuevaPartida.setBounds(100, 350, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonSalir);
        registrarOyenteBotonNuevaPartida();
        add(botonNuevaPartida,0);
    }
    public void registrarOyenteBotonReinicio(){
        botonReinicio.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                miControladorGrafico.accionarInicioJuego();
                miControladorGrafico.mostrarPantallaJuego();
            }
        });
    }
    public void registrarOyenteBotonSalir(){
        botonSalir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                miControladorGrafico.accionarPantallaPrincipal();
                miControladorGrafico.mostrarPantallaPrincipal();
            }
        });
    }
    private void registrarOyenteBotonNuevaPartida(){
        botonNuevaPartida.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                miControladorGrafico.accionarInicioJuego();
                miControladorGrafico.mostrarPantallaJuego();
            }
        });
    }    
    private void transparentarBoton(JButton boton){
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
    }
}