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
import com.fappslab.tourtip.model.TooltipModel
import com.fappslab.tourtip.model.HighlightType
import com.fappslab.tourtip.model.OverlayModel
import com.fappslab.tourtip.model.StepModel

internal data class TourtipViewState(
    val currentStep: Int = 0,
    val isVisible: Boolean = false,
    val stepModel: StepModel? = null,
    val indexedBounds: Map<Int, Rect> = emptyMap(),
    val highlightTypes: Map<Int, HighlightType> = emptyMap(),
    val tooltipModels: Map<Int, TooltipModel> = emptyMap(),
    val overlayModel: OverlayModel = OverlayModel()
) {
    private fun updateOverlayModelState(): TourtipViewState {
        val updatedOverlayModel = overlayModel.copy(
            targetBounds = indexedBounds[currentStep] ?: Rect.Zero,
            highlightType = highlightTypes[currentStep] ?: HighlightType.Rounded
        )
        return copy(overlayModel = updatedOverlayModel)
    }

    fun updateBoundsState(model: TooltipModel, bounds: Rect): TourtipViewState {
        return copy(
            indexedBounds = indexedBounds.toMutableMap()
                .apply { put(model.index, bounds) },
            highlightTypes = highlightTypes.toMutableMap()
                .apply { put(model.index, model.highlightType) },
            tooltipModels = tooltipModels.toMutableMap()
                .apply { put(model.index, model) }
        ).updateOverlayModelState()
    }

    fun startTourtipState(): TourtipViewState {
        return copy(isVisible = true, currentStep = 0).updateOverlayModelState()
    }

    fun updateOnNextState(): TourtipViewState {
        return if (currentStep < tooltipModels.size.dec()) {
            copy(currentStep = currentStep.inc()).updateOverlayModelState()
        } else copy(isVisible = false)
    }

    fun updateOnBackState(): TourtipViewState {
        return if (currentStep > 0) {
            copy(currentStep = currentStep.dec()).updateOverlayModelState()
        } else this
    }
}
