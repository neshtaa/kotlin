package com.example.my_app_compose

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.my_app_compose.ui.theme.My_app_composeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    val colors = lightColors(
        surface = Color(0xff121212),
        background = Color(0xff242424),
        onPrimary = Color(0xffffffff),
        secondary = Color(0xff737373),
        primary = Color(0xff6200EE)
    )
    MaterialTheme(colors = colors, content = content)
}


val BackgroundColor = Color(0xff121212)
val ItemBackgroundColor = Color(0xff202124)
val LineGray = Color(0xff242424)
val TextSecondary = Color(0xff737373)
val TextPrimary = Color(0xffffffff)


