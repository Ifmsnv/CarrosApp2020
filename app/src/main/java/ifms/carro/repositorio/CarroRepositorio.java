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
import ifms.carro.MeuApplication;
import ifms.carro.models.Carro;
import ifms.carro.models.Carros;

public class CarroRepositorio {

    private static final String TAG = CarroRepositorio.class.getSimpleName();

    public void loadCarros(final HttpListener<Carros> listener) {
        String url = "https://carros.chiquitto.com.br/api/veiculos";

        Response.Listener<JSONArray> okListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Carros carros = new Carros();

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject carroObj = response.getJSONObject(i);
                        Integer carroId = carroObj.getInt("idVeiculo");
                        String carroModelo = carroObj.getString("modelo");

                        Carro carro = new Carro(carroId, carroModelo, null);
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

    public void salvarCarro(final Carro carro, final HttpListener<Carro> httpListener) {
        String url = "https://carros.chiquitto.com.br/api/veiculos";

        Response.Listener<JSONObject> volleyListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.i(TAG, response.toString());

                try {
                    Integer idVeiculo = response.getInt("idVeiculo");
                    carro.setId(idVeiculo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                httpListener.onLoaded(carro);
            }
        };

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idMarca", carro.getMarca().getId());
            jsonObject.put("modelo", carro.getModelo());
            jsonObject.put("ano", 2020);
            jsonObject.put("placa", "ZZZ9999");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                volleyListener,
                null
        );

        MeuApplication.getVolleyQueue().add(request);
    }
}
