package com.fappslab.tourtip.compose.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fappslab.tourtip.model.BubbleDetail
import com.fappslab.tourtip.theme.defaults.LocalBoundsRegistry

/**
 * Anchors a bubble element to a position based on the bounds of the calling layout.
 *
 * This extension function allows a bubble to be positioned based on the layout coordinates
 * of the element to which this modifier is applied. It uses a registry to keep track of the
 * bounds of the element, which can then be used to position the bubble accurately.
 *
 * Usage:
 * To use this function, you need to provide a model function that returns a `BubbleDetail` object.
 * The `BubbleDetail` class should contain the necessary information about the bubble, including its index.
 *
 * Example:
 * ```
 * Text(
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .bubbleAnchor {
 *             BubbleDetail(
 *                 index = 0,
 *                 title = { Text("Step 1") },
 *                 message = { Text("This is the first step of the tour.") },
 *                 highlightType = HighlightType.Rounded
 *             )
 *         },
 *     text = "Let's start the Tourtip Sample!"
 * )
 * ```
 *
 * @param model A lambda function that returns a `BubbleDetail` object. The `BubbleDetail` should
 * include an index which is used to remember the positioning lambda.
 * @return A modified `Modifier` with the bubble anchor behavior applied.
 */
fun Modifier.bubbleAnchor(model: () -> BubbleDetail): Modifier = composed {
    val registry = LocalBoundsRegistry.current
    val boundsUpdated = remember(model().index) {
        { coordinates: LayoutCoordinates ->
            val bounds = coordinates.boundsInRoot()
            registry.updateBounds(model = model(), bounds = bounds)
        }
    }
    onGloballyPositioned(boundsUpdated)
}

internal fun Rect.isTargetZero(): Boolean {
    return this == Rect.Zero
}

@Composable
internal fun Float.toDp(): Dp {
    val density = LocalDensity.current.density
    return (this / density).dp
}

@Composable
internal fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return value * density
}
