import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.jetchart.pie.PieChart
import io.jetchart.pie.Pies
import io.jetchart.pie.Slice
import io.jetchart.pie.renderer.FilledSliceDrawer

data class CategoryAmount(val name: String, val amount: Float)

@Composable
fun SpendingStatisticChart(
    totalAmount: Float,
    categories: List<CategoryAmount>,
    modifier: Modifier = Modifier
) {
    val slices = categories.mapIndexed { i, c ->
        val color =
            if (i < donutColors.size - 1) donutColors[i]
            else donutColors.last()
        Slice(
            value = c.amount.coerceAtLeast(0f),
            color = color
        )
    }

    PieChart(
        pies = Pies(slices),
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 33.dp)
            .rotate(-90f),
        sliceDrawer = FilledSliceDrawer(thickness = 45f)
    )
}

// 팔레트(필요시 교체해서 사용)
private val donutColors = listOf(
    Color(0xFF0E0857),
    Color(0xFF1C1970),
    Color(0xFF2A2D89),
    Color(0xFF3B44A2),
    Color(0xFF5561BB),
    Color(0xFF7582D4),
    Color(0xFF9DA9ED),
    Color(0xFFF5F5F5),
)
