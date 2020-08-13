package ifms.carro.repositorio;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ifms.carro.HttpListener;
import ifms.carro.VolleyQueue;
import ifms.carro.models.Carro;
import ifms.carro.models.Carros;

public class CarroRepositorio {

    public void loadCarros(final HttpListener httpListener) {
        final String url ="http://carros.chiquitto.com.br/api/veiculos";

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Carros dados = new Carros();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject carroObj = response.getJSONObject(i);

                        Integer carroId = carroObj.getInt("idVeiculo");
                        String carroModelo = carroObj.getString("modelo");

                        Carro carro = new Carro(carroId, carroModelo);
                        dados.add(carro);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                httpListener.onLoad(dados);

            }
        };

        JsonArrayRequest req = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                listener,
                null
        );

        VolleyQueue.getInstance().add(req);
    }

}
