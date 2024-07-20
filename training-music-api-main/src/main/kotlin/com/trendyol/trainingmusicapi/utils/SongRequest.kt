package com.trendyol.trainingmusicapi.utils

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SongRequest (
    @field:NotBlank(message = "Song name must not be blank")
    @field:Size(min = 6, message = "Song name must have at least 6 characters")
    var title: String,

    @field:NotBlank(message = "Song artist must not be blank")
    @field:NotBlank(message = "Song artist must have at least one song")
    var artist: String
)