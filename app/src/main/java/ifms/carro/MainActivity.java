package ifms.carro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ifms.carro.models.Carro;
import ifms.carro.models.Carros;
import ifms.carro.repositorio.CarroRepositorio;

public class MainActivity extends AppCompatActivity {

    private Carros dados;
    private ListView listView;
    ArrayAdapter<Carro> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarLista();
        carregarDados();
    }

    private void iniciarLista() {
        listView = findViewById(R.id.lista);

        dados = new Carros();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dados
        );

        listView.setAdapter(adapter);
    }

    private void carregarDados() {
        HttpListener<Carros> httpListener = new HttpListener<Carros>() {
            @Override
            public void onLoad(Carros carros) {
                dados.clear();
                dados.addAll(carros);

                adapter.notifyDataSetChanged();
            }
        };

        CarroRepositorio repo = new CarroRepositorio();
        repo.loadCarros(httpListener);
    }
}