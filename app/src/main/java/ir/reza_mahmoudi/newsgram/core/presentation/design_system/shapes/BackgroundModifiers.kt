package ir.reza_mahmoudi.newsgram.core.presentation.design_system.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors

fun Modifier.bgRounded8Neutral00StrokePrimary00() = composed {
    this.then(
        this
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.NewsgramColors.designSystem.Primary00,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = MaterialTheme.NewsgramColors.designSystem.Neutral00,
            )
    )
}

fun Modifier.bgRounded8Neutral00StrokeNeutral10() = composed {
    this.then(
        this
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.NewsgramColors.designSystem.Neutral10,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = MaterialTheme.NewsgramColors.designSystem.Neutral00,
            )
    )
}
