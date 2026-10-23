package me.cristianpena.facebook


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FacebookBlue = Color(0xFF0866FF)
private val FacebookTextGray = Color(0xFF65676B)
private val FacebookBorderGray = Color(0xFFCCD0D5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacebookRegisterScreen(
    onBackClick: () -> Unit = {},
    onRegisterClick: (
        firstName: String,
        lastName: String,
        contact: String,
        password: String,
        birthDate: String,
        gender: String
    ) -> Unit = { _, _, _, _, _, _ -> },
    onLoginClick: () -> Unit = {}
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Mujer") }

    val genderOptions = listOf("Mujer", "Hombre", "Personalizado")

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Crear una cuenta",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Es rápido y fácil.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RegisterTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    placeholder = "Nombre",
                    modifier = Modifier.weight(1f)
                )
                RegisterTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = "Apellido",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            RegisterTextField(
                value = contact,
                onValueChange = { contact = it },
                placeholder = "Número de celular o correo electrónico",
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(12.dp))


            RegisterTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña nueva",
                keyboardType = KeyboardType.Password,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Fecha de nacimiento",
                fontSize = 12.sp,
                color = FacebookTextGray,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegisterTextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                placeholder = "DD/MM/AAAA",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Género",
                fontSize = 12.sp,
                color = FacebookTextGray,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                genderOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedGender = option }
                    ) {
                        RadioButton(
                            selected = selectedGender == option,
                            onClick = { selectedGender = option },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF5B4B9A)
                            )
                        )
                        Text(text = option, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = {
                    onRegisterClick(
                        firstName, lastName, contact,
                        password, birthDate, selectedGender
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = FacebookBlue)
            ) {
                Text(
                    text = "Registrarte",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "¿Ya tienes una cuenta?",
                fontSize = 13.sp,
                color = FacebookBlue,
                modifier = Modifier.clickable { onLoginClick() }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun RegisterTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = FacebookTextGray,
                fontSize = 14.sp
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        visualTransformation = if (isPassword)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = FacebookBlue,
            unfocusedBorderColor = FacebookBorderGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = FacebookBlue
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFacebookRegisterScreen() {
    FacebookRegisterScreen()
}