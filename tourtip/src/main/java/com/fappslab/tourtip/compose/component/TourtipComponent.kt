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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fappslab.tourtip.model.TourtipAnimType
import com.fappslab.tourtip.theme.defaults.LocalBoundsRegistry
import com.fappslab.tourtip.viewmodel.TourtipViewModel

@Composable
internal fun TourtipComponent(
    onBack: (currentStep: Int) -> Unit,
    onNext: (currentStep: Int) -> Unit,
    onClose: ((currentStep: Int) -> Unit)?,
    onClickOut: ((currentStep: Int) -> Unit)?,
    scrimColor: Color,
    backgroundColor: Color,
    animType: TourtipAnimType,
    content: @Composable (TourtipViewModel) -> Unit
) {
    val viewModel: TourtipViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSteps()
    }
    CompositionLocalProvider(LocalBoundsRegistry provides viewModel) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            content(viewModel)
            if (state.isVisible) {
                FocusOverlayComponent(
                    scrimColor = scrimColor,
                    overlayModel = state.overlayModel,
                    onClickOut = { onClickOut?.let { it(state.currentStep); viewModel.onEnd() } }
                )
                TooltipComponent(
                    animType = animType,
                    stepModel = state.stepModel,
                    onClose = onClose?.let { { it(state.currentStep); viewModel.onEnd() } },
                    onBack = { onBack(state.currentStep); viewModel.onBack() },
                    onNext = { onNext(state.currentStep); viewModel.onNext() },
                    title = state.tooltipModels[state.currentStep]?.title,
                    message = state.tooltipModels[state.currentStep]?.message ?: {},
                    targetBounds = state.overlayModel.targetBounds,
                    backgroundColor = backgroundColor,
                    action = state.tooltipModels[state.currentStep]?.action?.let { action ->
                        { action.invoke(viewModel) }
                    }
                )
            }
        }
    }
}
