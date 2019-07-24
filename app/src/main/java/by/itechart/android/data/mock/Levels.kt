package by.itechart.android.data.mock

import by.itechart.android.data.entity.Level
import by.itechart.android.data.entity.LevelsResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.SingleEmitter

object Levels {
    private const val mockJson = "{\n" +
            "   \"items\":[\n" +
            "      {\n" +
            "         \"title\":\"Level 1 - Introduction\",\n" +
            "         \"color\":\"blue\",\n" +
            "         \"pass_rate\":75,\n" +
            "         \"items\":[\n" +
            "            {\n" +
            "               \"title\":\"An intro to financial markets\",\n" +
            "               \"topic_count\":6,\n" +
            "               \"star_count\":2\n" +
            "            },\n" +
            "            {\n" +
            "               \"title\":\"Ways to access the markets\",\n" +
            "               \"topic_count\":4,\n" +
            "               \"star_count\":3\n" +
            "            }\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"title\":\"Level 2 - Foundation\",\n" +
            "         \"color\":\"green\",\n" +
            "         \"pass_rate\":0,\n" +
            "         \"items\":[\n" +
            "            {\n" +
            "               \"title\":\"What is FX trading?\",\n" +
            "               \"topic_count\":7,\n" +
            "               \"star_count\":2\n" +
            "            },\n" +
            "            {\n" +
            "               \"title\":\"Leverage\",\n" +
            "               \"topic_count\":7,\n" +
            "               \"star_count\":3\n" +
            "            },\n" +
            "            {\n" +
            "               \"title\":\"Fundamental analysis\",\n" +
            "               \"topic_count\":7,\n" +
            "               \"star_count\":0\n" +
            "            }\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"title\":\"Level 3 - Ways to trade\",\n" +
            "         \"color\":\"red\",\n" +
            "         \"pass_rate\":0,\n" +
            "         \"items\":[\n" +
            "            {\n" +
            "               \"title\":\"Chart Pattern Reversal\",\n" +
            "               \"topic_count\":6,\n" +
            "               \"star_count\":0\n" +
            "            }\n" +
            "         ]\n" +
            "      }\n" +
            "   ]\n" +
            "}\n"

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    fun getCards(): Single<List<Level>> = Single.create { emitter: SingleEmitter<List<Level>> ->
        try {
            val levelsResponse = gson.fromJson(mockJson, LevelsResponse::class.java)
            emitter.onSuccess(levelsResponse.levels)
        } catch (exception: Exception) {
            emitter.onError(exception)
        }
    }
}