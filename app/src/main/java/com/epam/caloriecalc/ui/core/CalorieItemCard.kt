package com.epam.caloriecalc.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.util.toPatternString

@Composable
fun CalorieItemCard(
    product: ProductRecord,
    intake: IntakeRecord? = null,
    onClickEvent: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    val isOnlyProduct by remember { mutableStateOf(intake == null) }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable(
                enabled = isOnlyProduct,
                onClick = { onClickEvent() }
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        border = BorderStroke(2.dp, Color.Black),
        backgroundColor = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = if (isOnlyProduct) Arrangement.Start else Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = if (product.isFood) R.drawable.ic_food else R.drawable.ic_drink),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(Color.Red)
                )
                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.align(Alignment.Start),
                        color = Color.Black
                    )
                    Text(
                        text = product.calories.toString() + stringResource(id = R.string.unit_kcal),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Color.Black
                    )
                }
                if (!isOnlyProduct) {
                    IconButton(
                        onClick = {
                            onDeleteClick()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Delete",
                            tint = if (MaterialTheme.colors.isLight) MaterialTheme.colors.onBackground
                            else MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
            if (!isOnlyProduct) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    TextButton(
                        onClick = {
                            onClickEvent()
                        }
                    ) {
                        Text(
                            text = "Details",
                            textDecoration = TextDecoration.Underline,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                    }
                    Text(
                        text = intake?.timestamp?.toPatternString("HH:mm") ?: "",
                        color = Color.Black,
                        modifier = Modifier.padding(top = 12.dp, end = 8.dp)
                    )
                }
            }
        }
    }
}