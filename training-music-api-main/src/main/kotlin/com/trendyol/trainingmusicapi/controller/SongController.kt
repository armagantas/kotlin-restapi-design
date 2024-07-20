package com.trendyol.trainingmusicapi.controller

import com.trendyol.trainingmusicapi.entity.Song
import com.trendyol.trainingmusicapi.services.SongService
import com.trendyol.trainingmusicapi.utils.SongRequest
import com.trendyol.trainingmusicapi.utils.SongResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/v1/songs")
@Validated
class SongController(private val songService: SongService) {
    @GetMapping
    @Operation(summary = "Get all songs")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    fun getSongs(): ResponseEntity<List<Song>> {
        val songs = songService.getSongs()
        return ResponseEntity(songs, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get song by id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    fun getSongById(@PathVariable("id") id: Long): ResponseEntity<Song> {
        val song = songService.getSongById(id)
        return ResponseEntity(song, HttpStatus.OK)
    }

    @PostMapping
    @Operation(summary = "Create a new song")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Success"),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ApiResponse(responseCode = "500", description = "Internal server error")
    ])
    fun createSong(@Valid @RequestBody songRequest: SongRequest): ResponseEntity<SongResponse> {
        val song = songService.createSong(songRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(SongResponse.fromSong(song))
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a song")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Success"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    fun updateSong(@PathVariable id: Long, @Valid @RequestBody  songRequest: SongRequest): ResponseEntity<SongResponse> {
        val song = songService.updateSong(id, songRequest)
        return ResponseEntity.status(HttpStatus.OK).body(SongResponse.fromSong(song))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a song")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Success"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    fun deleteSong(@PathVariable id: Long): ResponseEntity<Void> {
        val song = songService.getSongById(id)
            ?: let {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }
        songService.deleteSong(song)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}