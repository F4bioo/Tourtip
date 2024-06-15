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

import androidx.compose.runtime.staticCompositionLocalOf
import com.fappslab.tourtip.model.BoundsRegistry
import com.fappslab.tourtip.theme.Dimens
import com.fappslab.tourtip.theme.Elevation
import com.fappslab.tourtip.theme.Opacity
import com.fappslab.tourtip.theme.Radius

internal val LocalBoundsRegistry = staticCompositionLocalOf<BoundsRegistry> {
    error("No BoundsRegistry provided")
}

internal val LocalPlutoOpacity = staticCompositionLocalOf {
    Opacity
}

internal val LocalPlutoRadius = staticCompositionLocalOf {
    Radius
}

internal val LocalPlutoSizing = staticCompositionLocalOf {
    Dimens
}

internal val LocalPlutoElevation = staticCompositionLocalOf {
    Elevation
}
