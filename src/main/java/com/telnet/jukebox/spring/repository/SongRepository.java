package com.telnet.jukebox.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

	public List<Song> findSongsByArtistId(Long artistId);
	public List<Song> findSongsByPriceId(Long priceId);

	@Query("SELECT distinct s FROM com.telnet.jukebox.spring.model.Traffic t INNER JOIN t.song s GROUP BY s ORDER BY count(t.song) DESC")
	public Page<Song> findTop5Songs(Pageable pageable);

}
