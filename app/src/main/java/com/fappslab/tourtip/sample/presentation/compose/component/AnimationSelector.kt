package com.fappslab.tourtip.sample.presentation.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.fappslab.tourtip.model.TourtipAnimType

@Composable
fun AnimationSelector(
    currentAnimType: TourtipAnimType,
    onAnimationSelected: (TourtipAnimType) -> Unit
) {

    Column {
        TourtipAnimType.entries.forEach { animType ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.onSurface
                    ),
                    selected = currentAnimType == animType,
                    onClick = { onAnimationSelected(animType) }
                )
                Text(text = animType.label)
            }
        }
    }
}
