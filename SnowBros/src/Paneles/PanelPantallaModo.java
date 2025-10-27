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


public class PanelPantallaModo extends JPanel{
    protected JLabel imagenFondo;
    protected JButton botonClasico;
    protected JButton botonSupervivencia;
    protected JButton botonContrarreloj;
    protected GUI miControladorGrafico;

    public PanelPantallaModo(GUI miControladorGrafico){
        this.miControladorGrafico=miControladorGrafico;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonClasico();
        agregarBotonSupervivencia();
        agregarBotonContrarreloj();    
    }
    public void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/PantallaModo.png"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);    
    }
    public void agregarBotonClasico(){
        botonClasico = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonClasico.png"));
        botonClasico.setIcon(iconoBoton);
        botonClasico.setBounds(300, 96, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonClasico);
        registrarOyenteBotonClasico();
        add(botonClasico,0);
    }
    public void agregarBotonSupervivencia(){
        botonSupervivencia = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonSupervivencia.png"));
        botonSupervivencia.setIcon(iconoBoton);
        botonSupervivencia.setBounds(300, 256, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonSupervivencia);
        registrarOyenteBotonClasico();
        add(botonSupervivencia,0);
    }
    public void agregarBotonContrarreloj(){
        botonContrarreloj = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonContrarreloj.png"));
        botonContrarreloj.setIcon(iconoBoton);
        botonContrarreloj.setBounds(300, 416, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonContrarreloj);
        registrarOyenteBotonClasico();
        add(botonContrarreloj,0);
    }
    public void registrarOyenteBotonClasico(){
        botonClasico.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                //
                miControladorGrafico.accionarInicioJuego();
                miControladorGrafico.mostrarPantallaJuego();
			}
		});
    }
    public void registrarOyenteBotonContrarreloj(){
        botonContrarreloj.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
            miControladorGrafico.accionarInicioJuego();
            miControladorGrafico.mostrarPantallaJuego();
		}
		});
    }
    public void registrarOyenteBotonSupervivencia(){
        botonSupervivencia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                miControladorGrafico.accionarInicioJuego();
                miControladorGrafico.mostrarPantallaJuego();
			}
		});
    }
    public void transparentarBoton(JButton boton){
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
    }
}

