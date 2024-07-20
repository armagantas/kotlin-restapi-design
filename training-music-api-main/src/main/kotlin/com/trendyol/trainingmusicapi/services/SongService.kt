package com.trendyol.trainingmusicapi.services

import com.trendyol.trainingmusicapi.entity.Song
import com.trendyol.trainingmusicapi.repository.SongRepository
import com.trendyol.trainingmusicapi.utils.SongRequest
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class SongService(private val songRepository: SongRepository) {
    @Cacheable("Songs")

    fun getSongs(): List<Song> = songRepository.getSongs()

    fun getSongById(id: Long) : Song? = songRepository.getSongById(id) ?: throw RuntimeException("Song with id $id not found")

    fun createSong(req: SongRequest): Song {
        val song = Song(
            id = 0L,
            title = req.title,
            artist = req.artist,
            isRunning = false
        )
        return songRepository.save(song)
    }

    fun updateSong(id: Long, req: SongRequest): Song {
        val existingSong = getSongById(id)
        val updatedSong = existingSong!!.copy(
            title = req.title,
            artist = req.artist,
            isRunning = true
        )
        return songRepository.save(updatedSong)
    }

    fun deleteSong(song : Song) {
        songRepository.delete(song)
    }
}