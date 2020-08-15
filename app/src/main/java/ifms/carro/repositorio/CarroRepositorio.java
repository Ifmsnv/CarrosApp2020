package ifms.carro.repositorio;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ifms.carro.HttpListener;
import ifms.carro.MeuApplication;
import ifms.carro.models.Carro;
import ifms.carro.models.Carros;

public class CarroRepositorio {

    public void loadCarros(final HttpListener<Carros> listener) {
        /*Carros dados = new Carros();
        dados.add(new Carro(0, "Honda Civic"));
        dados.add(new Carro(0, "Fiat Toro"));
        dados.add(new Carro(0, "Ford Ka"));
        dados.add(new Carro(0, "Fiat Palio"));
        dados.add(new Carro(0, "Volkswagen Saveiro"));
        dados.add(new Carro(0, "Ford Ranger"));
        dados.add(new Carro(0, "Chevrolet Onix"));

        listener.onLoaded(dados);*/

        String url = "http://carros.chiquitto.com.br/api/veiculos";

        Response.Listener<JSONArray> okListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Carros carros = new Carros();

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject carroObj = response.getJSONObject(i);
                        Integer carroId = carroObj.getInt("idVeiculo");
                        String carroModelo = carroObj.getString("modelo");

                        Carro carro = new Carro(carroId, carroModelo);
                        carros.add(carro);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listener.onLoaded(carros);
            }
        };

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                okListener,
                null
        );

        MeuApplication.getVolleyQueue().add(request);
    }

}
