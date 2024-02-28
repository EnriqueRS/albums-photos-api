package com.enriquers.albumsphotosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.enriquers.albumsphotosapi.model.Album;
import com.enriquers.albumsphotosapi.model.Photo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlbumsPhotosApiModelTests {

  @Test
  void testAlbumToString() {
    Album album = new Album();
    album.setId(1L);
    album.setUserId(1L);
    album.setTitle("Test Album");

    String expected = "Album{id=1, userId=1, title='Test Album'}";
    assertEquals(expected, album.toString());
  }

  @Test
  void testAlbumHashCode() {
    Album album1 = new Album();
    album1.setId(1L);
    album1.setUserId(1L);
    album1.setTitle("Test Album");

    Album album2 = new Album();
    album2.setId(1L);
    album2.setUserId(1L);
    album2.setTitle("Test Album");

    assertEquals(album1.hashCode(), album2.hashCode());
  }

  @Test
  void testAlbumEquals() {
    Album album1 = new Album();
    album1.setId(1L);
    album1.setUserId(1L);
    album1.setTitle("Test Album");

    Album album2 = new Album();
    album2.setId(1L);
    album2.setUserId(1L);
    album2.setTitle("Test Album");

    assertEquals(album1, album2);

    album2.setTitle("Different Title");
    assertNotEquals(album1, album2);
  }

  @Test
  void testPhotoToString() {
    Photo photo = new Photo();
    photo.setId(1L);
    photo.setAlbumId(1L);
    photo.setTitle("Test Photo");
    photo.setUrl("http://test.com");
    photo.setThumbnailUrl("http://test.com/thumbnail");

    String expected = "Photo{id=1, albumId=1, title='Test Photo', url='http://test.com', thumbnailUrl='http://test.com/thumbnail'}";
    assertEquals(expected, photo.toString());
  }

}
