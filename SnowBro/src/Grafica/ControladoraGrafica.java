package SnowBro.src.Grafica;

interface ControladoraGrafica {
    public void registrarControladorJuego(Juego j);

    public void mostrarPantallaInicial();

    public void mostrarPantallaJuego();

    public void mostrarPantallaFinJuego();

    public Observer registrarEntidad(Entidad e);

    public Observer registrarEntidad(Jugador j);

    public Observer registrarSprite(Entidad e);

    public void accionarInicioJuego();
}
