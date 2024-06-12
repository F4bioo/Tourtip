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
