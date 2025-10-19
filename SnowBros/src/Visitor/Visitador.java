package SnowBros.src.Visitor;

import SnowBros.src.Enemigos.Enemigo;
import SnowBros.src.Jugador.Jugador;
import SnowBros.src.Obstaculos.Pared;
import SnowBros.src.Obstaculos.ParedDestructible;
import SnowBros.src.Obstaculos.SueloResbaladizo;
import SnowBros.src.Obstaculos.TrampaPinchos;
import SnowBros.src.Obstaculos.Escalera;
import SnowBros.src.Plataformas.Plataforma;
import SnowBros.src.PowerUps.PowerUp;

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
