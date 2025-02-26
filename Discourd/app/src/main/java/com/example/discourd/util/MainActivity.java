package com.example.discourd.util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discourd.vue.Vue_Accueil;
import com.example.discourd.vue.Vue_Connexion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*// Vérifiez si un utilisateur est connecté
        PreferencesManager preferencesManager = new PreferencesManager(this);

        if (preferencesManager.getUser() != null) {
            // Utilisateur connecté : redirige vers l'accueil
            Intent intent = new Intent(this, Vue_Accueil.class);
            startActivity(intent);
        } else {
            // Aucun utilisateur connecté : redirige vers la page de connexion
            Intent intent = new Intent(this, Vue_Connexion.class);
            startActivity(intent);
        }

        // Finir MainActivity pour ne pas pouvoir revenir dessus
        finish();*/

        PreferencesManager preferencesManager = new PreferencesManager(this);
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean biometricEnabled = sharedPreferences.getBoolean("biometric_enabled", true);

        if (preferencesManager.getUser() != null) {
            if (biometricEnabled) {
                // Lancer l'authentification biométrique
                BiometricPrompt biometricPrompt = new BiometricPrompt(this,
                        Executors.newSingleThreadExecutor(), new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        Intent intent = new Intent(MainActivity.this, Vue_Accueil.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        Toast.makeText(MainActivity.this, "Échec de l'authentification", Toast.LENGTH_SHORT).show();
                    }
                });

                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Connexion biométrique")
                        .setSubtitle("Utilisez votre empreinte digitale pour vous connecter")
                        .setNegativeButtonText("Annuler")
                        .build();

                biometricPrompt.authenticate(promptInfo);
            } else {
                // Si la biométrie est désactivée, aller directement à la page de connexion
                Intent intent = new Intent(this, Vue_Connexion.class);
                startActivity(intent);
                finish();
            }
        } else {
            Intent intent = new Intent(this, Vue_Connexion.class);
            startActivity(intent);
            finish();
        }
    }

    // Vérifie si l'authentification biométrique est disponible
    private boolean isBiometricAvailable() {
        BiometricManager biometricManager = BiometricManager.from(this);
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                == BiometricManager.BIOMETRIC_SUCCESS;
    }

    // Affiche la boîte de dialogue biométrique
    private void showBiometricPrompt(PreferencesManager preferencesManager) {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        goToHome();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        goToLogin();
                    }
                });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Connexion avec empreinte digitale")
                .setSubtitle("Posez votre doigt sur le capteur")
                .setNegativeButtonText("Utiliser le mot de passe")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    // Redirection vers l'accueil
    private void goToHome() {
        Intent intent = new Intent(this, Vue_Accueil.class);
        startActivity(intent);
        finish();
    }

    // Redirection vers la connexion
    private void goToLogin() {
        Intent intent = new Intent(this, Vue_Connexion.class);
        startActivity(intent);
        finish();
    }

    /*@Override
    protected void onDestroy() {
        PreferencesManager preferencesManager = new PreferencesManager(this);
        super.onDestroy();
        preferencesManager.clearUser(); // Efface les données utilisateur lorsque l'application est fermée
    }*/
}
