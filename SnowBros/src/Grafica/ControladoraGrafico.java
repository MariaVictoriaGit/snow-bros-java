package SnowBros.src.Grafica;

import SnowBros.src.Entidades.Entidad;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;

interface ControladorGrafico {
    
    public void mostrarPantallaPrincipal();
    public void mostrarPantallaJuego();
    public void mostrarPantallaFinDelJuego();
    public ObserverGrafico registrarEntidad(Entidad e);
    public Observer registrarEntidad(Jugador j);
    /*public ObserverEntidad registrarEntidad(EntidadDinamica e);
    public ObserverJugador registrarEntidad(Jugador j); */
    //TODO public Observer registrarSprite(Entidad e);
    public void accionarInicioJuego();
}
