package com.enriquers.albumsphotosapi.controller;

import com.enriquers.albumsphotosapi.exceptions.AlbumPhotoException;
import com.enriquers.albumsphotosapi.mapper.AlbumConverter;
import com.enriquers.albumsphotosapi.model.dto.AlbumDTO;
import com.enriquers.albumsphotosapi.services.AlbumPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AlbumPhotoController", description = "Controller for managing albums and photos")
@RestController
@RequestMapping("/api/v1/album-photo")
@RequiredArgsConstructor
public class AlbumPhotoController {

  private final AlbumPhotoService albumPhotoService;
  private final AlbumConverter albumConverter;

  @Operation(summary = "Get albums photos from API (no store in database)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved albums photos"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<AlbumDTO> getAlbumsPhotosFromAPI() {
    return Objects.requireNonNull(albumPhotoService.generateAlbums().block())
        .stream()
        .map(albumConverter::convertToAlbumDTO)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Store albums photos from API in database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully stored albums photos"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void storeAlbumsPhotosFromAPI() throws AlbumPhotoException {
    albumPhotoService.saveAlbums();
  }

  @Operation(summary = "Get albums photos from database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved albums photos from database"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/stored")
  @ResponseStatus(HttpStatus.OK)
  public List<AlbumDTO> getAlbumsPhotosFromDatabase() {
    return albumPhotoService.getAlbumsFromDatabase()
        .stream()
        .map(albumConverter::convertToAlbumDTO)
        .collect(Collectors.toList());
  }

}
