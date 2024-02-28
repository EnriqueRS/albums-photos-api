package com.enriquers.albumsphotosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.enriquers.albumsphotosapi.mapper.AlbumConverter;
import com.enriquers.albumsphotosapi.mapper.PhotoConverter;
import com.enriquers.albumsphotosapi.model.Album;
import com.enriquers.albumsphotosapi.model.Photo;
import com.enriquers.albumsphotosapi.model.dto.AlbumDTO;
import com.enriquers.albumsphotosapi.model.dto.PhotoDTO;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ConvertersUnitTests {

  @Mock
  private PhotoConverter photoConverter;

  @InjectMocks
  private AlbumConverter albumConverter;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testConvertToAlbumDTO() {
    Photo photo = new Photo();
    List<Photo> photos = List.of(photo);
    Album album = new Album();
    album.setId(1L);
    album.setUserId(1L);
    album.setTitle("Test Album");
    album.setPhotos(photos);

    PhotoDTO photoDTO = new PhotoDTO();
    List<PhotoDTO> photoDTOs = Arrays.asList(photoDTO);
    when(photoConverter.convertToPhotoDTOList(photos)).thenReturn(photoDTOs);

    AlbumDTO result = albumConverter.convertToAlbumDTO(album);

    assertEquals(1L, result.getId());
    assertEquals("Test Album", result.getTitle());
    assertEquals(1L, result.getUserId());
    assertEquals(photoDTOs, result.getPhotos());
  }

  @Test
  void testConvertToAlbum() {
    PhotoDTO photoDTO = new PhotoDTO();
    List<PhotoDTO> photoDTOs = List.of(photoDTO);
    AlbumDTO albumDTO = new AlbumDTO();
    albumDTO.setId(1L);
    albumDTO.setUserId(1L);
    albumDTO.setTitle("Test Album DTO");
    albumDTO.setPhotos(photoDTOs);

    Photo photo = new Photo();
    List<Photo> photos = List.of(photo);
    when(photoConverter.convertToPhotoList(photoDTOs)).thenReturn(photos);

    Album result = albumConverter.convertToAlbum(albumDTO);

    assertEquals(1L, result.getId());
    assertEquals("Test Album DTO", result.getTitle());
    assertEquals(1L, result.getUserId());
    assertEquals(photos, result.getPhotos());
  }
}
