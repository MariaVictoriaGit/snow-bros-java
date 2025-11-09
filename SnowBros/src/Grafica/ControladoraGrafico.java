package SnowBros.src.Grafica;

import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

interface ControladorGrafico {
    
    public void mostrarPantallaPrincipal();
    public void mostrarPantallaDominio();
    public void mostrarPantallaModo();
    public void mostrarPantallaRanking();
    public void mostrarPantallaJuego();
    public void mostrarPantallaFinDelJuego();
    public ObserverGrafico registrarEntidad(EntidadDinamica e);
    public Observer registrarEntidad(Jugador j);
    public ObserverPlataformaMovil registrarEntidad(PlataformaMovil p);
    /*public ObserverEntidad registrarEntidad(EntidadDinamica e);
    public ObserverJugador registrarEntidad(Jugador j); */
    //TODO public Observer registrarSprite(Entidad e);
}
