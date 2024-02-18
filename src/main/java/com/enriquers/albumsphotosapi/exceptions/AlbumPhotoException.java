package com.enriquers.albumsphotosapi.exceptions;

public class AlbumPhotoException extends RuntimeException {

    public AlbumPhotoException(String message) {
      super(message);
    }

    public AlbumPhotoException(String message, Throwable cause) {
      super(message, cause);
    }
}
