package com.enriquers.albumsphotosapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {

  private Long id;
  private Long albumId;
  private String title;
  private String url;
  private String thumbnailUrl;

}
