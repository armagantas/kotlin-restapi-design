package com.trendyol.trainingmusicapi.utils

import com.trendyol.trainingmusicapi.entity.Song

data class SongResponse (
    val id: Long,
            val title: String,
                val artist: String,
                        val isRunning: Boolean = false

) {
    companion object {
        fun fromSong(song: Song) : SongResponse {
            return SongResponse(song.id,
                song.title,
                song.artist,
                song.isRunning)
        }
    }
}