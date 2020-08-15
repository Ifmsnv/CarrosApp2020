package ifms.carro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import ifms.carro.models.Carro;
import ifms.carro.models.Carros;
import ifms.carro.repositorio.CarroRepositorio;

public class MainActivity extends AppCompatActivity {

    private Carros dados = new Carros();
    private ListView listView;
    private ArrayAdapter<Carro> adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        iniciarLista();
        carregarDados();
    }

    private void iniciarLista() {
        listView = findViewById(R.id.lista);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dados
        );

        listView.setAdapter(adapter);
    }

    private void carregarDados() {
        CarroRepositorio repo = new CarroRepositorio();

        HttpListener<Carros> listener = new HttpListener<Carros>() {
            @Override
            public void onLoaded(Carros carros) {
                dados.clear();
                dados.addAll(carros);
                adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
            }
        };

        repo.loadCarros(listener);

        // dados.clear();
        // dados.addAll(repo.loadCarros());
        // adapter.notifyDataSetChanged();
    }
}