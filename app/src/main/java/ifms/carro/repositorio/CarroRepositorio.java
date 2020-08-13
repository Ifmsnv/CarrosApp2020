package ifms.carro.repositorio;

import ifms.carro.models.Carro;
import ifms.carro.models.Carros;

public class CarroRepositorio {

    public Carros loadCarros() {
        Carros dados = new Carros();

        dados.add(new Carro(0, "Honda Civic"));
        dados.add(new Carro(0, "Fiat Toro"));
        dados.add(new Carro(0, "Ford Ka"));
        dados.add(new Carro(0, "Fiat Palio"));
        dados.add(new Carro(0, "Volkswagen Saveiro"));
        dados.add(new Carro(0, "Ford Ranger"));
        dados.add(new Carro(0, "Chevrolet Onix"));

        return dados;
    }

}
