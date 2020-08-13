package ifms.carro.models;

public class Carro {

    private int id;
    private String modelo;

    public Carro(int id, String modelo) {
        this.id = id;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public String toString() {
        return id + "/" + modelo;
    }
}
