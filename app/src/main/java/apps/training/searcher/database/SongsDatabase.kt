package apps.training.searcher.database

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import apps.training.searcher.data.Results

@Database(entities = [Results::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class SongsDatabase : RoomDatabase() {
//    abstract fun songs() : SongsDao
    abstract fun results() : ResultsDao

    companion object{
        private var database : SongsDatabase ?= null
        fun Context.instance() : SongsDatabase? {
            if (database == null){
                database = Room.databaseBuilder(this,SongsDatabase::class.java,"songs_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return database
        }

        fun Activity.instance() : SongsDatabase? {
            if (database == null){
                database = Room.databaseBuilder(this.applicationContext,SongsDatabase::class.java,"songs_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return database
        }

        fun Application.instance() : SongsDatabase? {
            if (database == null){
                database = Room.databaseBuilder(this.applicationContext,SongsDatabase::class.java,"songs_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return database
        }
    }
}