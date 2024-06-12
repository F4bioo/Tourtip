package com.fappslab.tourtip.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.fappslab.tourtip.theme.defaults.LocalPlutoElevation
import com.fappslab.tourtip.theme.defaults.LocalPlutoOpacity
import com.fappslab.tourtip.theme.defaults.LocalPlutoRadius
import com.fappslab.tourtip.theme.defaults.LocalPlutoSizing

@Composable
internal fun TourtipTheme(
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalPlutoOpacity provides Opacity,
        LocalPlutoRadius provides Radius,
        LocalPlutoSizing provides Dimens,
        LocalPlutoElevation provides Elevation
    ) {

        MaterialTheme(
            content = content
        )
    }
}

internal object TourtipTheme {

    val opacity: Opacity
        @Composable
        get() = LocalPlutoOpacity.current

    val radius: Radius
        @Composable
        get() = LocalPlutoRadius.current

    val dimen: Dimens
        @Composable
        get() = LocalPlutoSizing.current

    val elevation: Elevation
        @Composable
        get() = LocalPlutoElevation.current
}
