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

package com.fappslab.tourtip.viewmodel

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import com.fappslab.tourtip.model.BoundsRegistry
import com.fappslab.tourtip.model.TooltipModel
import com.fappslab.tourtip.model.StepModel
import com.fappslab.tourtip.model.TourtipController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class TourtipViewModel : ViewModel(), TourtipController, BoundsRegistry {

    private val _state = MutableStateFlow(value = TourtipViewState())
    val state: StateFlow<TourtipViewState> = _state.asStateFlow()

    private val currentState: TourtipViewState
        get() = _state.value

    private fun stepModelHandler() = currentState.run {
        val stepModel = StepModel(
            currentStep = currentStep.inc(),
            totalSteps = tooltipModels.size
        ).takeIf { currentStep < tooltipModels.size.dec() }
        _state.update { it.copy(stepModel = stepModel) }
    }

    override fun updateBounds(model: TooltipModel, bounds: Rect) {
        _state.update { it.updateBoundsState(model, bounds) }
    }

    override fun startTourtip() {
        _state.update { it.startTourtipState() }
        stepModelHandler()
    }

    override fun finishTourtip() = onEnd()

    fun loadSteps() = currentState.run {
        val firstIndex = tooltipModels.keys.minOrNull() ?: return
        val firstDetail = tooltipModels[firstIndex] ?: return
        val firstBounds = indexedBounds[firstIndex] ?: return
        updateBounds(firstDetail, firstBounds)
    }

    fun onNext() {
        _state.update { it.updateOnNextState() }
        stepModelHandler()
    }

    fun onBack() {
        _state.update { it.updateOnBackState() }
        stepModelHandler()
    }

    fun onEnd() {
        _state.update { it.copy(isVisible = false) }
    }
}
