package br.com.andreyneto.goomer.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun toList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(value, type)
    }
    @TypeConverter
    fun fromList(value: List<String>?) = Gson().toJson(value)

}
