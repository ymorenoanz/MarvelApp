package com.yaritzama.marvelapp.core.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput


fun Modifier.gesturesDisabled(disabled: Boolean = true) =
    if (disabled) {
        pointerInput(Unit) {
            awaitPointerEventScope {
                var eventReceived = true
                do {
                    val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                    eventReceived = event.changes.isNotEmpty()
                    event.changes.forEach(PointerInputChange::consume)
                } while (eventReceived)
            }
        }
    } else {
        this
    }
