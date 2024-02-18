package com.enriquers.albumsphotosapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="album")
@Getter
@Setter
public class Album {

  @Id
  private Long id;
  private Long userId;
  private String title;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Photo> photos;

  @Override
  public String toString() {
    return "Album{" +
            "id=" + id +
            ", userId=" + userId +
            ", title='" + title + '\'' +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, title);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Album album = (Album) o;

    if (!Objects.equals(id, album.id)) return false;
    if (!Objects.equals(userId, album.userId)) return false;
    return Objects.equals(title, album.title);
  }
}
