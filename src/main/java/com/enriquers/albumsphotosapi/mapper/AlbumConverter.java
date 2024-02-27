package com.enriquers.albumsphotosapi.mapper;

import com.enriquers.albumsphotosapi.model.Album;
import com.enriquers.albumsphotosapi.model.dto.AlbumDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumConverter {

  private final PhotoConverter photoConverter;

  public AlbumDTO convertToAlbumDTO(Album album) {
    AlbumDTO dto = new AlbumDTO();
    dto.setId(album.getId());
    dto.setUserId(album.getUserId());
    dto.setTitle(album.getTitle());
    dto.setPhotos(photoConverter.convertToPhotoDTOList(album.getPhotos()));
    return dto;
  }

  public Album convertToAlbum(AlbumDTO albumDTO) {
    Album album = new Album();
    album.setId(albumDTO.getId());
    album.setTitle(albumDTO.getTitle());
    album.setUserId(albumDTO.getUserId());
    album.setPhotos(photoConverter.convertToPhotoList(albumDTO.getPhotos()));
    return album;
  }
}
