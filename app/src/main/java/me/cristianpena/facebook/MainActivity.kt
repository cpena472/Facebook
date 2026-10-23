package me.cristianpena.facebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import me.cristianpena.facebook.ui.theme.FacebookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FacebookTheme {
                var showRegister by rememberSaveable { mutableStateOf(false) }


                BackHandler(enabled = showRegister) {
                    showRegister = false
                }

                if (showRegister) {
                    FacebookRegisterScreen(
                        onBackClick = { showRegister = false },
                        onLoginClick = { showRegister = false }
                    )
                } else {
                    FacebookLoginScreen(
                        onCreateAccountClick = { showRegister = true }
                    )
                }
            }
        }
    }
}