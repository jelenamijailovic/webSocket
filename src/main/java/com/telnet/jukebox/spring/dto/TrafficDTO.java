package com.telnet.jukebox.spring.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.telnet.jukebox.spring.model.Artist;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.model.Song;
import com.telnet.jukebox.spring.model.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "traffic")
public class TrafficDTO {

	@ApiModelProperty(dataType = "Long", example = "90", position = -1)
	private Long id;

	@ApiModelProperty(dataType = "Date", example = "2018-07-31", position = 0)
	private Date date;

	@ApiModelProperty(position = 2)
	private SongDTO song;

	@ApiModelProperty(position = 3)
	private User user;
	
	public TrafficDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SongDTO getSong() {
		return song;
	}

	public void setSong(SongDTO song) {
		this.song = song;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((song == null) ? 0 : song.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrafficDTO other = (TrafficDTO) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrafficDTO [id=" + id + ", date=" + date + ", song=" + song + ", user=" + user + "]";
	}

}
