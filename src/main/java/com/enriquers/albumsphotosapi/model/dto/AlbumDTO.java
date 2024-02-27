package com.enriquers.albumsphotosapi.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumDTO {
  private Long id;
  private Long userId;
  private String title;
  private List<PhotoDTO> photos;
}
