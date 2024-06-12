import androidx.compose.ui.geometry.Rect
import com.fappslab.tourtip.model.BubbleDetail
import com.fappslab.tourtip.model.HighlightType

fun bubbleDetailStub(index: Int): BubbleDetail {
    return BubbleDetail(
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
