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


public class PanelPantallaRanking extends JPanel {
    protected JLabel imagenFondo;
    protected JButton botonSalir;
    protected GUI miControladorGrafico;

    public PanelPantallaRanking(GUI miControladorGrafico){
        this.miControladorGrafico=miControladorGrafico;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonSalir();
    }
    public void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/PantallaRanking.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);    
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
    public void registrarOyenteBotonSalir(){
        botonSalir.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            miControladorGrafico.mostrarPantallaPrincipal();
        }
        });
    }
    private void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
    }
}
