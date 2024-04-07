package com.example.careerboast.view.navigation.drawer

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.careerboast.R
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.ui.theme.Red
import com.example.careerboast.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    drawerState: DrawerState? = null,
    navigationIcon: (@Composable () -> Unit)? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        title = {
            val annotateString = buildAnnotatedString {
                withStyle(
                    TextStyle(
                        fontSize = 20.sp,
                        color = Blue,
                        fontWeight = FontWeight.Bold
                    ).toSpanStyle()
                ) {
                    append(context.getString(R.string.career))
                }
                withStyle(
                    TextStyle(
                        fontSize = 20.sp,
                        color = Red,
                        fontWeight = FontWeight.Bold
                    ).toSpanStyle()
                ) {
                    append(context.getString(R.string.boast))
                }
            }
            Text(
                text = annotateString,
            )
        },
        modifier = Modifier.background(
            color = White
        ),
        navigationIcon = {
            if (drawerState != null && navigationIcon == null) {
                DrawerIcon(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            } else {
                navigationIcon?.invoke()
            }
        }
    )
}

@Composable
private fun DrawerIcon(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null,
        )
    }
}