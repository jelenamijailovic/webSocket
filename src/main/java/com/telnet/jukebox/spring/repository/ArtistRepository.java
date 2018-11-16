package com.telnet.jukebox.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

	public List<Artist> findArtistsByGenreId(Long genreId);
	public Artist findArtistById(Long artistId);
	
	@Query("SELECT a FROM com.telnet.jukebox.spring.model.Traffic t INNER JOIN t.song s INNER JOIN s.artist a GROUP BY a ORDER BY count(s.id) DESC")
	public Page<Artist> findTop5Artists(Pageable pageable);

	
}
