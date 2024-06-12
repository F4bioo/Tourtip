package com.fappslab.tourtip.sample.presentation.compose.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fappslab.tourtip.compose.extension.bubbleAnchor

@Composable
internal fun BottomMenu() {
    BottomAppBar(
        actions = {
            IconButton(
                modifier = Modifier.bubbleAnchor {
                    bubbleBottomCheck()
                },
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier.bubbleAnchor {
                    bubbleBottomEdit()
                },
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.bubbleAnchor {
                    bubbleFabButton()
                },
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
private fun BottomMenuPreview() {
    BottomMenu()
}
