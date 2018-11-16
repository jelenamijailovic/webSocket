package com.telnet.jukebox.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Traffic;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

	//public List<Traffic> findTrafficBySongId(Long songId);

	public List<Traffic> findTrafficByUserId(Long userId);

	public int countBySongId(Long songId);

}
