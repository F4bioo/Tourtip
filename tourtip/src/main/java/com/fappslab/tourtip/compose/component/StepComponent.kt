package com.fappslab.tourtip.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fappslab.tourtip.R
import com.fappslab.tourtip.model.StepModel
import com.fappslab.tourtip.theme.TourtipTheme

@Composable
internal fun StepComponent(
    stepModel: StepModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(enabled = stepModel.isBackButtonEnabled()) { onBack() },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = if (stepModel.isBackButtonEnabled()) {
                MaterialTheme.colorScheme.primary
            } else MaterialTheme.colorScheme.onBackground.copy(alpha = TourtipTheme.opacity.frosted),
            contentDescription = "Back",
        )
        Spacer(modifier = Modifier.size(TourtipTheme.dimen.dp4))
        Text(
            text = stringResource(
                id = R.string.tourtip_step_button,
                stepModel.currentStep.minimumValue(),
                stepModel.totalSteps.minimumValue()
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.size(TourtipTheme.dimen.dp4))
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(enabled = stepModel.isNextButtonEnabled()) { onNext() },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            tint = if (stepModel.isNextButtonEnabled()) {
                MaterialTheme.colorScheme.primary
            } else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            contentDescription = "Next",
        )
    }
}

private fun StepModel.isBackButtonEnabled(): Boolean {
    return currentStep.minimumValue() > 1
}

private fun StepModel.isNextButtonEnabled(): Boolean {
    return currentStep.minimumValue() < totalSteps.minimumValue()
}

private fun Int.minimumValue(): Int {
    return coerceAtLeast(minimumValue = 1)
}

@Preview(showBackground = true)
@Composable
private fun StepComponentPreview() {
    StepComponent(
        stepModel = StepModel(
            currentStep = 1,
            totalSteps = 3
        ),
        onBack = {},
        onNext = {},
    )
}
