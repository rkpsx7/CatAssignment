package com.dev_akash.catassignment.feature_catshow.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dev_akash.catassignment.feature_catshow.data.model.BreedDto
import com.dev_akash.catassignment.feature_catshow.data.model.WeightDto
import com.dev_akash.catassignment.feature_catshow.ui.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class TypeConvertors(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromJsonToWeight(json: String): WeightDto {
        return jsonParser.fromJson<WeightDto>(
            json, object : TypeToken<ArrayList<WeightDto>>() {}.type
        )
    }

    @TypeConverter
    fun fromWeightToJson(weightDto: WeightDto): String {
        return jsonParser.toJson(
            weightDto, object : TypeToken<ArrayList<WeightDto>>() {}.type
        )
    }

    @TypeConverter
    fun fromJsonToBreed(json: String): List<BreedDto> {
        return jsonParser.fromJson<ArrayList<BreedDto>>(
            json, object : TypeToken<ArrayList<BreedDto>>() {}.type
        )
    }

    @TypeConverter
    fun fromBreedsToJson(breeds: List<BreedDto>): String {
        return jsonParser.toJson(
            breeds, object : TypeToken<ArrayList<BreedDto>>() {}.type
        )
    }

}