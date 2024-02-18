package com.enriquers.albumsphotosapi.services;

import com.enriquers.albumsphotosapi.exceptions.AlbumPhotoException;
import com.enriquers.albumsphotosapi.model.Album;
import java.util.List;
import reactor.core.publisher.Mono;

public interface AlbumPhotoService {

  Mono<List<Album>> generateAlbums() throws AlbumPhotoException;

  void saveAlbums();

  List<Album> getAlbumsFromDatabase();
}
