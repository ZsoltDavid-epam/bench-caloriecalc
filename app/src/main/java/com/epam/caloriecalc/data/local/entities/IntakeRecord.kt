package com.epam.caloriecalc.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.epam.caloriecalc.util.Constants.INTAKE_TABLE_NAME
import com.epam.caloriecalc.util.Constants.PRODUCT_ID_COLUMN_NAME
import java.time.Instant
import java.time.Instant.now

@Entity(
    tableName = INTAKE_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = ProductRecord::class,
        parentColumns = [PRODUCT_ID_COLUMN_NAME],
        childColumns = [PRODUCT_ID_COLUMN_NAME],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class IntakeRecord(
    @PrimaryKey(autoGenerate = true)
    val intakeId: Int = 0,
    @ColumnInfo(name = PRODUCT_ID_COLUMN_NAME)
    val productId: Int = 0,
    val timestamp: Instant = now(),
    val amount: Int = 100
)

