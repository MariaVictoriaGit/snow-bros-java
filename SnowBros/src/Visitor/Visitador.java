package SnowBros.src.Visitor;

import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Escalera;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.SueloResbaladizo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.TrampaPinchos;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;

interface Visitador {
    
    public void visitar(Enemigo e);
    public void visitar(Plataforma e);
    public void visitar(PowerUp e);
    public void visitar(Pared e);
    public void visitar(ParedDestructible e);
    public void visitar(Escalera e);
    public void visitar(SueloResbaladizo e);
    public void visitar(TrampaPinchos e);
    public void visitar(Jugador e);
}
