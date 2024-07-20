package com.trendyol.trainingmusicapi.exceptions.errors

class SongNotFoundException(private val songId: Long) : RuntimeException("Song not found for given id: $songId")