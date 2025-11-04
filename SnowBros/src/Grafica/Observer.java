package SnowBros.src.Grafica;

public interface Observer {
    public void actualizar();
    public void actualizar(int direccion, int tocandoSuelo);
    public void actualizarBolaDeNieve();
    public void borrarObserver();
}
