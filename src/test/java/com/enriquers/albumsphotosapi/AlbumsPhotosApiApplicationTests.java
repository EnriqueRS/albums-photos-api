package com.enriquers.albumsphotosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.enriquers.albumsphotosapi.controller.AlbumPhotoController;
import com.enriquers.albumsphotosapi.mapper.AlbumConverter;
import com.enriquers.albumsphotosapi.model.Album;
import com.enriquers.albumsphotosapi.model.Photo;
import com.enriquers.albumsphotosapi.model.dto.AlbumDTO;
import com.enriquers.albumsphotosapi.model.dto.PhotoDTO;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AlbumsPhotosApiApplicationTests {

  @Autowired
  private AlbumPhotoController albumPhotoController;

  @Autowired
  private AlbumConverter albumConverter;


  @Test
  void testGetAlbumsPhotosFromAPI() {
    List<AlbumDTO> albums = albumPhotoController.getAlbumsPhotosFromAPI();
    // Assert based on the API return always same response
    assertEquals(100, albums.size());
  }

  @Test
  void testGetAlbumsPhotosFromDatabase() {
    albumPhotoController.storeAlbumsPhotosFromAPI();
    List<Album> albums = albumPhotoController.getAlbumsPhotosFromDatabase()
        .stream().map(albumConverter::convertToAlbum).toList();
    // Assert based on the API return always same response
    assertEquals(100, albums.size());
  }

  @Test
  void testSaveAlbums() {
    albumPhotoController.storeAlbumsPhotosFromAPI();
    List<Album> albumsPhotosFromDatabase = albumPhotoController.getAlbumsPhotosFromDatabase()
        .stream().map(albumConverter::convertToAlbum).toList();
    List<Album> albumsPhotosFromAPI = albumPhotoController.getAlbumsPhotosFromAPI()
        .stream().map(albumConverter::convertToAlbum).toList();
    assertEquals(albumsPhotosFromAPI.size(), albumsPhotosFromDatabase.size());
    assertEquals(albumsPhotosFromAPI, albumsPhotosFromDatabase);
  }

  @Test
  @DisplayName("setPhotos sets a list of photos to an album")
  void setPhotos_SetsListOfPhotos_ToAlbum() {
    Album album = new Album();
    Photo photo1 = new Photo();
    Photo photo2 = new Photo();
    photo1.setAlbumId(1L);
    photo1.setUrl("https://via.placeholder.com/600/92c952");
    photo1.setTitle("accusamus beatae ad facilis cum similique qui sunt");
    photo2.setAlbumId(1L);
    photo2.setUrl("https://via.placeholder.com/600/771796");
    photo2.setTitle("reprehenderit est deserunt velit ipsam");
    List<Photo> photos = Arrays.asList(photo1, photo2);

    album.setPhotos(photos);

    assertEquals(photos, album.getPhotos());
  }

  @Test
  void testStoreAndGetAlbumsPhotos() {
    albumPhotoController.storeAlbumsPhotosFromAPI();

    List<Album> albums = albumPhotoController.getAlbumsPhotosFromDatabase()
        .stream().map(albumConverter::convertToAlbum).toList();;

    assertTrue(albums.stream().anyMatch(album -> album.getUserId() == 1L && album.getId() == 1L && "quidem molestiae enim".equals(album.getTitle())));
  }

  @Test
  @Transactional
  void testStoreAndGetAlbumsPhotos_WithSpecificPhotos() {
    albumPhotoController.storeAlbumsPhotosFromAPI();

    List<AlbumDTO> albums = albumPhotoController.getAlbumsPhotosFromDatabase();

    assertTrue(albums.stream().anyMatch(album -> {
      if (album.getId() == 1L) {
        List<PhotoDTO> photos = album.getPhotos();
        return photos.stream().anyMatch(photo -> photo.getId() == 1L && "accusamus beatae ad facilis cum similique qui sunt".equals(photo.getTitle()) && "https://via.placeholder.com/600/92c952".equals(photo.getUrl()) && "https://via.placeholder.com/150/92c952".equals(photo.getThumbnailUrl())) &&
            photos.stream().anyMatch(photo -> photo.getId() == 2L && "reprehenderit est deserunt velit ipsam".equals(photo.getTitle()) && "https://via.placeholder.com/600/771796".equals(photo.getUrl()) && "https://via.placeholder.com/150/771796".equals(photo.getThumbnailUrl())) &&
            photos.stream().anyMatch(photo -> photo.getId() == 3L && "officia porro iure quia iusto qui ipsa ut modi".equals(photo.getTitle()) && "https://via.placeholder.com/600/24f355".equals(photo.getUrl()) && "https://via.placeholder.com/150/24f355".equals(photo.getThumbnailUrl())) &&
            photos.stream().anyMatch(photo -> photo.getId() == 4L && "culpa odio esse rerum omnis laboriosam voluptate repudiandae".equals(photo.getTitle()) && "https://via.placeholder.com/600/d32776".equals(photo.getUrl()) && "https://via.placeholder.com/150/d32776".equals(photo.getThumbnailUrl())) &&
            photos.stream().anyMatch(photo -> photo.getId() == 5L && "natus nisi omnis corporis facere molestiae rerum in".equals(photo.getTitle()) && "https://via.placeholder.com/600/f66b97".equals(photo.getUrl()) && "https://via.placeholder.com/150/f66b97".equals(photo.getThumbnailUrl()));
      }
      return false;
    }));
  }

}
