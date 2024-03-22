package com.example.careerboast.view.navigation.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerboast.ui.theme.Black


@Composable
fun DrawerHeader() {

}

@Composable
fun DrawerBody(
    items : List<MenuItem>,
    modifier : Modifier = Modifier,
    itemTextStyle : TextStyle = TextStyle(fontSize = 15.sp),
    onItemClick : (MenuItem) -> Unit
) {

    LazyColumn(modifier) {
        items(items) { item ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {

                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = stringResource(id = item.contentDescription)
                )
                Spacer(modifier = Modifier.width(16.dp))



            }

        }
    }

}
