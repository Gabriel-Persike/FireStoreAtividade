package com.example.firestoreatividade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class activity_Cadastra_Um extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cadastra__um);
    }

    public void salvarFirebaseStore(View view) {

    }
}
