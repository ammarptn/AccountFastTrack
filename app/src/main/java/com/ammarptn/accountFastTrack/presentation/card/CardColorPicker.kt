package com.ammarptn.accountFastTrack.presentation.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardColorPicker(
    selectedColor: Long,
    onColorSelected: (Long) -> Unit
) {
    val colors = listOf(
        // Blues
        0xFF2196F3, // Blue
        0xFF1976D2, // Dark Blue
        0xFF0D47A1, // Deep Blue
        0xFF03A9F4, // Light Blue
        0xFF0288D1, // Ocean Blue

        // Reds
        0xFFE53935, // Red
        0xFFC62828, // Dark Red
        0xFFD32F2F, // Bright Red
        0xFFFF5252, // Light Red
        0xFFFF1744, // Accent Red

        // Greens
        0xFF4CAF50, // Green
        0xFF388E3C, // Dark Green
        0xFF1B5E20, // Forest Green
        0xFF00E676, // Light Green
        0xFF00C853, // Bright Green

        // Purples
        0xFF9C27B0, // Purple
        0xFF7B1FA2, // Dark Purple
        0xFF6A1B9A, // Deep Purple
        0xFFE040FB, // Light Purple
        0xFFD500F9, // Bright Purple

        // Oranges
        0xFFFF9800, // Orange
        0xFFF57C00, // Dark Orange
        0xFFFF5722, // Deep Orange
        0xFFFFAB40, // Light Orange
        0xFFFFAB00, // Amber

        // Teals and Cyans
        0xFF009688, // Teal
        0xFF00695C, // Dark Teal
        0xFF00BCD4, // Cyan
        0xFF00ACC1, // Dark Cyan
        0xFF00B8D4, // Light Cyan

        // Browns
        0xFF795548, // Brown
        0xFF5D4037, // Dark Brown
        0xFF8D6E63, // Light Brown
        0xFF6D4C41, // Medium Brown
        0xFF4E342E, // Deep Brown

        // Greys
        0xFF9E9E9E, // Grey
        0xFF757575, // Dark Grey
        0xFF616161, // Deeper Grey
        0xFFBDBDBD, // Light Grey
        0xFF424242, // Almost Black

        // Specialty Colors
        0xFF607D8B, // Blue Grey
        0xFF455A64, // Dark Blue Grey
        0xFFFF4081, // Pink
        0xFFC2185B, // Dark Pink
        0xFFEC407A  // Light Pink
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(count = colors.size) { index ->
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(colors[index]))
                    .border(
                        width = 3.dp,
                        color = if (selectedColor == colors[index]) Color.White else Color.Transparent,
                        shape = CircleShape
                    )
                    .clickable { onColorSelected(colors[index]) }
            ) {
                if (selectedColor == colors[index]) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .border(
                                width = 2.dp,
                                color = Color.Black.copy(alpha = 0.1f),
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}