package com.minhoi.composepractice.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.minhoi.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun Graph3() {
    Graph3Bar()

}

@Composable
fun Graph3Bar() {
    val barDataList = listOf(2,6,4,7,8,9,5,3)
    BarChart(barDataList)
}

@Composable
fun BarChart(barDataList: List<Int>) {
    val maxData = barDataList.max()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        barDataList.forEach { barData ->
            Bar(barData, maxData)
        }
    }
}

@Composable
fun Bar(barData: Int, maxData: Int) {
    val height = (barData.toFloat() / maxData) * 300
    Box(
        modifier = Modifier
            .height(height.dp)
            .width(30.dp)
            .background(Color.Black),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(text = barData.toString(), color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun Graph3Preview() {
    ComposePracticeTheme {
        Graph3()
    }
}