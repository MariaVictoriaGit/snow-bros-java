package SnowBros.src.Grafica;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;

interface ControladorGrafico {
    
    public void mostrarPantallaInicial();
    public void mostrarPantallaJuego();
    public void mostrarPantallaFinJuego();
    public Observer registrarEntidad(Entidad e);
    public Observer registrarEntidad(Jugador j);
    //TODO public Observer registrarSprite(Entidad e);
    public void accionarInicioJuego();
}
