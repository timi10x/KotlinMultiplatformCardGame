package me.aliyuolalekan.common.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.aliyuolalekan.common.getImagePainter
import me.aliyuolalekan.common.ui.VerticalSpace
import me.aliyuolalekan.common.ui.standardContentMargin
import me.aliyuolalekan.common.ui.standardLargeMargin
import me.aliyuolalekan.common.ui.typography

@Composable
fun AboutItem(data: AboutGameData) {
    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
            .padding(start = standardContentMargin, end = standardContentMargin)
    ) {
        VerticalSpace(standardLargeMargin)
        Text(data.title, style = typography.h5)
        VerticalSpace(standardContentMargin)
        data.imagePath?.let {
            Image(
                getImagePainter(it),
                contentDescription = null,
                modifier = Modifier.width(150.dp).height(300.dp).align(Alignment.CenterHorizontally)
            )
        }
        VerticalSpace(standardContentMargin)
        if (data.detailDescription.isNotEmpty()) {
            Text(data.detailDescription, fontSize = 20.sp)
        }
        VerticalSpace(standardContentMargin)
    }
}