package com.fappslab.tourtip.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fappslab.tourtip.compose.component.TourtipComponent
import com.fappslab.tourtip.model.TourtipAnimType
import com.fappslab.tourtip.model.TourtipController
import com.fappslab.tourtip.theme.TourtipTheme
import com.fappslab.tourtip.theme.defaults.TourtipDefaults

/**
 * A composable function that provides a layout for displaying a guided tooltip tour.
 *
 * @param modifier A [Modifier] for this layout. Default is [Modifier].
 * @param onBack A callback invoked when the "back" action is triggered. Default is an empty function.
 * @param onNext A callback invoked when the "next" action is triggered. Default is an empty function.
 * @param onClose A callback invoked when the "close" action is triggered. Default is null.
 * @param onClickOut A callback invoked when a click outside the tooltip is detected. Default is null.
 * @param animType The type of animation to use for tooltip transitions. Default is [TourtipAnimType.Bouncy]. See other animations in [TourtipAnimType].
 * @param scrimColor The color of the scrim background. Default is [TourtipDefaults.scrimColor].
 * @param backgroundColor The background color of the tooltip. Default is [TourtipDefaults.backgroundColor].
 * @param content The composable content that the tour will guide through, with access to the [TourtipController].
 *
 * Example usage:
 * ```
 * TourtipLayout(
 *     onBack = { currentStep ->
 *         // Handle back action for event tracking $currentStep
 *     },
 *     onNext = { currentStep ->
 *         // Handle next action for event tracking $currentStep
 *     },
 *     onClose = { currentStep ->
 *         // Handle close action for event tracking $currentStep
 *     },
 *     onClickOut = { currentStep ->
 *        // Handle click-out action for event tracking $currentStep
 *     }
 * ) { controller ->
 *
 *     // Screen content
 *     Button(onClick = { controller.startTourtip() }) {
 *         Text("Start Tour")
 *     }
 * }
 * ```
 */
@Composable
fun TourtipLayout(
    modifier: Modifier = Modifier,
    onBack: (currentStep: Int) -> Unit = {},
    onNext: (currentStep: Int) -> Unit = {},
    onClose: ((currentStep: Int) -> Unit)? = null,
    onClickOut: ((currentStep: Int) -> Unit)? = null,
    animType: TourtipAnimType = TourtipAnimType.Bouncy,
    scrimColor: Color = TourtipDefaults.scrimColor,
    backgroundColor: Color = TourtipDefaults.backgroundColor,
    content: @Composable ColumnScope.(controller: TourtipController) -> Unit
) {

    TourtipTheme {

        TourtipComponent(
            onClose = onClose,
            onBack = onBack,
            onNext = onNext,
            onClickOut = onClickOut,
            animType = animType,
            scrimColor = scrimColor,
            backgroundColor = backgroundColor
        ) { viewModel ->

            Column(
                modifier = modifier
            ) {
                content(viewModel)
            }
        }
    }
}
