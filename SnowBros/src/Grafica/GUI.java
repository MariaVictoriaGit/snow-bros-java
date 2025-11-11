package SnowBros.src.Grafica;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Paneles.*;

public class GUI implements ControladorGrafico, ControladorDeVista {
    protected JFrame ventana;
    protected PanelPantallaPrincipal panelPantallaPrincipal;
    protected PanelPantallaRanking panelPantallaRanking;
    protected PanelPantallaDominio panelPantallaDominio;
    protected PanelPantallaModo panelPantallaModo;
    protected PanelPantallaGameOver panelPantallaGameOver;
    protected PanelPantallaTransicion panelPantallaTransicion;
    protected PanelPantallaJuego panelPantallaJuego;
    protected PanelPantallaFinDelJuego panelPantallaFinDelJuego;
    protected Juego miJuego;
    private boolean movDerecha=false;
    private boolean movIzquierda=false;
    private boolean disparando=false;
    private boolean empujando=false;
    protected String dominio;
    protected String modoDeJuego;

    public GUI(Juego juego){
        this.miJuego=juego;
        panelPantallaPrincipal = new PanelPantallaPrincipal(this);
        panelPantallaDominio= new PanelPantallaDominio(this);
        panelPantallaRanking = new PanelPantallaRanking(this);
        panelPantallaModo = new PanelPantallaModo(this);
        panelPantallaGameOver= new PanelPantallaGameOver(this);
        panelPantallaTransicion=new PanelPantallaTransicion(this);
        panelPantallaFinDelJuego= new PanelPantallaFinDelJuego(this);
        configurarVentana();
        registrarOyenteVentana();
    }
    private void configurarVentana() {
        ventana = new JFrame("✨❄️☃️SnowBros TDP Comision 14 ❄️💙✨");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setSize(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    protected void registrarOyenteVentana() {
        addWindowKeyListener();
    }
    private void addWindowKeyListener() {
        ventana.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                int keyCode=event.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_A:
                        movIzquierda=true;
                        break;
                    case KeyEvent.VK_D:
                        movDerecha=true;
                        break;
                    case KeyEvent.VK_W:
                        if(!miJuego.getJugador().getSaltando() && miJuego.getJugador().getTocandoSuelo()==ConstantesEntidades.SUELO){
                            miJuego.getJugador().mover(ConstantesEntidades.ARRIBA);
                            miJuego.getJugador().setSaltando(true);
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(miJuego.getJugador().estaHabilitadoASubirEscalera()){
                            miJuego.getJugador().mover(ConstantesEntidades.ABAJO);
                            miJuego.getJugador().getObserver().actualizar(ConstantesEntidades.ABAJO, miJuego.getJugador().getTocandoSuelo());
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if(miJuego.getJugador().estaEmpujando()!=null){
                            disparando=false;
                            empujando=true;
                        } else{
                            disparando=true;
                        }
                        break;
                }
            }
            public void keyReleased(KeyEvent event) {
                int keyCode=event.getKeyCode();
                if (keyCode==KeyEvent.VK_A || keyCode==KeyEvent.VK_D)
                    if(keyCode==KeyEvent.VK_A)
                        movIzquierda=false;
                    else
                        movDerecha=false;
                if(keyCode==KeyEvent.VK_SPACE){
                    disparando=false;
                    empujando=false;
                    miJuego.getJugador().setEmpujando(null);
                }
            }
        });
    }
    public void moverJugadorSegunInput(){
        if(movDerecha)
           miJuego.getJugador().mover(ConstantesEntidades.DERECHA);
        else if(movIzquierda)
            miJuego.getJugador().mover(ConstantesEntidades.IZQUIERDA);
        else{
            miJuego.getJugador().mover(ConstantesEntidades.QUIETO);
            movDerecha=false;
            movIzquierda=false;
        }
        if(miJuego.getJugador().getSaltando())
           miJuego.getJugador().mover(ConstantesEntidades.ARRIBA);
        if(disparando)
            miJuego.shootJugador(); 
        if(empujando)
            miJuego.empujarBolaDeNieve();
    }
    public JFrame getVentana() {
        return ventana;
    }   
    private void cambiarPantalla(JPanel panel) {
        ventana.setContentPane(panel);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void cambiarPantallaTransicion(){
        cambiarPantalla(panelPantallaTransicion);
    }
    public void cambiarPantallaGameOver(){
        cambiarPantalla(panelPantallaGameOver);
    }
    public void cambiarPantallaPrincipal() {
        Sonido.getInstancia().reproducirMusica("temaMusicalMenu.wav");
        cambiarPantalla(panelPantallaPrincipal);
    }
    public void cambiarPantallaFinDelJuego() {
        Sonido.getInstancia().frenarMusica();
        Sonido.getInstancia().reproducirEfectoDeSonido("gameOver.wav");
        cambiarPantalla(panelPantallaFinDelJuego);
    }
    public void cambiarPantallaDominio() {
        cambiarPantalla(panelPantallaDominio);
    }
    public void cambiarPantallaModo() {
        cambiarPantalla(panelPantallaModo);
    }    
    public void cambiarPantallaRanking() {
        panelPantallaRanking.actualizarRanking(miJuego.getRanking());
        cambiarPantalla(panelPantallaRanking);
    }
    public void cambiarInicioJuego() {
        this.panelPantallaJuego=new PanelPantallaJuego(this);
        cambiarPantalla(panelPantallaJuego);
        miJuego.iniciar();
        panelPantallaJuego.mostrarPlataformas();
        panelPantallaJuego.mostrarObstaculos();
        panelPantallaJuego.incorporarSilueta(miJuego.getNivelActual().getSilueta());
    //    panelPantallaJuego.setComponentZOrder(miJuego.getJugador().getObserver().getImagen(),3);
      //  for(Enemigo enemigo:miJuego.getNivelActual().getEnemigos()){
        //    panelPantallaJuego.setComponentZOrder(enemigo.getObserver().getImagen(),3);
        //}
        refrescar();
        //controladorDeSonido.reproducirSonidoDeFondo();
    }
    public void setDominio(String dominio){
        this.dominio=dominio;
        miJuego.setDominio(dominio);
    }
    public void setModoJuego(String modo) {
        modoDeJuego=modo;
        miJuego.setModoDeJuego(modo);
        String nombre = javax.swing.JOptionPane.showInputDialog(ventana,"Ingresa tu nombre para el ranking:","Registro de Jugador",javax.swing.JOptionPane.PLAIN_MESSAGE);
        if (nombre == null || nombre.trim().isEmpty())
            cambiarPantallaFinDelJuego();
        miJuego.setNombreJugador(nombre);
        miJuego.getRanking().setJugador(nombre, 0, modo);
    }
    public void pasarNivel(){
        miJuego.superarNivel();
    }
    public void reiniciarNivel(){
        miJuego.reiniciarNivel();
    }
    public void registrarEntidad(EntidadDinamica entidad){
        panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
    }
    public ObserverPlataformaMovil registrarEntidad(PlataformaMovil entidad){
        ObserverPlataformaMovil observerPlataforma = panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
        return observerPlataforma;
    }
    public void registrarEntidadEstatica(EntidadEstatica e){
        panelPantallaJuego.incorporarEntidadEstatica(e);
    }
    public void registrarEntidad(Jugador jugador) {
        panelPantallaJuego.incorporarEntidadJugador(jugador);
        refrescar();
    }
   // public Observer registrarSilueta(Entidad silueta) {
        //Observer observer_silueta = panelPantallaJuego.incorporarSilueta(silueta);
    //    refrescar();
     //   return observer_silueta;
    //}
   /* public Observer registrarSprite(Entidad e){
        return null; 
    }*/
    public void refrescar() {
        ventana.revalidate();
        ventana.repaint();
    }
    public void setJugadorVisibleGraficamente(){
        miJuego.getJugador().getObserver().setVisible(true);
        panelPantallaJuego.repaint();
    }
    public void setJugadorInvisibleGraficamente(){
        miJuego.getJugador().getObserver().setVisible(false);
        panelPantallaJuego.repaint();
    }
    public Juego getJuego() {
        return miJuego;
    }
    public PanelPantallaJuego getPantalla(){
        return panelPantallaJuego;
    }
}