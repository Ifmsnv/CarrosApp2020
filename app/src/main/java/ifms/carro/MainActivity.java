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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarDados();
        iniciarLista();
    }

    private void iniciarLista() {
        listView = findViewById(R.id.lista);

        ArrayAdapter<Carro> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dados
        );

        listView.setAdapter(adapter);
    }

    private void carregarDados() {
        CarroRepositorio repo = new CarroRepositorio();
        dados = repo.loadCarros();
    }
}