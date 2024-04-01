package com.example.careerboast.common.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.careerboast.R

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text(
                   text = stringResource(id = R.string.yes),
                   style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}

@Composable
fun FinishedDialog(
    title: String,
    message: String,
    state: Boolean = false,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    if (state) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                Button(
                    onClick = onConfirm,
                ) {
                    Text(
                        text = stringResource(id = R.string.continue_dialog),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(
                        text = stringResource(id = R.string.try_again),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        )
    }
}
