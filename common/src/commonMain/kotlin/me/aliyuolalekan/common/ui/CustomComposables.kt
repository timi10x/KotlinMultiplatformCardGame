package me.aliyuolalekan.common.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(standardButtonPadding),
        shape = RoundedCornerShape(20.dp),

    ) {
        Text(
            text,
            modifier = Modifier.wrapContentWidth().wrapContentHeight().align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            style = typography.h6,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CustomCard(
    modifier: Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    borderStroke: BorderStroke? = null,
    elevation: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    Box{

    }
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = borderStroke,
        elevation = elevation,
        content = content,
    )

}


// TODO Investigate how to load image from resource
@Composable
fun Avatar() {
    Box(
        modifier = Modifier.width(normalAvatarSize)
            .height(normalAvatarSize)
            .clip(CircleShape)
            .background(Color.Gray)
    ) {
        Icon(
            Icons.Filled.Person,
            "person",
            modifier = Modifier
                .align(Alignment.Center)
                .width(normalAvatarIconSize)
                .height(normalAvatarIconSize)
                .padding(standardContentMargin)
        )
    }
}

@Composable
fun VerticalSpace(verticalMargin: Dp = standardContentMargin) {
    Spacer(modifier = Modifier.height(standardContentMargin))
}