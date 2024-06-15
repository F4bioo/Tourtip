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
