package com.example.a6starter.data.entities

import com.squareup.moshi.Json

data class DogEntity(
    val data: List<DogBreed>,
    val links: Links
)

data class DogBreed(
    val id: String,
    val type: String,
    val attributes: Attributes,
    val relationships: Relationships
)

data class Attributes(
    val name: String,
    val description: String,
    val life: Life,
    @Json(name = "male_weight") val maleWeight: Weight,
    @Json(name = "female_weight") val femaleWeight: Weight,
    val hypoallergenic: Boolean
)

data class Life(
    val max: Int,
    val min: Int
)

data class Weight(
    val max: Int,
    val min: Int
)

data class Relationships(
    val group: Group
)

data class Group(
    val data: GroupData
)

data class GroupData(
    val id: String,
    val type: String
)

data class Links(
    val self: String,
    val current: String,
    val next: String,
    val last: String
)
