package SnowBros.src.Grafica;

import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

interface ControladorGrafico {
    
    public ObserverGrafico registrarEntidad(EntidadDinamica e);
    public Observer registrarEntidad(Jugador j);
    public ObserverPlataformaMovil registrarEntidad(PlataformaMovil p);
}
