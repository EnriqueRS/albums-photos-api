package com.enriquers.albumsphotosapi.mapper;

import com.enriquers.albumsphotosapi.model.Photo;
import com.enriquers.albumsphotosapi.model.dto.PhotoDTO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PhotoConverter {

  public List<PhotoDTO> convertToPhotoDTOList(List<Photo> photos) {
    return photos.stream().map(this::convertToPhotoDTO).toList();
  }

  public PhotoDTO convertToPhotoDTO(Photo photo) {
    PhotoDTO dto = new PhotoDTO();
    dto.setId(photo.getId());
    dto.setAlbumId(photo.getAlbumId());
    dto.setTitle(photo.getTitle());
    dto.setUrl(photo.getUrl());
    dto.setThumbnailUrl(photo.getThumbnailUrl());
    return dto;
  }

  public List<Photo> convertToPhotoList(List<PhotoDTO> photos) {
    return photos.stream().map(this::convertToPhoto).toList();
  }

  private Photo convertToPhoto(PhotoDTO photoDTO) {
    Photo photo = new Photo();
    photo.setId(photoDTO.getId());
    photo.setAlbumId(photoDTO.getAlbumId());
    photo.setTitle(photoDTO.getTitle());
    photo.setUrl(photoDTO.getUrl());
    photo.setThumbnailUrl(photoDTO.getThumbnailUrl());
    return photo;
  }
}
