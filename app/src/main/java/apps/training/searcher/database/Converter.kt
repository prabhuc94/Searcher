package apps.training.searcher.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converter {

    @JvmStatic @TypeConverter
    fun listToString(listData : List<String>?) : String? {
        return if (listData.isNullOrEmpty()){
            Gson().toJson(listData)
        } else {
            ""
        }
    }

    @JvmStatic @TypeConverter
    fun stringToList(content : String?) : List<String>? = Gson().fromJson(content, object : TypeToken<List<String?>?>() {}.type)
}