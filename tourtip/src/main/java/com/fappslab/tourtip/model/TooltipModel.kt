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

package com.fappslab.tourtip.model

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable

/**
 * Data class representing the model of a tooltip.
 *
 * @property index The index of the tooltip in the tour sequence.
 * @property title The optional composable function that displays the title of the tooltip.
 * @property message The composable function that displays the message of the tooltip.
 * @property action The optional composable function that provides an additional action, such as a button to finish the tour.
 * @property highlightType The type of highlight to use for this tooltip.
 *
 * Example usage:
 * ```
 * // Simple tooltip with title, message and a circle highlight
 * val model = TooltipModel(
 *     index = 0,
 *     title = { Text("Welcome") },
 *     message = { Text("This is the first step of the tour.") },
 *     highlightType = HighlightType.Circle
 * )
 *
 * // Tooltip with title, message, action and a rounded highlight
 * val modelWithFinishAction = TooltipModel(
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
data class TooltipModel(
    @IntRange(from = 0)
    val index: Int,
    val title: @Composable (() -> Unit)? = null,
    val message: @Composable () -> Unit,
    val action: @Composable ((controller: TourtipController) -> Unit)? = null,
    val highlightType: HighlightType,
)
