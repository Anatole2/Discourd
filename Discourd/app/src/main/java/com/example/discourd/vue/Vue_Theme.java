package com.example.discourd.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

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

        // Références pour le Switch et le texte de statut
        Switch themeSwitch = findViewById(R.id.themeSwitch);
        TextView themeStatus = findViewById(R.id.themeStatus);

        // Vérifier l'état actuel du thème et appliquer la configuration initiale
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeSwitch.setChecked(true); // Si sombre, activer le switch
            themeStatus.setText("Thème Sombre");
        } else {
            themeSwitch.setChecked(false); // Si clair, désactiver le switch
            themeStatus.setText("Thème Clair");
        }

        // Gérer le changement de thème lorsque le Switch est basculé
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Si le Switch est activé, appliquer le thème sombre
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                themeStatus.setText("Thème Sombre");
            } else {
                // Si le Switch est désactivé, appliquer le thème clair
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                themeStatus.setText("Thème Clair");
            }
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