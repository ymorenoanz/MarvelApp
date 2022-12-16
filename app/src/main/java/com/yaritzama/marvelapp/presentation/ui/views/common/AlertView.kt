package com.yaritzama.marvelapp.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertView(openDialog: Boolean, title: String = "Marvel App", message: String?, onClickButton:() -> Unit){
    if(openDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(text = title)
            },
            text = {
                Text(message?:"")
            },
            confirmButton = {
                Button(

                    onClick = {
                        onClickButton()
                    }) {
                    Text("Ok")
                }
            },
        )
    }

}


