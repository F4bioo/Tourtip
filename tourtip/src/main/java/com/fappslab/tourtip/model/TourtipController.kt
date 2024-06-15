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

/**
 * Interface representing a controller for managing the Tourtip guided tour.
 *
 * This interface provides methods to start and finish the guided tour.
 *
 * Example usage:
 * ```
 * @Composable
 * fun TourtipSampleScreen(controller: TourtipController) {
 *     Button(onClick = { controller.startTourtip() }) {
 *         Text("Start Tour")
 *     }
 *     Button(onClick = { controller.finishTourtip() }) {
 *         Text("Finish Tour")
 *     }
 * }
 * ```
 */
interface TourtipController {

    /**
     * Starts the Tourtip guided tour.
     */
    fun startTourtip()

    /**
     * Finishes the Tourtip guided tour.
     */
    fun finishTourtip()
}
