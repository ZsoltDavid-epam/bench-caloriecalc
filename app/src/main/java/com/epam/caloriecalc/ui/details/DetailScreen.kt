package com.epam.caloriecalc.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.model.ProductIntake
import com.epam.caloriecalc.data.model.StatType
import com.epam.caloriecalc.ui.home.components.StatsTextRow
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    navArgsDelegate = ProductIntake::class
)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {

    val item = viewModel.productIntake

    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.4f)
        ) {

            Image(
                imageVector = ImageVector.vectorResource(id = if (item.product.isFood) R.drawable.ic_food else R.drawable.ic_drink),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .padding(8.dp, top = 0.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Red)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(0.75f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.product.name,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                style = MaterialTheme.typography.h5,
                textDecoration = TextDecoration.Underline
            )
            StatsTextRow(
                statType = StatType.Calories,
                statAmount = item.product.calories,
                textStyle = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(4.dp))
            StatsTextRow(
                statType = StatType.Carbs,
                statAmount = item.product.carbohydrates,
                textStyle = MaterialTheme.typography.h6

            )
            StatsTextRow(
                statType = StatType.Fat,
                statAmount = item.product.totalFat,
                textStyle = MaterialTheme.typography.h6

            )
            StatsTextRow(
                statType = StatType.Protein,
                statAmount = item.product.protein,
                textStyle = MaterialTheme.typography.h6

            )
            Spacer(modifier = Modifier.height(8.dp))
            StatsTextRow(
                statType = StatType.Amount,
                statAmount = item.intake.amount.toFloat(),
                textStyle = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(8.dp))
            StatsTextRow(
                statType = StatType.TimeAdded,
                statTime = item.intake.timestamp,
                textStyle = MaterialTheme.typography.subtitle1
            )
        }
    }
}
