package com.example.ktmavenpublishtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktmavenpublishtest.ui.theme.KtMavenPublishTestTheme
import com.example.mathmodule.MathHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtMavenPublishTestTheme {
                MainPage()
            }
        }
    }
}

@Composable
fun MainPage() {
    var inputA by remember { mutableStateOf("") }
    var inputB by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val helper = MathHelper()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 50.dp)
    ) {
        Column {
            OutlinedTextField(
                value = inputA,
                onValueChange = { inputA = it },
                label = { Text("輸入數字 A") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = inputB,
                onValueChange = { inputB = it },
                label = { Text("輸入數字 B") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val a = inputA.toIntOrNull() ?: 0
                    val b = inputB.toIntOrNull() ?: 0
                    result = "結果：${helper.add(a, b)}"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("計算加總")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = result,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtMavenPublishTestTheme {
        MainPage()
    }
}