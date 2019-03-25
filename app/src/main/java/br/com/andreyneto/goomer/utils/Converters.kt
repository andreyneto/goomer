package br.com.andreyneto.goomer.utils

import androidx.room.TypeConverter
import br.com.andreyneto.goomer.model.Hour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun toList(value: String?): List<Hour> {
        val type = object : TypeToken<List<Hour>>() {}.type
        return Gson().fromJson<List<Hour>>(value ?: "", type) ?: listOf()
    }
    @TypeConverter
    fun fromList(value: List<Hour>?) = Gson().toJson(value ?: listOf<Hour>())
}
