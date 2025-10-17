package SnowBros.src.Grafica;

import SnowBros.src.Juego.Entidad;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Jugador.Jugador;

interface ControladorGrafico {
    public void registrarControladorJuego(Juego j);

    public void mostrarPantallaInicial();

    public void mostrarPantallaJuego();

    public void mostrarPantallaFinJuego();

    public Observer registrarEntidad(Entidad e);

    public Observer registrarEntidad(Jugador j);

    public Observer registrarSprite(Entidad e);

    public void accionarInicioJuego();
}
