package utils

import androidx.compose.ui.geometry.Rect
import com.fappslab.tourtip.model.TooltipModel
import com.fappslab.tourtip.model.HighlightType

fun tooltipModelStub(index: Int): TooltipModel {
    return TooltipModel(
        index = index,
        title = null,
        message = {},
        action = null,
        highlightType = HighlightType.Rounded
    )
}

fun boundsStub(): Rect {
    return Rect(0f, 0f, 10f, 10f)
}
