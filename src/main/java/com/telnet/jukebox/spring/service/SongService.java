package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Genre;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.Traffic;
import com.telnet.jukebox.spring.repository.GenreRepository;
import com.telnet.jukebox.spring.repository.SongRepository;
import com.telnet.jukebox.spring.repository.TrafficRepository;

@Service
public class SongService {

	@Autowired
	SongRepository songRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	TrafficRepository trafficRepository;

	@Autowired
	ArtistService artistService;

	@Autowired
	PriceService priceService;

	public List<SongDTO> getSongsByArtist(Long artistId) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();

		listOfSongs = songRepository.findSongsByArtistId(artistId);

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}

			return listOfSongsDTO;
		}
	}

	public List<SongDTO> getSongsByGenre(Long genreId) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();

		List<Artist> listOfArtists = new ArrayList<Artist>();

		listOfArtists = genreRepository.findById(genreId).get().getArtists();

		for (int i = 0; i < listOfArtists.size(); i++) {
			listOfSongs.addAll(listOfArtists.get(i).getSongs());
		}

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {
			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}

			return listOfSongsDTO;
		}
	}

	public Page<SongDTO> getAllSongsPagination(Pageable pageable) throws EmptyListException {
		List<SongDTO> listOfSongsDTO = new ArrayList<SongDTO>();

		List<Song> listOfSongs = new ArrayList<Song>();

		listOfSongs = songRepository.findAll(pageable).getContent();

		if (listOfSongs.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfSongs.size(); i++) {
				listOfSongsDTO.add(entityToDTO(listOfSongs.get(i)));
			}
		}

		Page<SongDTO> pageOfSongs = new PageImpl<SongDTO>(listOfSongsDTO, pageable,
				songRepository.findAll(pageable).getTotalElements());

		return pageOfSongs;

	}

	public List<SongDTO> recomended(Long id) throws UserNotFoundException {
		Random randomGenerator = new Random();

		List<Song> recomended = new ArrayList<Song>();
		List<SongDTO> recomendedDTO = new ArrayList<SongDTO>();

		List<Traffic> trafficByUser = trafficRepository.findTrafficByUserId(id);

		int max = 0;
		Long genre = (long) 0;

		for (int i = 0; i < trafficByUser.size(); i++) {
			int countSongId = trafficRepository.countBySongId(trafficByUser.get(i).getSong().getId());
			if (countSongId > max) {
				max = countSongId;
				genre = trafficByUser.get(i).getSong().getArtist().getGenre().getId();
			}
		}
		List<Song> songsByGenre = new ArrayList<Song>();
		Genre userGenre = genreRepository.findById(genre).get();
		List<Artist> artistsByGenre = userGenre.getArtists();
		for (int i = 0; i < artistsByGenre.size(); i++) {
			Artist artist = artistsByGenre.get(i);
			songsByGenre.addAll(artist.getSongs());
		}

		for (int br = 0; br < 3; br++) {
			int index = randomGenerator.nextInt(songsByGenre.size());
			Song randomSong = new Song();
			randomSong = songsByGenre.get(index);
			for (int i = 0; i < recomended.size(); i++) {
				if (randomSong == recomended.get(i)) {
					System.out.println("The song is repeated");
					
					if(index<songsByGenre.size()-1) {
					randomSong = songsByGenre.get(index + 1);
					i = recomended.size();
					}
					
				} else {
					randomSong = songsByGenre.get(index);
				}
			}
			recomended.add(randomSong);
		}

		for (int i = 0; i < recomended.size(); i++) {
			recomendedDTO.add(entityToDTO(recomended.get(i)));
		}
		return recomendedDTO;
	}

	public List<SongDTO> getTop5Songs() throws EmptyListException {
		List<SongDTO> listOfTrafficDTO = new ArrayList<SongDTO>();

		List<Song> listOfTraffic = new ArrayList<Song>();

		listOfTraffic = songRepository.findTop5Songs(PageRequest.of(0, 5)).getContent();

		if (listOfTraffic.isEmpty()) {
			throw new EmptyListException();
		} else {

			for (int i = 0; i < listOfTraffic.size(); i++) {
				listOfTrafficDTO.add(entityToDTO(listOfTraffic.get(i)));
			}
			return listOfTrafficDTO;

		}
	}

	public Song DTOToEntity(SongDTO song) {
		Song entity = new Song();
		entity.setId(song.getId());
		entity.setName(song.getName());
		try {
			entity.setArtist(artistService.DTOToEntity(song.getArtist()));
			entity.setPrice(priceService.DTOToEntity(song.getPrice()));
		} catch (NullPointerException e) {
			entity.setArtist(new Artist((long) 0));
			entity.setPrice(new Price((long) 0));
		}
		
		return entity;
	}

	public SongDTO entityToDTO(Song song) {
		SongDTO dto = new SongDTO();
		dto.setId(song.getId());
		dto.setName(song.getName());
		dto.setArtist(artistService.entityToDTO(song.getArtist()));
		dto.setPrice(priceService.entityToDTO(song.getPrice()));
		return dto;
	}
}
