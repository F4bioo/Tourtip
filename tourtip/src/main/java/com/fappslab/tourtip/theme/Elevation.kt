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

package com.fappslab.tourtip.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
internal object Elevation {
    val none = 0.dp
    val low = 1.dp
    val medium = 3.dp
    val high = 6.dp
    val extra = 8.dp
    val ultra = 12.dp
    val max = 16.dp
}
