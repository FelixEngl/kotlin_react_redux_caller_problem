import kotlinx.serialization.Serializable

@Serializable
data class WordCloud(val word: String, val score: Float)
