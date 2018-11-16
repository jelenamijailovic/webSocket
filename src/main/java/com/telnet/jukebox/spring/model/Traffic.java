package com.telnet.jukebox.spring.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

	@Entity
	@Table(name = "traffic")
	public class Traffic implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(nullable = false)
		private Date date;

		@ManyToOne
		@JoinColumn(name= "user_id", nullable= false, updatable=false)
		private User user;

		@ManyToOne
		@JoinColumn(name = "song_id", nullable = false, updatable=false)
		private Song song;

		public Traffic() {
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

		public Song getSong() {
			return song;
		}

		public void setSong(Song song) {
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
			return 31;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Traffic))
				return false;
			return id!=null && id.equals(((Traffic) obj).id);  
		}

		@Override
		public String toString() {
			return "Traffic [id=" + id + ", date=" + date + ", user=" + user + ", song=" + song + "]";
		}
	}