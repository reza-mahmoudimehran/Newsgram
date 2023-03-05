package ir.reza_mahmoudi.newsgram.core.presentation.app_components.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.shapes.bgRounded8Primary00NonStroke
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramTypography


@Composable
fun FiltersList(
    modifier: Modifier = Modifier,
    filterText: String,
    filterState: MutableState<String?>,
    filterList: Map<String?, String>
) {

    LazyRow(
        modifier = modifier
            .padding(5.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(8.dp),
                text = filterText,
                color = MaterialTheme.NewsgramColors.designSystem.PrimaryText,
                style = MaterialTheme.NewsgramTypography.text14
            )
        }
        filterList.forEach {
            item {
                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable {
                            filterState.value = it.key
                        }
                        .bgRounded8Primary00NonStroke()
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(8.dp),
                    text = it.value,
                    color = if (it.key == filterState.value) {
                        MaterialTheme.NewsgramColors.designSystem.TertiaryText
                    } else {
                        MaterialTheme.NewsgramColors.designSystem.PrimaryText
                    },
                    style = MaterialTheme.NewsgramTypography.text14
                )
            }
        }
    }
}