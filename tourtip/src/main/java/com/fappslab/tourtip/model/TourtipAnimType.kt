package com.fappslab.tourtip.model

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.Dp

/**
 * Enum class representing some types of animations for Tourtip transitions.
 *
 * @property label A human-readable label for the animation type.
 */
enum class TourtipAnimType(val label: String) {
    /**
     * Bouncy animation type, providing a low bouncy spring animation.
     */
    Bouncy(label = "Bouncy Animation"),

    /**
     * Tween animation type with a custom cubic bezier easing curve.
     */
    Tween(label = "Tween Animation"),

    /**
     * Ease in out animation type with a standard ease in and out transition.
     */
    EaseInOut(label = "Ease In Out Animation"),

    /**
     * Fast out slow in animation type with a standard fast out slow in transition.
     */
    FastOutSlowIn(label = "Fast Out Slow In Animation"),

    /**
     * Spring animation type with high damping, providing a medium bouncy spring animation.
     */
    SpringHighDamping(label = "Spring High Damping Animation");

    /**
     * Returns the corresponding [AnimationSpec] for the given [TourtipAnimType].
     *
     * @param type The type of animation.
     * @return The [AnimationSpec] for the specified animation type.
     */
    fun animOf(type: TourtipAnimType): AnimationSpec<Dp> {
        return when (type) {
            Bouncy -> spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )

            Tween -> tween(
                durationMillis = 1000,
                easing = CubicBezierEasing(a = 0.5f, b = 1.4f, c = 0.5f, d = 1f)
            )

            EaseInOut -> tween(
                durationMillis = 300,
                easing = androidx.compose.animation.core.EaseInOut
            )

            FastOutSlowIn -> tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )

            SpringHighDamping -> spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        }
    }
}
