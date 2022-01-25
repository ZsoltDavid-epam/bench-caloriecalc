package com.epam.caloriecalc.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.epam.caloriecalc.util.Constants.PRODUCT_ID_COLUMN_NAME
import com.epam.caloriecalc.util.Constants.PRODUCT_NAME_COLUMN_NAME
import com.epam.caloriecalc.util.Constants.PRODUCT_TABLE_NAME

@Entity(
    tableName = PRODUCT_TABLE_NAME,
    indices = [Index(value = [PRODUCT_NAME_COLUMN_NAME], unique = true)]
)
data class ProductRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PRODUCT_ID_COLUMN_NAME)
    val productId: Int = 0,
    @ColumnInfo(name = PRODUCT_NAME_COLUMN_NAME)
    val name: String = "",
    val calorie: Float = 0f,
    val totalFat: Float = 0f,
    val protein: Float = 0f,
    val carbohydrates: Float = 0f,
    val isFood: Boolean = true,
    val imagePath: String = ""
)
