package com.fappslab.tourtip.viewmodel

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import com.fappslab.tourtip.model.BoundsRegistry
import com.fappslab.tourtip.model.BubbleDetail
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
            totalSteps = bubbleDetails.size
        ).takeIf { currentStep < bubbleDetails.size.dec() }
        _state.update { it.copy(stepModel = stepModel) }
    }

    override fun updateBounds(model: BubbleDetail, bounds: Rect) {
        _state.update { it.updateBoundsState(model, bounds) }
    }

    override fun startTourtip() {
        _state.update { it.startTourtipState() }
        stepModelHandler()
    }

    override fun finishTourtip() = onEnd()

    fun loadSteps() = currentState.run {
        val firstIndex = bubbleDetails.keys.minOrNull() ?: return
        val firstDetail = bubbleDetails[firstIndex] ?: return
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
