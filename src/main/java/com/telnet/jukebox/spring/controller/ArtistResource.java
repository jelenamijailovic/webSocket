package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.service.ArtistService;
import com.telnet.jukebox.spring.service.SongService;

@RestController
@RequestMapping("/artists")
public class ArtistResource {

	//final static Logger logger = LogManager.getLogger(ArtistResource.class);

	@Autowired
	ArtistService artistService;

	@Autowired
	SongService songService;

	@CrossOrigin(origins= "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ArtistDTO> getAllArtists() {

		List<ArtistDTO> listOfArtists = new ArrayList<ArtistDTO>();
		try {
			listOfArtists = artistService.getAllArtists();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return listOfArtists;

	}

	@CrossOrigin(origins= "*")
	@GetMapping("/{artistId}/songs")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> getSongsByArtist(@PathVariable Long artistId) {
		//logger.info("Prikaz pesama za izvodjaca sa id-om " + artistId);

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();

		try {
			listOfSongs = songService.getSongsByArtist(artistId);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfSongs;
	}

	@CrossOrigin(origins= "*")
	@GetMapping("/top5artists")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ArtistDTO> getTop5Artists() {
		//logger.info("Prikaz top 5 izvodjaca");

		List<ArtistDTO> listOfTraffic = new ArrayList<ArtistDTO>();
		try {
			listOfTraffic = artistService.getTop5Artists();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfTraffic;
	}

}
