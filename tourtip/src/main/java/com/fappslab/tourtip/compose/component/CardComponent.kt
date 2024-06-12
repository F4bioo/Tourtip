package com.fappslab.tourtip.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.fappslab.tourtip.model.StepModel
import com.fappslab.tourtip.theme.TourtipTheme

@Composable
internal fun CardComponent(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)?,
    message: @Composable () -> Unit,
    action: @Composable (() -> Unit)?,
    onClose: (() -> Unit)?,
    onBack: () -> Unit,
    onNext: () -> Unit,
    stepModel: StepModel?,
    backgroundColor: Color
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = TourtipTheme.elevation.extra
        ),
        shape = RoundedCornerShape(size = TourtipTheme.radius.large),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        )
    ) {

        Column(
            modifier = Modifier.padding(TourtipTheme.dimen.dp16)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {

                title?.let { content ->
                    Box(
                        modifier = Modifier.weight(weight = 1f),
                    ) {
                        CompositionLocalProvider(
                            LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant,
                            LocalTextStyle provides MaterialTheme.typography.titleSmall,
                            content = content
                        )
                    }
                }
                onClose?.let { onClick ->
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = onClick),
                        imageVector = Icons.Default.Close,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Close",
                    )
                }
            }
            Box(
                modifier = Modifier.textVerticalPadding(
                    subheadExists = title != null,
                    actionExists = onClose != null
                )
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant,
                    LocalTextStyle provides MaterialTheme.typography.bodyMedium,
                    content = message
                )
            }
            if (action != null) {
                Box(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides MaterialTheme.colorScheme.primary,
                        LocalTextStyle provides MaterialTheme.typography.labelLarge,
                        content = action
                    )
                }
            } else stepModel?.let { model ->
                Spacer(modifier = Modifier.size(TourtipTheme.dimen.dp8))
                StepComponent(
                    stepModel = model,
                    onBack = onBack,
                    onNext = onNext
                )
            }
        }
    }
}

@Stable
@Composable
private fun Modifier.textVerticalPadding(
    subheadExists: Boolean,
    actionExists: Boolean
): Modifier {
    return if (!subheadExists && !actionExists) {
        this.padding(vertical = TourtipTheme.dimen.dp4)
    } else {
        this
            .paddingFromBaseline(top = TourtipTheme.dimen.dp24)
            .padding(bottom = TourtipTheme.dimen.dp16)
    }
}

@Preview(showBackground = true)
@Composable
private fun CardComponentPreview() {
    Column(
        modifier = Modifier.padding(TourtipTheme.dimen.dp16)
    ) {

        CardComponent(
            onClose = {},
            title = {
                Text(text = "Lorem ipsum dolor sit amet")
            },
            message = {
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.")
            },
            stepModel = StepModel(
                currentStep = 1,
                totalSteps = 3
            ),
            onBack = {},
            onNext = {},
            action = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(modifier = Modifier.size(TourtipTheme.dimen.dp16))
        CardComponent(
            title = {
                Text(text = "Lorem ipsum dolor sit amet")
            },
            message = {
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.")
            },
            onClose = {},
            stepModel = null,
            onBack = {},
            onNext = {},
            action = {
                Button(
                    onClick = {}
                ) {
                    Text(text = "Finish")
                }
            },
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}
