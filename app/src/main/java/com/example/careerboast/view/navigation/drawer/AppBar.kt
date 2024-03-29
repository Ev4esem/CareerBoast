package com.example.careerboast.view.navigation.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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

    TopAppBar(

        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.career),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Blue,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = stringResource(id = R.string.boast),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Red,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        modifier = Modifier.background(
            color = White
        ),
        navigationIcon = {

            if (drawerState != null && navigationIcon == null) {
                DrawerIcon(drawerState = drawerState)
            } else {
                navigationIcon?.invoke()
            }
        }
    )

}

@Composable
private fun DrawerIcon(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    IconButton(onClick = {
        coroutineScope.launch {
            drawerState.open()
        }
    }) {



        Icon(
            Icons.Rounded.Menu,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = ""
        )




    }
}