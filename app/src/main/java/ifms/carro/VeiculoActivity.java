package ifms.carro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ifms.carro.models.Carro;
import ifms.carro.models.Marca;
import ifms.carro.models.Marcas;
import ifms.carro.repositorio.CarroRepositorio;
import ifms.carro.repositorio.MarcaRepositorio;

public class VeiculoActivity extends AppCompatActivity {

    private static final String TAG = VeiculoActivity.class.getSimpleName();
    private Spinner spMarca;
    private EditText etModelo;
    private Button btnSalvar;

    private ArrayAdapter<Marca> marcaAdapter;
    private Marcas marcas = new Marcas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        iniciarSpinner();
        iniciarBotao();
        iniciarOutros();

        carregarMarcas();
    }

    private void iniciarOutros() {
        etModelo = findViewById(R.id.etModelo);
    }

    private void iniciarBotao() {
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar() {
        Marca marca = (Marca) spMarca.getSelectedItem();
        String modelo = etModelo.getText().toString();

        Carro carro = new Carro(0, modelo, marca);
        HttpListener<Carro> httpListener = new HttpListener<Carro>() {
            @Override
            public void onLoaded(Carro valor) {
                Toast.makeText(VeiculoActivity.this,
                        "Veiculo salvo", Toast.LENGTH_SHORT).show();

                finish();
            }
        };

        CarroRepositorio carroRepositorio = new CarroRepositorio();
        carroRepositorio.salvarCarro(carro, httpListener);
    }

    private void carregarMarcas() {
        HttpListener<Marcas> marcasListener = new HttpListener<Marcas>() {
            @Override
            public void onLoaded(Marcas valor) {
                marcas.clear();
                marcas.addAll(valor);
                marcaAdapter.notifyDataSetChanged();
            }
        };

        MarcaRepositorio repositorio = new MarcaRepositorio();
        repositorio.loadMarcas(marcasListener);
    }

    private void iniciarSpinner() {
        spMarca = findViewById(R.id.spMarca);

        marcaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                marcas);

        spMarca.setAdapter(marcaAdapter);
    }
}