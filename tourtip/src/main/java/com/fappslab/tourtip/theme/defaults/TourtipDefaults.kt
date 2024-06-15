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

package com.fappslab.tourtip.theme.defaults

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fappslab.tourtip.theme.TourtipTheme

internal object TourtipDefaults {

    val scrimColor: Color
        @Composable
        get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.onSurface.copy(alpha = TourtipTheme.opacity.faint)
        } else MaterialTheme.colorScheme.scrim.copy(alpha = TourtipTheme.opacity.frosted)

    val backgroundColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant
}
