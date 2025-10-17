package SnowBro.src.Visitor;

import SnowBro.src.Jugador.Jugador;
import SnowBro.src.Obstáculos.TrampaPinchos;
import SnowBro.src.Plataformas.Plataforma;
import SnowBro.src.PowerUps.PowerUp;

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
