package com.example.careerboast.view.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.CareerBoastTheme
import com.example.careerboast.view.navigation.drawer.AppBar
import com.example.careerboast.view.navigation.drawer.items
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareerBoastApp(
    modifier : Modifier = Modifier,
    appState : CareerBoastAppState = rememberCareerBoastAppState(),
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    CareerBoastTheme {

        Surface(color = MaterialTheme.colorScheme.background) {

            val scope = rememberCoroutineScope()
            var selectedItemIndex by rememberSaveable {
                mutableIntStateOf(0)
            }

            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet {
                        Spacer(modifier = modifier.height(16.dp))
                        items.forEachIndexed { index, menuItem ->
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = stringResource(menuItem.title),
                                        color = Black,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.weight(1f),
                                        fontSize = 15.sp
                                    )
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = menuItem.icon),
                                        contentDescription = stringResource(id = menuItem.contentDescription)
                                    )
                                }

                            )
                        }
                    }
                },
                drawerState = drawerState,
                modifier = modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            ) {
                Scaffold(
                    topBar = {
                        AppBar {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    },
                    modifier = modifier
                        .navigationBarsPadding(),
                ) {
                    CareerBoastNavHost(
                        modifier = modifier.padding(it),
                        navController = appState.navController,
                        appState = appState
                    )

                }
            }
        }

    }

}