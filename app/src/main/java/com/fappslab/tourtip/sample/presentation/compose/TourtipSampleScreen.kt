package com.fappslab.tourtip.sample.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fappslab.tourtip.compose.TourtipLayout
import com.fappslab.tourtip.compose.extension.tooltipAnchor
import com.fappslab.tourtip.model.TourtipAnimType
import com.fappslab.tourtip.sample.R
import com.fappslab.tourtip.sample.presentation.compose.component.AnimationSelector
import com.fappslab.tourtip.sample.presentation.compose.component.BottomMenu
import com.fappslab.tourtip.sample.presentation.compose.component.IconLauncher
import com.fappslab.tourtip.sample.presentation.compose.component.tooltipAppName
import com.fappslab.tourtip.sample.presentation.compose.component.tooltipIconLauncher
import com.fappslab.tourtip.sample.presentation.compose.component.tooltipRadioGroupAnim
import com.fappslab.tourtip.sample.presentation.compose.component.tooltipStartTourtip
import com.fappslab.tourtip.sample.presentation.compose.component.tooltipTitle

@Composable
fun TourtipSampleScreen() {
    var animType by remember { mutableStateOf(TourtipAnimType.Bouncy) }

    TourtipLayout(
        modifier = Modifier.fillMaxSize(),
        animType = animType,
        onClose = { currentStep ->
            // Handle close for event tracking $currentStep
        },
        onBack = { currentStep ->
            // Handle back for event tracking $currentStep
        },
        onNext = { currentStep ->
            // Handle next for event tracking $currentStep
        }
    ) { controller ->
        TourtipSampleContent(
            animType = animType,
            onAnimChecked = { animType = it },
            onStartClicked = {
                controller.startTourtip()
            }
        )
    }
}

@Composable
fun ColumnScope.TourtipSampleContent(
    animType: TourtipAnimType,
    onAnimChecked: (TourtipAnimType) -> Unit,
    onStartClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .weight(1f)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .tooltipAnchor { tooltipTitle() },
            text = "Let's start the Tourtip Sample!",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(Modifier.size(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconLauncher(
                modifier = Modifier
                    .size(50.dp)
                    .tooltipAnchor { tooltipIconLauncher(index = 1) },
            )
            IconLauncher(
                modifier = Modifier
                    .size(40.dp)
                    .tooltipAnchor { tooltipIconLauncher(index = 2) },
            )
            IconLauncher(
                modifier = Modifier
                    .size(30.dp)
                    .tooltipAnchor { tooltipIconLauncher(index = 3) },
            )
            Text(
                modifier = Modifier.tooltipAnchor {
                    tooltipAppName()
                },
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Spacer(Modifier.size(32.dp))
        Row(
            modifier = Modifier.tooltipAnchor {
                tooltipRadioGroupAnim()
            }
        ) {

            AnimationSelector(
                currentAnimType = animType,
                onAnimationSelected = onAnimChecked
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .tooltipAnchor { tooltipStartTourtip() }
                .size(52.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = onStartClicked
        ) {
            Text(text = "Start")
        }
    }
    BottomMenu()
}

@Preview(showSystemUi = true)
@Composable
private fun TourtipSampleScreenPreview() {
    TourtipSampleScreen()
}
