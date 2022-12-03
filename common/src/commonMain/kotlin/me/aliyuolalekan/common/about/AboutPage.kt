package me.aliyuolalekan.common.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import me.aliyuolalekan.common.GlobalResources
import me.aliyuolalekan.common.Strings.ABOUT_GAME_TITLE
import me.aliyuolalekan.common.getImagePainter
import me.aliyuolalekan.common.ui.standardContentMargin
import me.aliyuolalekan.common.ui.typography

private val data = AboutGameDataRepository().getAboutGameData()

@Composable
fun AboutPage(onClose: () -> Unit) {
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize(),
            lazyListState,
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(240.dp)) {
                    Image(
                        painter = getImagePainter(GlobalResources.topBannerImage),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .graphicsLayer {
                                scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                                translationY = scrolledY * 0.1f
                                previousOffset = lazyListState.firstVisibleItemScrollOffset
                            }
                            .height(240.dp)
                            .fillMaxWidth()
                    )
                    Text(ABOUT_GAME_TITLE, style = typography.h4, modifier = Modifier.padding(standardContentMargin))
                }
            }
            items(data) { itemData ->
                AboutItem(itemData)
            }
        }
        CloseButton(onClose = onClose, Modifier.align(Alignment.TopEnd))
    }
}