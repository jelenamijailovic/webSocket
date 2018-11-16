package com.telnet.jukebox.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
