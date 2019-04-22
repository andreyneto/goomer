package br.com.andreyneto.goomer.utils

import androidx.room.TypeConverter
import br.com.andreyneto.goomer.model.Hour
import br.com.andreyneto.goomer.model.Sale
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun hourToList(value: String?): List<Hour> {
        val type = object : TypeToken<List<Hour>>() {}.type
        return Gson().fromJson<List<Hour>>(value ?: "", type) ?: listOf()
    }
    @TypeConverter
    fun hourFromList(value: List<Hour>?) = Gson().toJson(value ?: listOf<Hour>())

    @TypeConverter
    fun saleToList(value: String?): List<Sale> {
        val type = object : TypeToken<List<Sale>>() {}.type
        return Gson().fromJson<List<Sale>>(value ?: "", type) ?: listOf()
    }
    @TypeConverter
    fun saleFromList(value: List<Sale>?) = Gson().toJson(value ?: listOf<Sale>())
}
