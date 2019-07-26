package by.itechart.android.data.mock

import by.itechart.android.data.entity.LevelsResponse
import com.google.gson.Gson

class Levels(private val gson: Gson) {
    private val mockJson = "{\n" +
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

    fun getLevels() = gson.fromJson(mockJson, LevelsResponse::class.java).levels
}