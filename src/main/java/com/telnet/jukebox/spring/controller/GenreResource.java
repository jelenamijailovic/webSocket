package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.GenreDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.service.ArtistService;
import com.telnet.jukebox.spring.service.GenreService;
import com.telnet.jukebox.spring.service.SongService;

@RestController
@RequestMapping("/genres")
public class GenreResource {

	// final static Logger logger = Logger.getLogger(GenreResource.class);

	@Autowired
	GenreService genreService;

	@Autowired
	ArtistService artistService;

	@Autowired
	SongService songService;

	@CrossOrigin(origins= "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<GenreDTO> getAllGenres() {
		// logger.info("Prikaz svih zanrova");

		List<GenreDTO> genres = new ArrayList<GenreDTO>();
		try {
			genres = genreService.getAllGenres();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return genres;
	}

	@CrossOrigin(origins= "*")
	@GetMapping("/{genreId}/songs")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> getSvePesmePoZanru(@PathVariable Long genreId) {
		// logger.info("Prikaz pesama za zanr sa id-om " + genreId);

		List<SongDTO> songs = new ArrayList<SongDTO>();

		try {
			songs = songService.getSongsByGenre(genreId);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return songs;
	}

}
