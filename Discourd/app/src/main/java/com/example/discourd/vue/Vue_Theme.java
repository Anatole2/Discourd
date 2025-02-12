package com.example.discourd.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.discourd.R;

public class Vue_Theme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vue_theme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Boutons pour changer le thème
        Button buttonSombre = findViewById(R.id.buttonSombre);
        Button buttonClair = findViewById(R.id.buttonClair);

        // Action pour le bouton Thème Sombre
        buttonSombre.setOnClickListener(v -> {
            // Appliquer le thème sombre
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        });

        // Action pour le bouton Thème Clair
        buttonClair.setOnClickListener(v -> {
            // Appliquer le thème clair
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        });

        // Récupérer le bouton par son ID
        Button buttonRetourParametres = findViewById(R.id.buttonRetourParametres);

        // Définir un OnClickListener
        buttonRetourParametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour changer de page
                Intent intent = new Intent(Vue_Theme.this, Vue_Parametres.class);
                startActivity(intent); // Lancer l'activité
            }
        });
    }
}