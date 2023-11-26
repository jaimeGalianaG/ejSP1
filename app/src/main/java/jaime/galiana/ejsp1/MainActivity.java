package jaime.galiana.ejsp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import jaime.galiana.ejsp1.modelos.Constantes;

public class MainActivity extends AppCompatActivity {
    private TextView txtNombre;
    private Button btnGuardar;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVista();
        sp = getSharedPreferences(Constantes.LOGIN, MODE_PRIVATE);

        boolean acceso = comprobarAcceso();

        if (acceso){
            abrirActivity();
        }else {

            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombre = txtNombre.getText().toString();
                    
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Constantes.NOMBRE, nombre);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "ACCESS GRANTED", Toast.LENGTH_SHORT).show();
                    
                    abrirActivity();
                }
            });
        }


    }

    private void abrirActivity() {
        Intent intent = new Intent(MainActivity.this, AccesoActivity.class);
        startActivity(intent);
    }

    private boolean comprobarAcceso() {
        //recoger los datos del xml
        String nombre = sp.getString(Constantes.NOMBRE, "");

        if (!nombre.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    private void inicializarVista() {
        txtNombre = findViewById(R.id.txtNombreMain);
        btnGuardar = findViewById(R.id.btnGuardarMain);
    }
}