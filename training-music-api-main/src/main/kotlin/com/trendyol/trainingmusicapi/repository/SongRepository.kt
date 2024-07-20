package com.trendyol.trainingmusicapi.repository

import com.trendyol.trainingmusicapi.entity.Song
import org.springframework.stereotype.Repository

@Repository
class SongRepository {
    private val songs = hashMapOf<Long, Song>()
    private var currentId = 1L

    fun getSongs(): List<Song> = songs.values.toList()

    fun getSongById(id: Long): Song? = songs[id]

    fun save(song: Song): Song {
        val id = song.id.takeIf { it != 0L } ?: currentId++
        val newSong = song.copy(id = id)
        songs[id] = newSong
        return newSong
    }

    fun delete(song : Song) {
        songs.remove(song.id)
    }
}