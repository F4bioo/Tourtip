package com.fappslab.tourtip.compose.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.fappslab.tourtip.compose.extension.isTargetZero
import com.fappslab.tourtip.model.HighlightType
import com.fappslab.tourtip.model.OverlayModel
import com.fappslab.tourtip.theme.TourtipTheme

@Composable
internal fun FocusOverlayComponent(
    overlayModel: OverlayModel,
    onClickOut: () -> Unit,
    scrimColor: Color
) {
    val (padding, outerRadius, targetBounds, highlightType) = overlayModel

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onPress = { onClickOut() })
            }
    ) {
        val adjustedBounds = calculateAdjustedBounds(targetBounds, padding)
        val path = Path().apply {
            addRect(Rect(left = 0f, top = 0f, size.width, size.height))
            if (!targetBounds.isTargetZero()) {
                when (highlightType) {
                    is HighlightType.Custom -> highlightType.draw(this, adjustedBounds)
                    HighlightType.Rectangle -> addRect(adjustedBounds)
                    HighlightType.Circle -> addOval(adjustedBounds)
                    HighlightType.Rounded -> addRoundRect(
                        RoundRect(
                            rect = adjustedBounds,
                            radiusX = outerRadius.toPx(),
                            radiusY = outerRadius.toPx()
                        )
                    )
                }
                fillType = PathFillType.EvenOdd
            }
        }
        drawPath(
            path = path,
            color = scrimColor
        )
    }
}

private fun DrawScope.calculateAdjustedBounds(
    targetBounds: Rect,
    padding: Dp
): Rect {
    return Rect(
        left = targetBounds.left - padding.toPx(),
        top = targetBounds.top - padding.toPx(),
        right = targetBounds.right + padding.toPx(),
        bottom = targetBounds.bottom + padding.toPx()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FocusOverlayComponentPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(TourtipTheme.dimen.dp16),
            text = "Lorem ipsum dolor"
        )
        FocusOverlayComponent(
            overlayModel = OverlayModel(
                targetBounds = Rect(left = 35f, top = 125f, right = 370f, bottom = 10f)
            ),
            onClickOut = {},
            scrimColor = Color.Black.copy(alpha = TourtipTheme.opacity.medium)
        )
    }
}
