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
        agregarBotonContrarreloj();    }
    public void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/PantallaPrincipal.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);    
    }
    public void agregarBotonClasico(){
        botonClasico = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonJugar.png"));
        botonClasico.setIcon(iconoBoton);
        botonClasico.setBounds(100, 350, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonClasico);
        registrarOyenteBotonClasico();
        add(botonClasico,0);
    }
    public void agregarBotonSupervivencia(){
        botonSupervivencia = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonJugar.png"));
        botonSupervivencia.setIcon(iconoBoton);
        botonSupervivencia.setBounds(100, 350, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonSupervivencia);
        registrarOyenteBotonClasico();
        add(botonSupervivencia,0);
    }
    public void agregarBotonContrarreloj(){
        botonContrarreloj = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/botonJugar.png"));
        botonContrarreloj.setIcon(iconoBoton);
        botonContrarreloj.setBounds(100, 350, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonContrarreloj);
        registrarOyenteBotonClasico();
        add(botonContrarreloj,0);
    }
    public void registrarOyenteBotonClasico(){
        botonClasico.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                miControladorGrafico.mostrarPantallaJuego();
			}
		});
    }
    public void registrarOyenteBotonContraTiempo(){
        //TODO implementar
    }
    public void registrarOyenteBotonSupervivencia(){
        //TODO implementar
    }
    public void transparentarBoton(JButton boton){
        //TODO implementar
    }
}

