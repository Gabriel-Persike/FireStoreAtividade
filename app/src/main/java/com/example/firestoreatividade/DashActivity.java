package com.example.firestoreatividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textWelcome;
    private FirebaseFirestore db;
    private TextView textResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        mAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
        textResultado = findViewById(R.id.textResultado);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        textWelcome.setText("Bem vindo, " + user.getEmail());
        db = FirebaseFirestore.getInstance();
    }

    public void sair(View view){
        mAuth.signOut();
        Intent inicio = new Intent(DashActivity.this, MainActivity.class);
        startActivity(inicio);
        finish();
    }

    public void cadastrardados(View view) {
        Intent cadastrardados = new Intent(DashActivity.this, CadOneActivity.class);
        startActivity(cadastrardados);
    }

    public void cadastrarvenda(View view) {
        Intent cadastrarvenda = new Intent(DashActivity.this, CadTwoActivity.class);
        startActivity(cadastrarvenda);
    }

    public void cadastrartarefa(View view) {
        Intent cadastrartarefa = new Intent(DashActivity.this, CadTarefaActivity.class);
        startActivity(cadastrartarefa);
    }

    public void gerardadosnofirebase(View view) {
        String nome = "Ana Maria";
        int filhos = 0;
        double salario = 3000.60;
        boolean ativo = true;
        List<String> pets = Arrays.asList("Thor", "Mel", "Bolinha");
        long dataL = LocalDate.of(1995,3,15).toEpochDay();
        Date data = new Date(dataL);
        Pessoa pessoa = new Pessoa(nome, filhos, salario, ativo, pets, data);

        db.collection("exemplo").add(pessoa);
    }

    public void carregardados(View view){
        CollectionReference pessoas = db.collection("exemplo");
        pessoas.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    String result = "";

                    for (QueryDocumentSnapshot document : task.getResult()){
                        result +=  document.getData().toString() + "\n";
                    }
                    textResultado.setText(result);
                }
            }
        });
    }

    public void consultarpessoa(View view) {
        Intent consutarpessoas = new Intent(DashActivity.this, ConsultaPessoas.class);
        startActivity(consutarpessoas);
    }
}
