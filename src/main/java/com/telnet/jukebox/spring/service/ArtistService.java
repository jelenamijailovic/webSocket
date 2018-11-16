package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.ArtistDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.repository.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	GenreService genreService;

	public List<ArtistDTO> getAllArtists() throws EmptyListException {
		List<ArtistDTO> listOfArtistsDTO = new ArrayList<ArtistDTO>();

		List<Artist> listOfArtists = new ArrayList<Artist>();

		listOfArtists = artistRepository.findAll();

		if (listOfArtists.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfArtists.size(); i++) {
				listOfArtistsDTO.add(entityToDTO(listOfArtists.get(i)));
			}

			return listOfArtistsDTO;
		}
	}

	public List<ArtistDTO> getArtistsByGenre(Long genreId) throws EmptyListException {
		List<ArtistDTO> listOfArtistsDTO = new ArrayList<ArtistDTO>();

		List<Artist> listOfArtists = new ArrayList<Artist>();

		listOfArtists = artistRepository.findArtistsByGenreId(genreId);

		if (listOfArtists.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfArtists.size(); i++) {
				listOfArtistsDTO.add(entityToDTO(listOfArtists.get(i)));
			}

			return listOfArtistsDTO;
		}
	}

	public List<ArtistDTO> getTop5Artists() throws EmptyListException {
		List<ArtistDTO> listOfTrafficDTO = new ArrayList<ArtistDTO>();

		List<Artist> listOfTraffic = new ArrayList<Artist>();

		listOfTraffic = artistRepository.findTop5Artists(PageRequest.of(0, 5)).getContent();

		if (listOfTraffic.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfTraffic.size(); i++) {
				listOfTrafficDTO.add(entityToDTO(listOfTraffic.get(i)));
			}
			return listOfTrafficDTO;
		}
	}

	public Artist DTOToEntity(ArtistDTO artist) {
		Artist entity = new Artist();
		entity.setId(artist.getId());
		entity.setName(artist.getName());
		try {
		entity.setGenre(genreService.DTOToEntity(artist.getGenre()));
		} catch(NullPointerException e) {
			entity.setGenre(new Genre());
		}
		return entity;
	}
	
	public ArtistDTO entityToDTO(Artist artist) {
		ArtistDTO dto = new ArtistDTO();
		dto.setId(artist.getId());
		dto.setName(artist.getName());
		dto.setGenre(genreService.entityToDTO(artist.getGenre()));
		return dto;
	}
}
