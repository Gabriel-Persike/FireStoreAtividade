package com.example.firestoreatividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class CadOneActivity extends AppCompatActivity {

    private EditText editNome, editEndereco, editTelefone;
    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_one);
        editNome = findViewById(R.id.editNome);
        editEndereco = findViewById(R.id.editEndereco);
        editTelefone = findViewById(R.id.editTelefone);
    }

    @Override
    protected void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }



    public void salvarnofirebase1(View view) {
        String nome = editNome.getText().toString();
        String endereco = editEndereco.getText().toString();
        String telefone = editTelefone.getText().toString();

        Map<String,Object> dadosUsuario = new HashMap<>();
        dadosUsuario.put("nome", nome);
        dadosUsuario.put("telefone", telefone);
        dadosUsuario.put("endereco", endereco);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("usuarios").document(user.getUid())
                .set(dadosUsuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        String texto = "Dados cadastrados com sucesso.";
                        Toast.makeText(CadOneActivity.this, texto, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String texto = "Falha ao cadastrar dados.";
                        Toast.makeText(CadOneActivity.this, texto, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
