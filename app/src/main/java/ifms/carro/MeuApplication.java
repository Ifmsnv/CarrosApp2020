package ifms.carro;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MeuApplication extends Application {

    private static Application app;
    private static RequestQueue volleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public static Application getApp() {
        return app;
    }

    public static RequestQueue getVolleyQueue() {
        if (volleyQueue == null) {
            volleyQueue = Volley.newRequestQueue(MeuApplication.getApp().getApplicationContext());
        }
        return volleyQueue;
    }
}
