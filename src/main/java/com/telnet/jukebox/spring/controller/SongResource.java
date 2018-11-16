package com.telnet.jukebox.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.exceptions.EmptyListException;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.telnet.jukebox.spring.service.SongService;
import com.telnet.jukebox.spring.service.TrafficService;

@RestController
@RequestMapping("/songs")
public class SongResource {

	// final static Logger logger = Logger.getLogger(PesmaResource.class);

	@Autowired
	SongService songService = new SongService();

	@Autowired
	TrafficService trafficService = new TrafficService();

	@CrossOrigin(origins= "*")
	@GetMapping("/pagination/{page}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Page<SongDTO> getAllSongsPagination(@PathVariable int page) {
		// logger.info("Prikaz svih pesama");

		page = page - 1;
		Page<SongDTO> listOfSongs = null;
		try {
			listOfSongs = songService.getAllSongsPagination(PageRequest.of(page, 5, new Sort(Direction.ASC, "id")));
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfSongs;
	}

	/*
	 * @GetMapping("/{songId}/traffic")
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public List<TrafficDTO> getTrafficBySong(@PathVariable Long
	 * songId) { // logger.info("Prikaz prometa za pesmu sa id-om " + pesmaId);
	 * 
	 * List<TrafficDTO> listOfTraffic = new ArrayList<TrafficDTO>(); try {
	 * listOfTraffic = trafficService.getTrafficBySong(songId); } catch
	 * (ClassNotFoundException e) { e.printStackTrace(); }
	 * 
	 * return listOfTraffic; }
	 */

	@CrossOrigin(origins= "*")
	@GetMapping("/top5songs")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> getTop5Songs() {
		// logger.info("Prikaz top 5 pesama");

		List<SongDTO> listOfTraffic = new ArrayList<SongDTO>();
		try {
			listOfTraffic = songService.getTop5Songs();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}

		return listOfTraffic;
	}

}
