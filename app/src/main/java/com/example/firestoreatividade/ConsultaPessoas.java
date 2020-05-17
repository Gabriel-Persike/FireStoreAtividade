package com.example.firestoreatividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConsultaPessoas extends AppCompatActivity {

    private EditText editValorParametroQuery;
    private Spinner spinnerCampoParametroQuery;
    private TextView textParametrosQuery;
    private FirebaseFirestore db;
    private List<String> parametros = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_pessoas);
        editValorParametroQuery = findViewById(R.id.editValorParametroQuery);
        spinnerCampoParametroQuery = findViewById(R.id.spinnerCampoParametroQuery);
        textParametrosQuery = findViewById(R.id.textParametrosQuery);

        //Define o spinner de campos
        ArrayAdapter<CharSequence> campoAdapter = ArrayAdapter
                .createFromResource(this, R.array.campos_pessoa, android.R.layout.simple_spinner_dropdown_item);

        campoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCampoParametroQuery.setAdapter(campoAdapter);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void adicionarParametro(View view) {
        parametros.add(spinnerCampoParametroQuery.getSelectedItem().toString() + ":" + editValorParametroQuery.getText().toString());
        atualizaParamtros();
    }


    public void resetaParametros(View view) {
        int size = parametros.size();
        for (int i = 0; i < size; i++){
            parametros.remove(parametros.get(0));
        }
        atualizaParamtros();
    }

    private void atualizaParamtros(){
        String parametoString = "";
        for (int i = 0; i < parametros.size(); i++){
            parametoString += parametros.get(i) + "\n";
        }
        textParametrosQuery.setText(parametoString);
    }


    public void consultarpessoa(View view) {
        CollectionReference pessoas = db.collection("exemplo");
        Query query;
        if(spinnerCampoParametroQuery.getSelectedItem().toString().equals("Todos")){
            query = pessoas;
        }
        else if (spinnerCampoParametroQuery.getSelectedItem().toString().equals("Nome")){
            Toast.makeText(this, "Nome", Toast.LENGTH_SHORT).show();
            query = pessoas.whereEqualTo("nome", editValorParametroQuery.getText().toString());
        }
        else if (spinnerCampoParametroQuery.getSelectedItem().toString().equals("Quantidade de filhos maior que")){
            query = pessoas.whereGreaterThan("flihos", Integer.parseInt(editValorParametroQuery.getText().toString()));
        }
        else if (spinnerCampoParametroQuery.getSelectedItem().toString().equals("Quantidade de filhos menor que")){
            query = pessoas.whereLessThan("flihos", Integer.parseInt(editValorParametroQuery.getText().toString()));
        }
        else if (spinnerCampoParametroQuery.getSelectedItem().toString().equals("Salario maior que")){
            query = pessoas.whereGreaterThan("salario", Double.parseDouble(editValorParametroQuery.getText().toString()));
        }
        else if (spinnerCampoParametroQuery.getSelectedItem().toString().equals("Salario menor que")){
            query = pessoas.whereLessThan("salario", Double.parseDouble(editValorParametroQuery.getText().toString()));
        }
        else if (spinnerCampoParametroQuery.getSelectedItem().toString().equals("Ativo")){
            query = pessoas.whereEqualTo("ativo", Boolean.parseBoolean(editValorParametroQuery.getText().toString()));
        }
        else if (spinnerCampoParametroQuery.getSelectedItem().toString().equals("Nome do pet")){
            query = pessoas.whereArrayContains("pets", editValorParametroQuery.getText().toString());
        }
        else{
            Toast.makeText(this, spinnerCampoParametroQuery.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            query = pessoas.whereGreaterThan("nome", editValorParametroQuery.getText().toString());
        }


        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    String resultado = "";
                    List<Pessoa> listPessoas = new ArrayList<>();
                    for (QueryDocumentSnapshot document:task.getResult()){
                        listPessoas.add(document.toObject(Pessoa.class));
                    }
                    for (Pessoa p : listPessoas){
                        resultado += p.returnString() + "\n";
                        Toast.makeText(ConsultaPessoas.this, p.toString(), Toast.LENGTH_SHORT).show();
                    }
                    textParametrosQuery.setText(resultado);
                }
                else{
                    Log.e("queryConsultarPesosa", task.toString());
                    Toast.makeText(ConsultaPessoas.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(this, "Fim", Toast.LENGTH_SHORT).show();
    }
}
