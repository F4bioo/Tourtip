package com.fappslab.tourtip.sample.presentation.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fappslab.tourtip.sample.R

@Composable
fun IconLauncher(
    modifier: Modifier
) {
    Image(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
    )
}

@Preview
@Composable
private fun IconLauncherPreview() {
    IconLauncher(
        modifier = Modifier
    )
}
