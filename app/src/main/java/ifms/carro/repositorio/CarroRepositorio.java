package ifms.carro.repositorio;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ifms.carro.HttpListener;
import ifms.carro.VolleyQueue;
import ifms.carro.models.Carro;
import ifms.carro.models.Carros;

public class CarroRepositorio {

    private static final String TAG = CarroRepositorio.class.getSimpleName();

    public void loadCarros(final HttpListener httpListener) {
        final String url = "http://carros.chiquitto.com.br/api/veiculos";

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Carros dados = new Carros();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        dados.add(json2Carro(response.getJSONObject(i)));
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

    private Carro json2Carro(JSONObject carroObj) throws JSONException {
        Integer carroId = carroObj.getInt("idVeiculo");
        String carroModelo = carroObj.getString("modelo");

        return new Carro(carroId, carroModelo, null);
    }


    public void novo(final Carro carro, final HttpListener<Carro> carroHttpListener) {
        JSONObject carroJson = new JSONObject();
        try {
            carroJson.put("idMarca", carro.getMarca().getId());
            carroJson.put("modelo", carro.getModelo());
            carroJson.put("ano", 2000);
            carroJson.put("placa", "ABC1234");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url = "http://carros.chiquitto.com.br/api/veiculos";

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Integer idVeiculo = response.getInt("idVeiculo");
                    carro.setId(idVeiculo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                carroHttpListener.onLoad(carro);
            }
        };

        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                carroJson,
                listener,
                null
        );

        VolleyQueue.getInstance().add(req);

    }
}
