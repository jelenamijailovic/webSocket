package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.ExpTokenException;
import com.telnet.jukebox.spring.exceptions.SongNotFoundException;
import com.telnet.jukebox.spring.exceptions.TrafficNotFoundException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.model.Traffic;
import com.telnet.jukebox.spring.repository.TrafficRepository;

@Service
public class TrafficService {

	@Autowired
	TrafficRepository trafficRepository;

	@Autowired
	UserService userService = new UserService();
	
	@Autowired
	SongService songService = new SongService();

	public List<TrafficDTO> getAllTraffic() throws EmptyListException {
		List<TrafficDTO> listOfTrafficDTO = new ArrayList<TrafficDTO>();

		List<Traffic> listOfTraffic = new ArrayList<Traffic>();

		listOfTraffic = trafficRepository.findAll();

		if (listOfTraffic.isEmpty()) {
			throw new EmptyListException();
		} else {
			for (int i = 0; i < listOfTraffic.size(); i++) {
				listOfTrafficDTO.add(entityToDTO(listOfTraffic.get(i)));
			}
		}

		return listOfTrafficDTO;
	}

	/*
	public List<TrafficDTO> getTrafficBySong(Long songId) throws SongNotFoundException {
		List<TrafficDTO> listOfTrafficDTO = new ArrayList<TrafficDTO>();

		List<Traffic> listOfTraffic = new ArrayList<Traffic>();

		listOfTraffic = trafficRepository.findTrafficBySongId(songId);

		if (listOfTraffic.isEmpty()) {
			throw new SongNotFoundException(songId);
		} else {
			for (int i = 0; i < listOfTraffic.size(); i++) {
				listOfTrafficDTO.add(entityToDTO(listOfTraffic.get(i)));
			}
		}

		return listOfTrafficDTO;
	}

	public List<TrafficDTO> getTrafficByUser(Long userId) throws UserNotFoundException {
		List<TrafficDTO> listOfTrafficDTO = new ArrayList<TrafficDTO>();

		List<Traffic> listOfTraffic = new ArrayList<Traffic>();

		listOfTraffic = trafficRepository.findTrafficByUserId(userId);

		if (listOfTraffic.isEmpty()) {
			throw new UserNotFoundException(userId);
		} else {
			for (int i = 0; i < listOfTraffic.size(); i++) {
				listOfTrafficDTO.add(entityToDTO(listOfTraffic.get(i)));
			}
			return listOfTrafficDTO;
		}

	}

	public TrafficDTO getTraffic(Long trafficId) throws TrafficNotFoundException {
		Traffic traffic = new Traffic();
		traffic = trafficRepository.findById(trafficId).get();

		if (traffic.equals(null)) {
			throw new TrafficNotFoundException(trafficId);
		} else {
			return entityToDTO(traffic);
		}

	}*/

	public TrafficDTO addTraffic(TrafficDTO traffic) throws BadEntryException, ExpTokenException {

		java.util.Date datum = new java.util.Date();
		java.sql.Date date = new java.sql.Date(datum.getTime());

		traffic.setDate(date);

		TrafficDTO newTraffic = entityToDTO(trafficRepository.save(DTOToEntity(traffic)));

		return newTraffic;
	}

	/*public void deleteTraffic(Long trafficId) throws TrafficNotFoundException {
		trafficRepository.deleteById(trafficId);
	}*/

	public Traffic DTOToEntity(TrafficDTO traffic) {
		Traffic entity = new Traffic();
		entity.setId(traffic.getId());
		entity.setDate(traffic.getDate());
		entity.setSong(songService.DTOToEntity(traffic.getSong()));
		entity.setUser(traffic.getUser());
		return entity;
	}

	public TrafficDTO entityToDTO(Traffic traffic) {
		TrafficDTO dto = new TrafficDTO();
		dto.setId(traffic.getId());
		dto.setDate(traffic.getDate());
		dto.setSong(songService.entityToDTO(traffic.getSong()));
		dto.setUser(traffic.getUser());
		return dto;
	}

}
