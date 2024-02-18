package com.enriquers.albumsphotosapi.repository;

import com.enriquers.albumsphotosapi.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumPhotoRepository extends JpaRepository<Album, Long> {
}
