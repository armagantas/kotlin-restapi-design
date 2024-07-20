package com.trendyol.trainingmusicapi.entity

import jakarta.persistence.*
import org.apache.commons.lang3.mutable.Mutable


data class Playlist(
    val id: Long = 0,
    val name: String,
    val songs : MutableList<Song>,
    val isActive : Boolean = false
)



