package com.fappslab.tourtip.theme.defaults

import androidx.compose.runtime.staticCompositionLocalOf
import com.fappslab.tourtip.model.BoundsRegistry
import com.fappslab.tourtip.theme.Dimens
import com.fappslab.tourtip.theme.Elevation
import com.fappslab.tourtip.theme.Opacity
import com.fappslab.tourtip.theme.Radius

internal val LocalBoundsRegistry = staticCompositionLocalOf<BoundsRegistry> {
    error("No BoundsRegistry provided")
}

internal val LocalPlutoOpacity = staticCompositionLocalOf {
    Opacity
}

internal val LocalPlutoRadius = staticCompositionLocalOf {
    Radius
}

internal val LocalPlutoSizing = staticCompositionLocalOf {
    Dimens
}

internal val LocalPlutoElevation = staticCompositionLocalOf {
    Elevation
}
