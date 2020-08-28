package ifms.carro.models;

public class Marca {

    private int id;
    private String marca;

    public Marca(int id, String marca) {
        this.id = id;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return id + "/" + marca;
    }
}
