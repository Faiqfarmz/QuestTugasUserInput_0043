
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
import java.util.Locale

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

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Gambar header di belakang form
            Image(
                painter = painterResource(R.drawable.gambar),
                contentDescription = stringResource(
                    R.string.biodata_subtitle
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(255.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )


            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.70f)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                ),
                color = white,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(
                            horizontal = 24.dp,
                            vertical = 22.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Judul
                    Text(
                        text = stringResource(R.string.biodata_title),
                        modifier = Modifier.fillMaxWidth(),
                        color = purple,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    // Subjudul
                    Text(
                        text = stringResource(R.string.biodata_subtitle),
                        modifier = Modifier.fillMaxWidth(),
                        color = hintColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Nama depan dan belakang sejajar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BiodataInput(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = stringResource(R.string.first_name),
                            modifier = Modifier.weight(1f)
                        )

                        BiodataInput(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = stringResource(R.string.last_name),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Email memenuhi lebar form
                    BiodataInput(
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(R.string.email),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Tanggal lahir dan pengalaman sejajar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = birthDate,
                                onValueChange = {},
                                readOnly = true,
                                label = {
                                    Text(stringResource(R.string.birth_date))
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                singleLine = true
                            )

                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        val calendar = Calendar.getInstance()

                                        DatePickerDialog(
                                            context,
                                            { _, year, month, day ->
                                                birthDate = String.format(
                                                    java.util.Locale.getDefault(),
                                                    "%02d/%02d/%04d",
                                                    day,
                                                    month + 1,
                                                    year
                                                )
                                            },
                                            calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH)
                                        ).show()
                                    }
                            )
                        }



                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = experience,
                                onValueChange = {},
                                readOnly = true,
                                label = {
                                    Text(
                                        stringResource(R.string.experience),
                                        maxLines = 1
                                    )
                                },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                modifier = Modifier
                                    .menuAnchor(
                                        ExposedDropdownMenuAnchorType.PrimaryNotEditable
                                    )
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                singleLine = true
                            )


                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                listOf(
                                    R.string.experience_beginner to "Beginner",
                                    R.string.experience_intermediate to "Intermediate",
                                    R.string.experience_expert to "Expert"
                                ).forEach { (option, optionText) ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(text = stringResource(id = option))
                                        },
                                        onClick = {
                                            experience = optionText
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Judul pilihan skill
                    Text(
                        text = stringResource(R.string.choose_skill),
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    // Semua skill dalam satu baris
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SkillOption(
                            label = stringResource(R.string.skill_android),
                            checked = androidSkill,
                            onCheckedChange = { androidSkill = it }
                        )

                        SkillOption(
                            label = stringResource(R.string.skill_uiux),
                            checked = uiuxSkill,
                            onCheckedChange = { uiuxSkill = it }
                        )

                        SkillOption(
                            label = stringResource(R.string.skill_web),
                            checked = webSkill,
                            onCheckedChange = { webSkill = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tombol Proceed
                    Button(
                        onClick = {
                            showResult = true
                        },
                        enabled = firstName.isNotBlank() &&
                                lastName.isNotBlank() &&
                                email.isNotBlank() &&
                                birthDate.isNotBlank() &&
                                experience.isNotBlank() &&
                                (androidSkill || uiuxSkill || webSkill),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = purple,
                            disabledContainerColor = colorResource(
                                R.color.biodata_disabled
                            )
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.proceed),
                            color = white,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Hasil biodata
                    if (showResult) {
                        Text(
                            text = "Nama: $firstName $lastName\n" +
                                    "Email: $email\n" +
                                    "Tanggal Lahir: $birthDate\n" +
                                    "Experience: $experience",
                            color = textColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BiodataInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        singleLine = true
    )
}

@Composable
private fun SkillOption(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = label,
            fontSize = 12.sp,
            color = colorResource(R.color.biodata_text)
        )
    }
}
