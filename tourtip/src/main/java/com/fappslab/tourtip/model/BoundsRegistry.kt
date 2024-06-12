package com.fappslab.tourtip.model

import androidx.compose.ui.geometry.Rect

internal interface BoundsRegistry {
    fun updateBounds(model: BubbleDetail, bounds: Rect)
}
