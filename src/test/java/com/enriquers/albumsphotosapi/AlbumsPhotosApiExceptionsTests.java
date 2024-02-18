package com.enriquers.albumsphotosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.enriquers.albumsphotosapi.controller.AlbumPhotoController;
import com.enriquers.albumsphotosapi.exceptions.AlbumPhotoException;
import com.enriquers.albumsphotosapi.exceptions.GlobalExceptionHandler;
import com.enriquers.albumsphotosapi.services.AlbumPhotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class AlbumsPhotosApiExceptionsTests {

  @Autowired
  private AlbumPhotoController albumPhotoController;

  @MockBean
  private AlbumPhotoService albumPhotoService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void main() {
    assertThrows(IllegalArgumentException.class, () -> AlbumsPhotosApiApplication.main(null));
  }


  @Test
  void testAlbumPhotoException() {
    when(albumPhotoService.getAlbumsFromDatabase()).thenThrow(new AlbumPhotoException("Test exception"));

    assertThrows(AlbumPhotoException.class, () -> albumPhotoController.getAlbumsPhotosFromDatabase());
  }

  @Test
  void testAlbumPhotoException_WithMessageAndCause() {
    Exception cause = new Exception("Cause");
    AlbumPhotoException exception = assertThrows(AlbumPhotoException.class, () -> {
      throw new AlbumPhotoException("Test exception", cause);
    });

    assertEquals("Test exception", exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

  @Test
  void testHandleException() {
    GlobalExceptionHandler handler = new GlobalExceptionHandler();
    Exception exception = new Exception("Test exception");
    ResponseEntity<String> response = handler.handleException(exception);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals("Test exception", response.getBody());
  }

}
