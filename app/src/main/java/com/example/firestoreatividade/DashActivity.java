package com.example.firestoreatividade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        mAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        textWelcome.setText("Bem vindo, " + user.getEmail());
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
}
