package com.minhoi.composepractice.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minhoi.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun Graph1() {
    Graph1Gauge(percent = 70F, maxPercent = 100F)
}

@Composable
fun Graph1Gauge(percent: Float, maxPercent: Float) {
    val colorPercent = 360 * (percent / maxPercent)

    Box(modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
        .padding(20.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(32f)
            )
            drawArc(
                color = Color.Blue,
                startAngle = -90f,
                sweepAngle = colorPercent,
                useCenter = false,
                style = Stroke(32f)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "${percent.toInt()}%", fontSize = 30.sp)
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "$percent / $maxPercent")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Graph1Preview() {
    ComposePracticeTheme {
        Graph1()
    }
}