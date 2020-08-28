package ifms.carro.repositorio;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ifms.carro.HttpListener;
import ifms.carro.VolleyQueue;
import ifms.carro.models.Marca;
import ifms.carro.models.Marcas;

public class MarcaRepositorio {

    public void loadMarcas(final HttpListener httpListener) {
        final String url = "http://carros.chiquitto.com.br/api/marcas";

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Marcas dados = new Marcas();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        dados.add(json2Marca(response.getJSONObject(i)));
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

    private Marca json2Marca(JSONObject marcaObj) throws JSONException {
        Integer idMarca = marcaObj.getInt("idMarca");
        String marca = marcaObj.getString("marca");

        return new Marca(idMarca, marca);
    }

}
