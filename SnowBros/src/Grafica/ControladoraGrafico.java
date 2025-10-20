package SnowBros.src.Grafica;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadDinamica.Jugador.Jugador;
import SnowBros.src.Juego.Juego;

interface ControladorGrafico {
    
    public void registrarControladorJuego(Juego j);
    public void mostrarPantallaInicial();
    public void mostrarPantallaJuego();
    public void mostrarPantallaFinJuego();
    public Observer registrarEntidad(Entidad e);
    public Observer registrarEntidad(Jugador j);
    //TODO public Observer registrarSprite(Entidad e);
    public void accionarInicioJuego();
}
