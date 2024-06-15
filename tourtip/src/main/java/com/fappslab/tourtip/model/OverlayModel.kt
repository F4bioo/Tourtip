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

package com.fappslab.tourtip.model

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import com.fappslab.tourtip.theme.Dimens

internal data class OverlayModel(
    val padding: Dp = Dimens.dp4,
    val outerRadius: Dp = Dimens.dp12,
    val targetBounds: Rect = Rect.Zero,
    val highlightType: HighlightType = HighlightType.Rounded
)
