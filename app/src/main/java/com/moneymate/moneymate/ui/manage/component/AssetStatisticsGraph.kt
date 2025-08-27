package com.moneymate.moneymate.ui.manage.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.point
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.CartesianMeasuringContext
import com.patrykandpatrick.vico.core.cartesian.axis.Axis
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.shape.CorneredShape
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat

class DateAxisValueFormatter(private val dates: List<String>) : CartesianValueFormatter {
    override fun format(
        context: CartesianMeasuringContext,
        value: Double,
        verticalAxisPosition: Axis.Position.Vertical?,
    ): String {
        val index = value.toInt()
        if (index < 0 || index >= dates.size) return ""
        
        val date = dates[index]
        val (year, month) = date.split("-").map { it.toInt() }
        return "${year}/${month.toString().padStart(2, '0')}"
    }
}

private val MarkerValueFormatter = DefaultCartesianMarker.ValueFormatter.default(DecimalFormat("#,###원"))

@Composable
fun AssetStatisticsGraph(
    modifier: Modifier = Modifier,
    modelProducer: CartesianChartModelProducer,
    dates: List<String> = emptyList()  // 날짜 정보를 파라미터로 받음
) {
    val lineColor = MoneyMateTheme.colors.deepBlue

    CartesianChartHost(
        chart =
            rememberCartesianChart(
                rememberLineCartesianLayer(
                    lineProvider = LineCartesianLayer.LineProvider.series(
                        LineCartesianLayer.rememberLine(
                            fill = LineCartesianLayer.LineFill.single(fill(lineColor)),
//                            areaFill =
//                                LineCartesianLayer.AreaFill.single(
//                                    fill(
//                                        ShaderProvider.verticalGradient(
//                                            arrayOf(lineColor.copy(alpha = 0.4f), Color.Transparent)
//                                        )
//                                    )
//                                ),
                            pointProvider =
                                LineCartesianLayer.PointProvider.single(
                                    LineCartesianLayer.point(rememberShapeComponent(fill(
                                        MoneyMateTheme.colors.deepBlue), CorneredShape.Pill))
                                ),
                        ),
                    )
                ),
                startAxis = VerticalAxis.rememberStart(
                    guideline = null,
                    tick = null,
                    label = null
                ),
                bottomAxis = HorizontalAxis.rememberBottom(
                    valueFormatter = remember(dates) { DateAxisValueFormatter(dates) },
                    guideline = null,
                    labelRotationDegrees = 65f  // 레이블이 겹치지 않도록 45도 회전
                ),
                marker = rememberMarker(MarkerValueFormatter)
            ),
        modelProducer = modelProducer,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun AssetStatisticsGraphPreview() {
    val modelProducer = remember { CartesianChartModelProducer() }
    // Use `runBlocking` only for previews, which don’t support asynchronous execution.
    runBlocking {
        modelProducer.runTransaction {
            lineSeries { series(13, 8, 7, 12, 0, 1, 15, 14, 0, 11, 6, 12, 0, 11, 12, 11) }
        }
    }
    AssetStatisticsGraph(
        modifier = Modifier,
        modelProducer = modelProducer,
    )
}