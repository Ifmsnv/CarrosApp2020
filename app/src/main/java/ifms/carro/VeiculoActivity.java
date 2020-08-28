package ifms.carro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import ifms.carro.models.Carro;
import ifms.carro.models.Marca;
import ifms.carro.models.Marcas;
import ifms.carro.repositorio.CarroRepositorio;
import ifms.carro.repositorio.MarcaRepositorio;

public class VeiculoActivity extends AppCompatActivity {

    private static final String TAG = VeiculoActivity.class.getSimpleName();
    private Spinner spMarca;
    private Marcas marcas = new Marcas();
    private ArrayAdapter<Marca> adapter;
    private EditText etModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        iniciarComponentes();
        iniciarMarcas();
    }

    private void iniciarComponentes() {
        spMarca = findViewById(R.id.spMarca);
        adapter = new ArrayAdapter<>(
                VeiculoActivity.this,
                android.R.layout.simple_list_item_1,
                marcas
        );
        spMarca.setAdapter(adapter);

        etModelo = findViewById(R.id.etModelo);

        Button btnSalvar = findViewById(R.id.btnSalvar);
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

        CarroRepositorio carroRepositorio = new CarroRepositorio();
        carroRepositorio.novo(carro, new HttpListener<Carro>() {
            @Override
            public void onLoad(Carro data) {
                finish();
            }
        });
    }

    private void iniciarMarcas() {
        MarcaRepositorio marcaRepositorio = new MarcaRepositorio();
        marcaRepositorio.loadMarcas(new HttpListener<Marcas>() {
            @Override
            public void onLoad(Marcas dados) {
                marcas.clear();
                marcas.addAll(dados);

                adapter.notifyDataSetChanged();
            }
        });
    }
}
