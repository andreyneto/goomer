package br.com.andreyneto.goomer.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.andreyneto.goomer.utils.Converters

@Database(entities = [Restaurantes::class, Menu::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}