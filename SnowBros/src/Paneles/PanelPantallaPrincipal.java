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
    protected GUI miControlador;
    protected JLabel imagenFondo;
    protected JButton botonIniciar;
    protected JButton botonRanking;

    public PanelPantallaPrincipal(GUI controlador){
        this.miControlador=controlador;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonIniciar();
        agregarBotonRanking();
    }
    protected void agregarImagenFondo() {
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/PantallaPrincipal.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);
    }
    protected void agregarBotonIniciar() {
        botonIniciar = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/Imagenes/botonJugar.png"));
        botonIniciar.setIcon(iconoBoton);
        botonIniciar.setBounds(100, 350, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonIniciar);
        registrarOyenteBotonIniciar();
        add(botonIniciar,0);
    }
    private void agregarBotonRanking() {
        botonRanking= new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/Imagenes/Botones/botonRanking.png"));
        botonRanking.setIcon(iconoBoton);
        botonRanking.setBounds(85, 450, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonRanking);
        registrarOyenteBotonRanking();
        add(botonRanking,0);
    }
    private void registrarOyenteBotonIniciar() {
        botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//miControlador.accionarPantallaDominio();
                miControlador.mostrarPantallaJuego();
			}
		});
    }
    private void registrarOyenteBotonRanking() {
        botonRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.accionarPantallaRanking();
			}
		});
    }
    private void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
    }
}
