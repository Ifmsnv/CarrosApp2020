package ifms.carro.repositorio;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ifms.carro.HttpListener;
import ifms.carro.MeuApplication;
import ifms.carro.models.Marca;
import ifms.carro.models.Marcas;

public class MarcaRepositorio {

    private static final String TAG = MarcaRepositorio.class.getSimpleName();

    public void loadMarcas(final HttpListener<Marcas> httpListener) {
        final String url = "https://carros.chiquitto.com.br/api/marcas";

        Response.Listener<JSONArray> volleyListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Log.i(TAG, response.toString());

                Marcas marcas = new Marcas();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject marcaObj = response.getJSONObject(i);
                        Integer idMarca = marcaObj.getInt("idMarca");
                        String nomeMarca = marcaObj.getString("marca");

                        Marca marca = new Marca(idMarca, nomeMarca);
                        marcas.add(marca);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                httpListener.onLoaded(marcas);
            }
        };

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                volleyListener,
                null
        );

        MeuApplication.getVolleyQueue().add(request);
    }

}
