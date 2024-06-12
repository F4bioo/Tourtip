package com.fappslab.tourtip.compose.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.fappslab.tourtip.compose.extension.toDp
import com.fappslab.tourtip.compose.extension.toPx
import com.fappslab.tourtip.model.BubblePosition
import com.fappslab.tourtip.model.ShapeType
import com.fappslab.tourtip.model.StepModel
import com.fappslab.tourtip.model.TourtipAnimType
import com.fappslab.tourtip.theme.TourtipTheme

@Composable
internal fun BubbleComponent(
    targetBounds: Rect,
    title: @Composable (() -> Unit)?,
    message: @Composable () -> Unit,
    action: @Composable (() -> Unit)?,
    onClose: (() -> Unit)?,
    onBack: () -> Unit,
    onNext: () -> Unit,
    stepModel: StepModel?,
    backgroundColor: Color,
    animType: TourtipAnimType
) {
    var cardSize by remember { mutableStateOf(Size.Zero) }
    val (isCaretUp, caretMargin, caretWidth, caretHeight) = calculateAlignment(targetBounds)

    val initialCardYOffset = initialCardYOffset(
        targetBounds = targetBounds,
        cardSize = cardSize,
        caretHeight = caretHeight,
        caretMargin = caretMargin,
        isCaretUp = isCaretUp
    )

    val cardYOffset by animateDpAsState(
        targetValue = initialCardYOffset,
        animationSpec = animType.animOf(animType),
        label = animType.label
    )

    val (caretXOffset, caretYOffset, shapeType) = calculateCaretPosition(
        targetBounds = targetBounds,
        cardSize = cardSize,
        cardYOffset = cardYOffset,
        caretHeight = caretHeight,
        caretWidth = caretWidth,
        isCaretUp = isCaretUp
    )

    CardComponent(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(x = 0, y = cardYOffset.roundToPx()) }
            .padding(horizontal = TourtipTheme.dimen.dp12)
            .onGloballyPositioned { coordinates ->
                cardSize = coordinates.size.toSize()
            },
        title = title,
        message = message,
        action = action,
        onClose = onClose,
        onBack = onBack,
        onNext = onNext,
        stepModel = stepModel,
        backgroundColor = backgroundColor
    )
    CaretComponent(
        isCaretUp = isCaretUp,
        caretWidth = caretWidth,
        caretHeight = caretHeight,
        xOffset = caretXOffset,
        yOffset = caretYOffset,
        targetBounds = targetBounds,
        color = backgroundColor,
        shapeType = shapeType
    )
}

@Composable
private fun calculateAlignment(targetBounds: Rect): BubblePosition {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp.toPx()
    val isCaretUp = targetBounds.top <= screenHeight / 2

    return BubblePosition(isCaretUp = isCaretUp)
}

@Composable
fun initialCardYOffset(
    targetBounds: Rect,
    cardSize: Size,
    caretHeight: Dp,
    caretMargin: Dp,
    isCaretUp: Boolean
): Dp {
    val initialCardYOffset = if (isCaretUp) {
        targetBounds.bottom + caretHeight.toPx() + caretMargin.toPx()
    } else targetBounds.top - cardSize.height - caretHeight.toPx() - caretMargin.toPx()

    return initialCardYOffset.toDp()
}

@Composable
private fun calculateCaretPosition(
    targetBounds: Rect,
    cardSize: Size,
    cardYOffset: Dp,
    caretHeight: Dp,
    caretWidth: Dp,
    isCaretUp: Boolean
): Triple<Float, Float, ShapeType> {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp.toPx()

    val xOffset = (targetBounds.left + targetBounds.width / 2) - (caretWidth.toPx() / 2)
    val caretYOffset = if (isCaretUp) {
        cardYOffset.toPx() - caretHeight.toPx()
    } else cardYOffset.toPx() + cardSize.height

    val shapeType = calculateShapeType(xOffset, screenWidth)

    val caretXOffset = when (shapeType) {
        ShapeType.Center -> xOffset
        ShapeType.LeftBounds -> (xOffset + caretWidth.toPx())
            .coerceAtLeast(minimumValue = 0f)

        ShapeType.RightBounds -> (xOffset - caretWidth.toPx())
            .coerceAtMost(maximumValue = screenWidth - caretWidth.toPx())
    }

    return Triple(caretXOffset, caretYOffset, shapeType)
}

@Composable
private fun calculateShapeType(
    xOffset: Float,
    screenWidth: Float
): ShapeType {
    val leftBoundaryEnd = screenWidth * 0.15
    val rightBoundaryStart = screenWidth * 0.85

    return when {
        xOffset < leftBoundaryEnd -> ShapeType.LeftBounds
        xOffset > rightBoundaryStart -> ShapeType.RightBounds
        else -> ShapeType.Center
    }
}
