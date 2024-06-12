package com.fappslab.tourtip.sample.presentation.compose.component.extension

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

fun Path.drawZigzagRect(bounds: Rect, zigzagSize: Float = 10f) {
    val zigzagPath = Path()
    val stepsX = (bounds.width / zigzagSize).toInt()
    val stepsY = (bounds.height / zigzagSize).toInt()

    // Top edge
    for (i in 0 until stepsX) {
        val x = bounds.left + i * zigzagSize
        val y = if (i % 2 == 0) bounds.top else bounds.top + zigzagSize
        if (i == 0) {
            zigzagPath.moveTo(x, y)
        } else {
            zigzagPath.lineTo(x, y)
        }
    }

    // Right edge
    for (i in 0 until stepsY) {
        val x = if (i % 2 == 0) bounds.right else bounds.right - zigzagSize
        val y = bounds.top + i * zigzagSize
        zigzagPath.lineTo(x, y)
    }

    // Bottom edge
    for (i in 0 until stepsX) {
        val x = bounds.right - i * zigzagSize
        val y = if (i % 2 == 0) bounds.bottom else bounds.bottom - zigzagSize
        zigzagPath.lineTo(x, y)
    }

    // Left edge
    for (i in 0 until stepsY) {
        val x = if (i % 2 == 0) bounds.left else bounds.left + zigzagSize
        val y = bounds.bottom - i * zigzagSize
        zigzagPath.lineTo(x, y)
    }

    zigzagPath.close()
    this.addPath(zigzagPath)
}

fun Path.drawWavyRect(bounds: Rect, waveLength: Float = 20f, waveHeight: Float = 10f) {
    val wavyPath = Path()

    val stepsX = (bounds.width / waveLength).toInt()
    val stepsY = (bounds.height / waveLength).toInt()

    // Top edge
    for (i in 0..stepsX) {
        val startX = bounds.left + i * waveLength
        val midX = startX + waveLength / 2
        val endX = startX + waveLength
        val startY = bounds.top
        val midY = if (i % 2 == 0) startY - waveHeight else startY + waveHeight

        if (i == 0) {
            wavyPath.moveTo(startX, startY)
        } else {
            wavyPath.lineTo(startX, startY)
        }
        if (endX <= bounds.right) {
            wavyPath.quadraticTo(midX, midY, endX, startY)
        }
    }

    // Right edge
    for (i in 0..stepsY) {
        val startY = bounds.top + i * waveLength
        val midY = startY + waveLength / 2
        val endY = startY + waveLength
        val startX = bounds.right
        val midX = if (i % 2 == 0) startX + waveHeight else startX - waveHeight

        wavyPath.lineTo(startX, startY)
        if (endY <= bounds.bottom) {
            wavyPath.quadraticTo(midX, midY, startX, endY)
        }
    }

    // Bottom edge
    for (i in 0..stepsX) {
        val startX = bounds.right - i * waveLength
        val midX = startX - waveLength / 2
        val endX = startX - waveLength
        val startY = bounds.bottom
        val midY = if (i % 2 == 0) startY + waveHeight else startY - waveHeight

        wavyPath.lineTo(startX, startY)
        if (endX >= bounds.left) {
            wavyPath.quadraticTo(midX, midY, endX, startY)
        }
    }

    // Left edge
    for (i in 0..stepsY) {
        val startY = bounds.bottom - i * waveLength
        val midY = startY - waveLength / 2
        val endY = startY - waveLength
        val startX = bounds.left
        val midX = if (i % 2 == 0) startX - waveHeight else startX + waveHeight

        wavyPath.lineTo(startX, startY)
        if (endY >= bounds.top) {
            wavyPath.quadraticTo(midX, midY, startX, endY)
        }
    }

    wavyPath.close()
    this.addPath(wavyPath)
}
