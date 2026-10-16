package com.example.tugasprak5

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataScreen() {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }

    val purple = colorResource(R.color.biodata_purple)
    val backgroundColor = colorResource(R.color.biodata_background)
    val white = colorResource(R.color.biodata_white)
    val textColor = colorResource(R.color.biodata_text)
    val hintColor = colorResource(R.color.biodata_hint)
    val calendar = Calendar.getInstance()

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )