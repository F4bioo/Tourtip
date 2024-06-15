package com.fappslab.tourtip.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import utils.boundsStub
import utils.tooltipModelStub
import com.fappslab.tourtip.model.HighlightType
import com.fappslab.tourtip.model.OverlayModel
import com.fappslab.tourtip.model.StepModel
import io.mockk.clearAllMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import utils.CoroutineTestRule

@ExperimentalCoroutinesApi
internal class TourtipViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    private val initialState = TourtipViewState()
    private lateinit var subject: TourtipViewModel

    @Before
    fun setUp() {
        subject = TourtipViewModel()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given updateBounds When is invoked Then should expose expected state`() = runTest {
        // Given
        val bounds = boundsStub()
        val model = tooltipModelStub(index = 1)
        val expectedState = initialState.copy(
            indexedBounds = mapOf(1 to bounds),
            highlightTypes = mapOf(1 to HighlightType.Rounded),
            tooltipModels = mapOf(1 to model)
        )

        // When
        subject.updateBounds(model, bounds)

        // Then
        subject.state.test {
            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun `Given startTourtip When is invoked Then should expose expected state`() = runTest {
        // Given
        val expectedState = initialState.copy(isVisible = true, currentStep = 0)

        // When
        subject.startTourtip()

        // Then
        subject.state.test {
            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun `Given finishTourtip When is invoked Then should expose expected state`() = runTest {
        // Given
        val expectedState = initialState.copy(isVisible = false)

        // When
        subject.finishTourtip()

        // Then
        subject.state.test {
            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun `Given loadSteps When is invoked Then should expose expected state`() = runTest {
        // Given
        val bounds = boundsStub()
        val model = tooltipModelStub(index = 1)
        subject.updateBounds(model, bounds)
        val expectedState = initialState.copy(
            indexedBounds = mapOf(1 to bounds),
            highlightTypes = mapOf(1 to HighlightType.Rounded),
            tooltipModels = mapOf(1 to model)
        )

        // When
        subject.loadSteps()

        // Then
        subject.state.test {
            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun `Given onNext When is invoked Then should expose expected state`() = runTest {
        // Given
        val bounds = boundsStub()
        val model1 = tooltipModelStub(index = 0)
        val model2 = tooltipModelStub(index = 1)
        subject.startTourtip()
        subject.updateBounds(model1, bounds)
        subject.updateBounds(model2, bounds)
        val expectedState = initialState.copy(
            currentStep = 1,
            indexedBounds = mapOf(0 to bounds, 1 to bounds),
            highlightTypes = mapOf(0 to HighlightType.Rounded, 1 to HighlightType.Rounded),
            tooltipModels = mapOf(0 to model1, 1 to model2),
            overlayModel = OverlayModel(targetBounds = bounds),
            isVisible = true
        )

        // When
        subject.onNext()

        // Then
        subject.state.test {
            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun `Given onBack When is invoked Then should expose expected state`() = runTest {
        // Given
        val bounds = boundsStub()
        val model1 = tooltipModelStub(index = 0)
        val model2 = tooltipModelStub(index = 1)
        subject.startTourtip()
        subject.updateBounds(model1, bounds)
        subject.updateBounds(model2, bounds)
        subject.onNext()
        subject.onNext()
        val expectedState = initialState.copy(
            indexedBounds = mapOf(0 to bounds, 1 to bounds),
            highlightTypes = mapOf(0 to HighlightType.Rounded, 1 to HighlightType.Rounded),
            tooltipModels = mapOf(0 to model1, 1 to model2),
            overlayModel = OverlayModel(targetBounds = bounds),
            stepModel = StepModel(currentStep = 1, totalSteps = 2)
        )

        // When
        subject.onBack()

        // Then
        subject.state.test {
            assertEquals(expectedState, awaitItem())
        }
    }
}
