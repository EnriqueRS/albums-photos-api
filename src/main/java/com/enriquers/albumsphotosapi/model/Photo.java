package com.enriquers.albumsphotosapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="photo")
@Getter
@Setter
public class Photo {

  @Id
  private Long id;
  private Long albumId;
  private String title;
  private String url;
  private String thumbnailUrl;

  @Override
  public String toString() {
    return "Photo{" +
            "id=" + id +
            ", albumId=" + albumId +
            ", title='" + title + '\'' +
            ", url='" + url + '\'' +
            ", thumbnailUrl='" + thumbnailUrl + '\'' +
            '}';
  }

}
