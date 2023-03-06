package ir.reza_mahmoudi.newsgram.core.presentation.compose_components.paging_state

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import ir.reza_mahmoudi.newsgram.R
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramTypography


@Composable
fun LoadingComponent(state: PagingState) {
    when(state){
        PagingState.FINISHED -> {}
        PagingState.LOADING -> {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_loader))
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                speed = 2f
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                LottieAnimation(
                    modifier = Modifier
                        .height(32.dp)
                        .align(Alignment.Center),
                    composition = composition,
                    progress = progress,

                    )
            }
        }
        PagingState.ERROR -> {
            Box(modifier = Modifier.fillMaxWidth()){
                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(8.dp)
                        .align(Alignment.Center),
                    text = stringResource(id = R.string.some_thing_went_wrong),
                    color = NewsgramColors.designSystem.PrimaryText,
                    style = NewsgramTypography.text12
                )
            }
        }
    }
}