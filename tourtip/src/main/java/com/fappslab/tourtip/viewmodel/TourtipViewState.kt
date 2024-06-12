package com.fappslab.tourtip.viewmodel

import androidx.compose.ui.geometry.Rect
import com.fappslab.tourtip.model.BubbleDetail
import com.fappslab.tourtip.model.HighlightType
import com.fappslab.tourtip.model.OverlayModel
import com.fappslab.tourtip.model.StepModel

internal data class TourtipViewState(
    val currentStep: Int = 0,
    val isVisible: Boolean = false,
    val stepModel: StepModel? = null,
    val indexedBounds: Map<Int, Rect> = emptyMap(),
    val highlightTypes: Map<Int, HighlightType> = emptyMap(),
    val bubbleDetails: Map<Int, BubbleDetail> = emptyMap(),
    val overlayModel: OverlayModel = OverlayModel()
) {
    private fun updateOverlayModelState(): TourtipViewState {
        val updatedOverlayModel = overlayModel.copy(
            targetBounds = indexedBounds[currentStep] ?: Rect.Zero,
            highlightType = highlightTypes[currentStep] ?: HighlightType.Rounded
        )
        return copy(overlayModel = updatedOverlayModel)
    }

    fun updateBoundsState(model: BubbleDetail, bounds: Rect): TourtipViewState {
        return copy(
            indexedBounds = indexedBounds.toMutableMap()
                .apply { put(model.index, bounds) },
            highlightTypes = highlightTypes.toMutableMap()
                .apply { put(model.index, model.highlightType) },
            bubbleDetails = bubbleDetails.toMutableMap()
                .apply { put(model.index, model) }
        ).updateOverlayModelState()
    }

    fun startTourtipState(): TourtipViewState {
        return copy(isVisible = true, currentStep = 0).updateOverlayModelState()
    }

    fun updateOnNextState(): TourtipViewState {
        return if (currentStep < bubbleDetails.size.dec()) {
            copy(currentStep = currentStep.inc()).updateOverlayModelState()
        } else copy(isVisible = false)
    }

    fun updateOnBackState(): TourtipViewState {
        return if (currentStep > 0) {
            copy(currentStep = currentStep.dec()).updateOverlayModelState()
        } else this
    }
}
