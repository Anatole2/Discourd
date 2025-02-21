package com.example.discourd.vue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

    private static final String PREFS_NAME = "ThemePrefs";
    private static final String THEME_KEY = "theme";

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

        Button buttonThemeSombre = findViewById(R.id.buttonSombre);
        Button buttonThemeClair = findViewById(R.id.buttonClair);

        // Action pour passer au thème sombre
        buttonThemeSombre.setOnClickListener(v -> {
            // Appliquer le mode sombre
            Log.e("e", "Sombre");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            recreate(); // Recréer l'activité pour appliquer le thème
        });

        // Action pour passer au thème clair
        buttonThemeClair.setOnClickListener(v -> {
            // Appliquer le mode clair
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate(); // Recréer l'activité pour appliquer le thème
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