package com.fappslab.tourtip.sample.presentation.compose.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.geometry.RoundRect
import com.fappslab.tourtip.model.TooltipModel
import com.fappslab.tourtip.model.HighlightType
import com.fappslab.tourtip.sample.presentation.compose.component.extension.drawZigzagRect

fun tooltipTitle(): TooltipModel {
    return TooltipModel(
        index = 0,
        title = {
            Text(text = "Custom Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a custom highlight type in the Tourtip library.")
        },
        highlightType = HighlightType.Custom { adjustedBounds ->
            drawZigzagRect(adjustedBounds)
        }
    )
}

fun tooltipIconLauncher(index: Int): TooltipModel {
    return TooltipModel(
        index = index,
        title = {
            Text(text = "Circle Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a circle highlight type in the Tourtip library.")
        },
        highlightType = HighlightType.Circle
    )
}

fun tooltipAppName(): TooltipModel {
    return TooltipModel(
        index = 4,
        title = {
            Text(text = "Rounded Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a rounded highlight type in the Tourtip library.")
        },
        highlightType = HighlightType.Rounded
    )
}

fun tooltipRadioGroupAnim(): TooltipModel {
    return TooltipModel(
        index = 5,
        title = {
            Text(text = "Rounded Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a rounded highlight type in the Tourtip library.")
        },
        highlightType = HighlightType.Rounded
    )
}

fun tooltipStartTourtip(): TooltipModel {
    return TooltipModel(
        index = 6,
        title = {
            Text(text = "Rounded Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a rounded highlight type in the Tourtip library.")
        },
        highlightType = HighlightType.Rounded
    )
}

fun tooltipBottomCheck(): TooltipModel {
    return TooltipModel(
        index = 7,
        title = {
            Text(text = "Circle Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a circle highlight type in the Tourtip library.")
        },
        highlightType = HighlightType.Circle
    )
}

fun tooltipBottomEdit(): TooltipModel {
    return TooltipModel(
        index = 8,
        title = {
            Text(text = "Circle Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a circle highlight type in the Tourtip library.")
        },
        highlightType = HighlightType.Circle
    )
}

fun tooltipFabButton(): TooltipModel {
    return TooltipModel(
        index = 9,
        title = {
            Text(text = "Custom Highlight Example")
        },
        message = {
            Text(text = "This example demonstrates how to use a custom highlight type in the Tourtip library.")
        },
        action = { controller ->
            Button(
                onClick = {
                    controller.finishTourtip()
                }
            ) {
                Text(text = "Finish")
            }
        },
        highlightType = HighlightType.Custom { adjustedBounds ->
            addRoundRect(
                RoundRect(
                    rect = adjustedBounds,
                    radiusX = 50f,
                    radiusY = 50f
                )
            )
        }
    )
}
