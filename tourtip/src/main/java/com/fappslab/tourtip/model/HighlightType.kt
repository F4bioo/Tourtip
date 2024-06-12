package com.fappslab.tourtip.model

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

/**
 * A sealed class representing the different types of highlights for the Tourtip bubbles.
 */
sealed class HighlightType {

    /**
     * Rectangle highlight type.
     */
    data object Rectangle : HighlightType()

    /**
     * Rounded rectangle highlight type.
     */
    data object Rounded : HighlightType()

    /**
     * Circle highlight type.
     */
    data object Circle : HighlightType()

    /**
     * Custom highlight type that allows drawing custom shapes.
     *
     * @property draw A lambda function that provides the drawing logic for the custom shape.
     *
     * Example usage:
     * ```
     * val customHighlight = HighlightType.Custom { adjustedBounds ->
     *     val path = Path().apply {
     *         val cornerRadius = 50f
     *         // Top left rounded corner
     *         moveTo(adjustedBounds.left + cornerRadius, adjustedBounds.top)
     *         arcTo(
     *             rect = Rect(
     *                 adjustedBounds.left,
     *                 adjustedBounds.top,
     *                 adjustedBounds.left + 2 * cornerRadius,
     *                 adjustedBounds.top + 2 * cornerRadius
     *             ),
     *             startAngleDegrees = 180f,
     *             sweepAngleDegrees = 90f,
     *             forceMoveTo = false
     *         )
     *         // Top edge
     *         lineTo(adjustedBounds.right - cornerRadius, adjustedBounds.top)
     *         // Top right corner
     *         lineTo(adjustedBounds.right, adjustedBounds.top)
     *         lineTo(adjustedBounds.right, adjustedBounds.top + cornerRadius)
     *         // Right edge
     *         lineTo(adjustedBounds.right, adjustedBounds.bottom - cornerRadius)
     *         // Bottom right rounded corner
     *         arcTo(
     *             rect = Rect(
     *                 adjustedBounds.right - 2 * cornerRadius,
     *                 adjustedBounds.bottom - 2 * cornerRadius,
     *                 adjustedBounds.right,
     *                 adjustedBounds.bottom
     *             ),
     *             startAngleDegrees = 0f,
     *             sweepAngleDegrees = 90f,
     *             forceMoveTo = false
     *         )
     *         // Bottom edge
     *         lineTo(adjustedBounds.left + cornerRadius, adjustedBounds.bottom)
     *         // Bottom left corner
     *         lineTo(adjustedBounds.left, adjustedBounds.bottom)
     *         lineTo(adjustedBounds.left, adjustedBounds.bottom - cornerRadius)
     *         // Left edge
     *         lineTo(adjustedBounds.left, adjustedBounds.top + cornerRadius)
     *         close()
     *     }
     *     addPath(path)
     * }
     * ```
     */
    class Custom(val draw: Path.(adjustedBounds: Rect) -> Unit) : HighlightType()
}
