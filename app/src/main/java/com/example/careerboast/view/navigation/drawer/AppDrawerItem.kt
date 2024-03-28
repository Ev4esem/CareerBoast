package com.example.careerboast.view.navigation.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.careerboast.common.ext.basisPadding
import com.example.careerboast.common.ext.spacer
import com.example.careerboast.ui.theme.GrayMenuItem
import com.example.careerboast.ui.theme.White

@Composable
fun <T> AppDrawerItem(item : MenuItem<T>, onClick : (options : T) -> Unit) {

    Surface(
        color = White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        onClick = { onClick(item.drawerOption) }
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Icon(
                painter = painterResource(item.icon),
                contentDescription = stringResource(item.contentDescription)
            )

            Spacer(modifier = Modifier.spacer())
            Text(
                text = stringResource(id = item.title),
                style = MaterialTheme.typography.bodyMedium
            )

        }

    }

}