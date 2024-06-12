package com.fappslab.tourtip.model

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable

/**
 * Data class representing the details of a bubble in the Tourtip guided tour.
 *
 * @property index The index of the bubble in the tour sequence.
 * @property title The optional composable function that displays the title of the bubble.
 * @property message The composable function that displays the message of the bubble.
 * @property action The optional composable function that provides an additional action, such as a button to finish the tour.
 * @property highlightType The type of highlight to use for this bubble.
 *
 * Example usage:
 * ```
 * // Simple bubble with title and message
 * val bubbleDetail = BubbleDetail(
 *     index = 0,
 *     title = { Text("Welcome") },
 *     message = { Text("This is the first step of the tour.") },
 *     highlightType = HighlightType.Circle
 * )
 *
 * // Bubble with an action to finish the tour
 * val bubbleDetailWithAction = BubbleDetail(
 *     index = 1,
 *     title = { Text("Finish") },
 *     message = { Text("This is the final step. Click the button to finish.") },
 *     action = { controller ->
 *         Button(onClick = { controller.finishTourtip() }) {
 *             Text("Finish Tour")
 *         }
 *     },
 *     highlightType = HighlightType.Rounded
 * )
 * ```
 */
data class BubbleDetail(
    @IntRange(from = 0)
    val index: Int,
    val title: @Composable (() -> Unit)? = null,
    val message: @Composable () -> Unit,
    val action: @Composable ((controller: TourtipController) -> Unit)? = null,
    val highlightType: HighlightType,
)
