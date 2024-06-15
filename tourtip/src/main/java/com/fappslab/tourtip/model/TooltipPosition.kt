package com.fappslab.tourtip.model

import androidx.compose.ui.unit.Dp
import com.fappslab.tourtip.theme.Dimens

internal data class TooltipPosition(
    val isCaretUp: Boolean,
    val caretMargin: Dp = Dimens.dp8,
    val caretWidth: Dp = Dimens.dp16,
    val caretHeight: Dp = Dimens.dp16
)
