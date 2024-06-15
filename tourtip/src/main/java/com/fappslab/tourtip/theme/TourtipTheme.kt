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

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.fappslab.tourtip.theme.defaults.LocalPlutoElevation
import com.fappslab.tourtip.theme.defaults.LocalPlutoOpacity
import com.fappslab.tourtip.theme.defaults.LocalPlutoRadius
import com.fappslab.tourtip.theme.defaults.LocalPlutoSizing

@Composable
internal fun TourtipTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalPlutoOpacity provides Opacity,
        LocalPlutoRadius provides Radius,
        LocalPlutoSizing provides Dimens,
        LocalPlutoElevation provides Elevation
    ) {
        MaterialTheme(
            content = content
        )
    }
}

internal object TourtipTheme {

    val opacity: Opacity
        @Composable
        get() = LocalPlutoOpacity.current

    val radius: Radius
        @Composable
        get() = LocalPlutoRadius.current

    val dimen: Dimens
        @Composable
        get() = LocalPlutoSizing.current

    val elevation: Elevation
        @Composable
        get() = LocalPlutoElevation.current
}
