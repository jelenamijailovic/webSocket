package com.telnet.jukebox.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
