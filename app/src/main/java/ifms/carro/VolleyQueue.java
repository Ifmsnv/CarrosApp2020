package ifms.carro;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyQueue {

    private static RequestQueue instance;

    public static RequestQueue getInstance() {
        if (instance == null) {
            instance = Volley.newRequestQueue(AppController.getInstance().getApplicationContext());
        }
        return instance;
    }
}
