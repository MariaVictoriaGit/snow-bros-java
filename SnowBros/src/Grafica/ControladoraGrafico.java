package SnowBros.src.Grafica;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

interface ControladorGrafico {
    public ObserverPlataformaMovil registrarEntidad(PlataformaMovil p);
    public void registrarEntidadEstatica(EntidadEstatica e);
    public void registrarEntidad(EntidadDinamica e);
    public void registrarEntidad(Jugador j);
    //TODO public Observer registrarSprite(Entidad e);
}
