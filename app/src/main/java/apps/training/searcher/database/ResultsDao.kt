package apps.training.searcher.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import apps.training.searcher.data.Results

@Dao
interface ResultsDao {
    //For Debug Purpose
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(resultData : List<Results>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resultData: Results)

    @Query("SELECT * FROM results WHERE trackName  LIKE '%' || :trackName || '%' OR collectionName LIKE '%' || :trackName || '%' OR collectionCensoredName LIKE '%' || :trackName || '%' OR artistName LIKE '%' || :trackName || '%' OR primaryGenreName LIKE '%' || :trackName || '%' ")
    fun getResults(trackName : String) : LiveData<List<Results>>

    @Query("SELECT * FROM results")
    fun getResults() : LiveData<List<Results>>
}