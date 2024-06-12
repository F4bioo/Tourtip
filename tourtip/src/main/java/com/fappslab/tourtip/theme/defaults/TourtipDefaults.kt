package com.fappslab.tourtip.theme.defaults

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fappslab.tourtip.theme.TourtipTheme

internal object TourtipDefaults {

    val scrimColor: Color
        @Composable
        get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.onSurface.copy(alpha = TourtipTheme.opacity.faint)
        } else MaterialTheme.colorScheme.scrim.copy(alpha = TourtipTheme.opacity.frosted)

    val backgroundColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant
}
