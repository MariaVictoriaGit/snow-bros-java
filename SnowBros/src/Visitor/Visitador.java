package SnowBros.src.Visitor;

import SnowBros.src.Entidades.EntidadDinamica.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadDinamica.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadEstatica.Obstaculos.Escalera;
import SnowBros.src.Entidades.EntidadEstatica.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadEstatica.Obstaculos.ParedDestructible;
import SnowBros.src.Entidades.EntidadEstatica.Obstaculos.SueloResbaladizo;
import SnowBros.src.Entidades.EntidadEstatica.Obstaculos.TrampaPinchos;
import SnowBros.src.Entidades.EntidadEstatica.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadEstatica.PowerUps.PowerUp;

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
