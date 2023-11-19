package com.example.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.model.UniversityResponseItem.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = TABLE_NAME)
data class UniversityResponseItem(
    val alpha_two_code: String?,
    val country: String,
    val domains: List<String>,
    @PrimaryKey val name: String,
    val state_province: String?,
    val web_pages: List<String>?
) :Parcelable
{
    companion object{
        const val TABLE_NAME = "universities"
    }
}
