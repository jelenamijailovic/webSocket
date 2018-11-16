package com.telnet.jukebox.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import io.swagger.annotations.ApiResponse;

import com.telnet.jukebox.spring.dto.SongDTO;
import com.telnet.jukebox.spring.dto.TrafficDTO;
import com.telnet.jukebox.spring.dto.UserDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.ExpTokenException;
import com.telnet.jukebox.spring.exceptions.TrafficNotFoundException;
import com.telnet.jukebox.spring.exceptions.UserNotFoundException;
import com.telnet.jukebox.spring.model.User;
import com.telnet.jukebox.spring.security.JwtTokenProvider;
import com.telnet.jukebox.spring.service.SongService;
import com.telnet.jukebox.spring.service.TrafficService;
import com.telnet.jukebox.spring.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/traffic")
@Api(value = "/traffic", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Traffic")
public class TrafficResource {

	// final static Logger logger = Logger.getLogger(PrometResource.class);

	@Autowired
	public TrafficService trafficService;
	
	@Autowired
	public JwtTokenProvider token;

	@Autowired
	public SongService songService;
	
	@Value("${app.jwtSecret}")
	private String secret;

	  @CrossOrigin(origins= "*")
	  @GetMapping
	  @ResponseStatus(HttpStatus.OK)
	  @ResponseBody public List<TrafficDTO> getSviPrometi() { //
	 //logger.info("Prikaz svih prometa");
	  
	  List<TrafficDTO> listOfTrafficDTO = new ArrayList<TrafficDTO>(); try {
	  listOfTrafficDTO = trafficService.getAllTraffic(); 
	  } catch(EmptyListException e) {
		  e.printStackTrace();
	  }
	 
	  return listOfTrafficDTO; }
	  
	 /*@GetMapping("/{trafficId}")
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public TrafficDTO getPromet(@PathVariable Long trafficId) { //
	 * logger.info("Prikaz prometa sa id-om " + trafficId);
	 * 
	 * TrafficDTO traffic = new TrafficDTO(); try { traffic =
	 * trafficService.getTraffic(trafficId); } catch (TrafficNotFoundException e) {
	 * e.printStackTrace(); }
	 * 
	 * return traffic; }
	 */

	@CrossOrigin(origins= "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Add new traffic")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	public void addTraffic(@RequestHeader("Authorization") String authorization, @RequestBody TrafficDTO traffic) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		Long id = token.getUserIdFromJWT(authorization.substring(7));
		traffic.setUser(new User(id));

		System.out.println("User id from token is  :" + id);

		try {
			trafficService.addTraffic(traffic);
		} catch (ExpTokenException e) {
			e.printStackTrace();
		} catch (BadEntryException e) {
			e.printStackTrace();
		}

	}

	/*@DeleteMapping("/{trafficId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTraffic(@PathVariable Long trafficId) {
		// logger.info("Brisanje prometa sa id-om " + prometId);

		try {
			trafficService.deleteTraffic(trafficId);
		} catch (TrafficNotFoundException e) {
			e.printStackTrace();
		}

	}*/

	@CrossOrigin(origins= "*")
	@GetMapping("/recomended")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SongDTO> recomended(@RequestHeader("Authorization") String authorization) {
		
		Long id = token.getUserIdFromJWT(authorization.substring(7));

		List<SongDTO> listOfSongs = new ArrayList<SongDTO>();
		try {
			listOfSongs = songService.recomended(id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return listOfSongs;

	}

}
