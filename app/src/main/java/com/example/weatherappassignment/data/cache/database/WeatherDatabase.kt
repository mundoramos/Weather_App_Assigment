package com.example.weatherappassignment.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherappassignment.data.model.WeatherForecastDayObject
import com.example.weatherappassignment.data.cache.database.dao.WeatherObjectDao

@Database(entities = [WeatherForecastDayObject::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherObjectDao

    companion object {
        @Volatile
        private var DATABASE_INSTANCE: WeatherDatabase? = null

        fun getDatabaseInstance(context: Context): WeatherDatabase {
            return DATABASE_INSTANCE ?: synchronized(this) {
                DATABASE_INSTANCE ?: buildDatabase(context).also { DATABASE_INSTANCE = it }
            }
        }

        fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "weatherdatabase.db"
            ).build()
        }
    }
}