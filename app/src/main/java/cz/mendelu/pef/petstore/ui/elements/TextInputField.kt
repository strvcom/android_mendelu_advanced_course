@file:OptIn(ExperimentalMaterial3Api::class)

package cz.mendelu.pef.petstore.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    value: String,
    testTagErrorText: String,
    hint: String,
    modifier: Modifier = Modifier,
    leadingIcon: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    onValueChange: ((String) -> Unit),
    errorMessage: String? = null
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        value = value,
        label = { Text(text = hint) },
        onValueChange = onValueChange,
        visualTransformation =
        if (keyboardType == KeyboardType.Password)
            if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation()
        else
            VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = false,
        maxLines = 1,
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = description,
                        tint = Color.Black
                    )
                }
            }
        },

        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    tint = Color.Black,
                    contentDescription = null
                )
            }
        } else {
            null
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Blue,
            focusedBorderColor = if (!errorMessage.isNullOrEmpty()) Red else Blue,
            unfocusedBorderColor = if (!errorMessage.isNullOrEmpty()) Red else Gray,
            focusedLabelColor = if (!errorMessage.isNullOrEmpty()) Red else Gray,
        ),
    )

    if (errorMessage.isNullOrEmpty().not()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 4.dp)
                .testTag(testTagErrorText),
            text = errorMessage!!,
            color = Red,
            textAlign = TextAlign.Start,
            fontSize = 11.sp
        )
    }
}