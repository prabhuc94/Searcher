package apps.training.searcher.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import apps.training.searcher.database.Converter

data class SearchResult(
    val resultCount : Int ?= null,
    val results : List<Results> ?= null
)
@Entity(tableName = "results")
data class Results(
    @PrimaryKey(autoGenerate = false)
    val artistId: Int? = null, // 409812684
    val artistName: String? = null, // Apple
    val artistViewUrl: String? = null, // https://podcasts.apple.com/us/artist/events-at-the-apple-store/409812684?uo=4
    val artworkUrl100: String? = null, // https://is5-ssl.mzstatic.com/image/thumb/Podcasts113/v4/e6/7e/b4/e67eb4e0-74be-e664-55f4-5ce80479d38e/mza_7148052795381163715.jpg/100x100bb.jpg
    val artworkUrl30: String? = null, // https://is5-ssl.mzstatic.com/image/thumb/Podcasts113/v4/e6/7e/b4/e67eb4e0-74be-e664-55f4-5ce80479d38e/mza_7148052795381163715.jpg/30x30bb.jpg
    val artworkUrl60: String? = null, // https://is5-ssl.mzstatic.com/image/thumb/Podcasts113/v4/e6/7e/b4/e67eb4e0-74be-e664-55f4-5ce80479d38e/mza_7148052795381163715.jpg/60x60bb.jpg
    val artworkUrl600: String? = null, // https://is5-ssl.mzstatic.com/image/thumb/Podcasts113/v4/e6/7e/b4/e67eb4e0-74be-e664-55f4-5ce80479d38e/mza_7148052795381163715.jpg/600x600bb.jpg
    val collectionCensoredName: String? = null, // Malcolm Gladwell, Revisionist History: Special Event
    val collectionExplicitness: String? = null, // cleaned
    val collectionHdPrice: Double? = null, // 0
    val collectionId: Int? = null, // 1144280043
    val collectionName: String? = null, // Malcolm Gladwell, Revisionist History: Special Event
    val collectionPrice: Double? = null, // 0.00
    val collectionViewUrl: String? = null, // https://podcasts.apple.com/us/podcast/malcolm-gladwell-revisionist-history-special-event/id1144280043?uo=4
    val contentAdvisoryRating: String? = null, // Clean
    val country: String? = null, // USA
    val currency: String? = null, // USD
    val feedUrl: String? = null, // https://applehosted.podcasts.apple.com/eaas/us/special_event/gladwell/gladwell.xml
    @TypeConverters(Converter::class) val genreIds: List<String>? = null,
    @TypeConverters(Converter::class) val genres: List<String>? = null,
    val kind: String? = null, // podcast
    val primaryGenreName: String? = null, // Society & Culture
    val releaseDate: String? = null, // 2016-08-15T12:01:00Z
    val trackCensoredName: String? = null, // Malcolm Gladwell, Revisionist History: Special Event
    val trackCount: Int? = null, // 2
    val trackExplicitness: String? = null, // cleaned
    val trackHdPrice: Double? = null, // 0
    val trackHdRentalPrice: Double? = null, // 0
    val trackId: Int? = null, // 1144280043
    val trackName: String? = null, // Malcolm Gladwell, Revisionist History: Special Event
    val trackPrice: Double? = null, // 0.00
    val trackRentalPrice: Double? = null, // 0
    val trackViewUrl: String? = null, // https://podcasts.apple.com/us/podcast/malcolm-gladwell-revisionist-history-special-event/id1144280043?uo=4
    val wrapperType: String? = null // track
) {
//    @PrimaryKey(autoGenerate = true) var id : Int ?= null
}