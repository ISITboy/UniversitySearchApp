package com.example.domain.typeConverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class UniversitiesTypeConvertor {
    @TypeConverter
    fun dataToString(data:List<String>):String? = data.joinToString(separator = ",")

    @TypeConverter
    fun stringToData(data:String):List<String>? = data.split(",")
}