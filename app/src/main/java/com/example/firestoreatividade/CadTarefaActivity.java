package com.example.firestoreatividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadTarefaActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editTituloTarefa;
    private Spinner spinnerPrioridade, spinnerCategoria;
    private Calendar calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_tarefa);
        editTituloTarefa = findViewById(R.id.editTituloTarefa);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerPrioridade = findViewById(R.id.spinnerPrioridade);
        calendario = Calendar.getInstance();
        //Define o spinner de categoria
        ArrayAdapter<CharSequence> categoriasAdapter = ArrayAdapter
                .createFromResource(this, R.array.categorias_array, android.R.layout.simple_spinner_dropdown_item);

        categoriasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(categoriasAdapter);

        //Define o spinner de prioridade
        ArrayAdapter<CharSequence> prioridadeAdapter = ArrayAdapter
                .createFromResource(this, R.array.prioridades_array, android.R.layout.simple_spinner_dropdown_item);

        prioridadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridade.setAdapter(prioridadeAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void cadastrardadosTarefa(View view) {
        String tituloTarefa = editTituloTarefa.getText().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();
        String prioridade = spinnerPrioridade.getSelectedItem().toString();
        String data = "";
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        data = date.format(calendario.getTime());
        Tarefa tarefa = new Tarefa(data, tituloTarefa, prioridade, categoria);
        db.collection("tarefas").add(tarefa).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String texto = "Tarefa adicionada.";
                Toast.makeText(CadTarefaActivity.this, texto, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String texto = "Falha ao adicionar tarefa.";
                Toast.makeText(CadTarefaActivity.this, texto, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
