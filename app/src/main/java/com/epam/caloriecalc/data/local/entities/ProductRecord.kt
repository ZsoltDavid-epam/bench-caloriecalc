package com.epam.caloriecalc.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.epam.caloriecalc.util.Constants.PRODUCT_ID_COLUMN_NAME
import com.epam.caloriecalc.util.Constants.PRODUCT_NAME_COLUMN_NAME
import com.epam.caloriecalc.util.Constants.PRODUCT_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = PRODUCT_TABLE_NAME,
    indices = [Index(value = [PRODUCT_NAME_COLUMN_NAME], unique = true)]
)
@Parcelize
data class ProductRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PRODUCT_ID_COLUMN_NAME)
    val productId: Int = 0,
    @ColumnInfo(name = PRODUCT_NAME_COLUMN_NAME)
    val name: String = "",
    val calories: Float = 0f,
    val totalFat: Float = 0f,
    val protein: Float = 0f,
    val carbohydrates: Float = 0f,
    val isFood: Boolean = true,
    val imagePath: String = ""
): Parcelable
