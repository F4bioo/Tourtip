package com.fappslab.tourtip.model

import androidx.compose.ui.geometry.Rect

internal interface BoundsRegistry {
    fun updateBounds(model: TooltipModel, bounds: Rect)
}
