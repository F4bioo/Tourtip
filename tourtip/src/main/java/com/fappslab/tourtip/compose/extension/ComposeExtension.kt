/*
 * Copyright 2024 Fabio Marinho
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.fappslab.tourtip.model.TooltipModel
import com.fappslab.tourtip.theme.defaults.LocalBoundsRegistry

/**
 * Anchors a tooltip element to a position based on the bounds of the calling layout.
 *
 * This extension function allows a tooltip to be positioned based on the layout coordinates
 * of the element to which this modifier is applied. It uses a registry to keep track of the
 * bounds of the element, which can then be used to position the tooltip accurately.
 *
 * Usage:
 * To use this function, you need to provide a model function that returns a `TooltipModel` object.
 * The `TooltipModel` class should contain the necessary information about the tooltip, including its index.
 *
 * Example:
 * ```
 * Text(
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .tooltipAnchor {
 *             TooltipModel(
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
 * @param model A lambda function that returns a `TooltipModel` object. The `TooltipModel` should
 * include an index which is used to remember the positioning lambda.
 * @return A modified `Modifier` with the tooltip anchor behavior applied.
 */
fun Modifier.tooltipAnchor(model: () -> TooltipModel): Modifier = composed {
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
