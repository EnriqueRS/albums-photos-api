package com.enriquers.albumsphotosapi.services;

import com.enriquers.albumsphotosapi.exceptions.AlbumPhotoException;
import com.enriquers.albumsphotosapi.model.Album;
import com.enriquers.albumsphotosapi.model.Photo;
import com.enriquers.albumsphotosapi.repository.AlbumPhotoRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlbumPhotoServiceImp implements AlbumPhotoService {

  private final WebClient webClient;
  private final AlbumPhotoRepository albumPhotoRepository;

  @Override
  public Mono<List<Album>> generateAlbums() throws AlbumPhotoException {

    Mono<List<Album>> albumsMono = webClient.get().uri("/albums")
        .retrieve()
        .bodyToFlux(Album.class)
        .collectList();

    Mono<List<Photo>> photosMono = webClient.get().uri("/photos")
        .retrieve()
        .bodyToFlux(Photo.class)
        .collectList();

    return Mono.zip(albumsMono, photosMono)
        .map(tuple -> {
          List<Album> albums = tuple.getT1();
          List<Photo> photos = tuple.getT2();

          Map<Long, List<Photo>> photosByAlbumId = photos.stream()
              .collect(Collectors.groupingBy(Photo::getAlbumId));

          return albums.stream()
              .map(album -> {
                Album newAlbum = new Album();
                newAlbum.setId(album.getId());
                newAlbum.setUserId(album.getUserId());
                newAlbum.setTitle(album.getTitle());
                newAlbum.setPhotos(photosByAlbumId.getOrDefault(album.getId(), Collections.emptyList()));
                return newAlbum;
              })
              .toList();
        });
  }

  @Override
  public void saveAlbums() throws AlbumPhotoException {
    List<Album> albumsGenerated = generateAlbums().block();
    log.info("Albums: {}", albumsGenerated);
    if (albumsGenerated != null) {
      albumPhotoRepository.saveAll(albumsGenerated);
    }
  }

  @Override
  public List<Album> getAlbumsFromDatabase() {
    return albumPhotoRepository.findAll();
  }

}
