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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.fappslab.tourtip.compose.extension.isTargetZero
import com.fappslab.tourtip.compose.extension.toDp
import com.fappslab.tourtip.model.ShapeType
import com.fappslab.tourtip.theme.TourtipTheme

@Composable
internal fun CaretComponent(
    targetBounds: Rect,
    isCaretUp: Boolean,
    xOffset: Float,
    yOffset: Float,
    caretWidth: Dp,
    caretHeight: Dp,
    color: Color,
    shapeType: ShapeType
) {
    if (targetBounds.isTargetZero()) return
    val customShape = remember(isCaretUp, shapeType) { TriangleShape(isCaretUp, shapeType) }

    Box(
        modifier = Modifier
            .offset(x = xOffset.toDp(), y = yOffset.toDp())
            .size(caretWidth, caretHeight)
            .background(
                color = color,
                shape = customShape
            )
    )
}

private class TriangleShape(
    private val isCaretUp: Boolean,
    private val shapeType: ShapeType
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            when (shapeType) {
                ShapeType.Center -> {
                    if (isCaretUp) {
                        moveTo(x = 0f, size.height)
                        lineTo(x = size.width / 2, 0f)
                        lineTo(size.width, size.height)
                    } else {
                        moveTo(x = 0f, y = 0f)
                        lineTo(x = size.width / 2, size.height)
                        lineTo(size.width, y = 0f)
                    }
                }

                ShapeType.LeftBounds -> {
                    if (isCaretUp) {
                        moveTo(0f, size.height)
                        lineTo(size.width, size.height)
                        lineTo(0f, 0f)
                    } else {
                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(0f, size.height)
                    }
                }

                ShapeType.RightBounds -> {
                    if (isCaretUp) {
                        moveTo(size.width, size.height)
                        lineTo(0f, size.height)
                        lineTo(size.width, 0f)
                    } else {
                        moveTo(size.width, 0f)
                        lineTo(0f, 0f)
                        lineTo(size.width, size.height)
                    }
                }
            }
            close()
        }
        return Outline.Generic(path)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CaretComponentPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CaretComponent(
            targetBounds = Rect(0f, 0f, 100f, 100f),
            isCaretUp = false,
            xOffset = 0f,
            yOffset = 0f,
            caretWidth = TourtipTheme.dimen.dp100,
            caretHeight = TourtipTheme.dimen.dp100,
            color = Color.Red,
            shapeType = ShapeType.Center
        )
    }
}
