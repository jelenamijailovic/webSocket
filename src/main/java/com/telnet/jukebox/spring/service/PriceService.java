package com.telnet.jukebox.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.jukebox.spring.dto.PriceDTO;
import com.telnet.jukebox.spring.exceptions.BadEntryException;
import com.telnet.jukebox.spring.exceptions.EmptyListException;
import com.telnet.jukebox.spring.exceptions.PriceNotFoundException;
import com.telnet.jukebox.spring.model.Price;
import com.telnet.jukebox.spring.repository.PriceRepository;

@Service
public class PriceService {

	@Autowired
	PriceRepository priceRepository;

	public List<PriceDTO> getAllPrices() throws EmptyListException {
		List<PriceDTO> listOfPricesDTO = new ArrayList<PriceDTO>();

		List<Price> listOfPrices = new ArrayList<Price>();

		listOfPrices = priceRepository.findAll();

		if (listOfPrices.isEmpty()) {
			throw new EmptyListException();
		}

		for (int i = 0; i < listOfPrices.size(); i++) {
			listOfPricesDTO.add(entityToDTO(listOfPrices.get(i)));
		}

		return listOfPricesDTO;
	}

	public PriceDTO getPrice(Long priceId) throws PriceNotFoundException {
		Price price = new Price();

		price = priceRepository.findById(priceId).get();

		if (price.equals(null)) {
			throw new PriceNotFoundException(priceId);
		} else {
			return entityToDTO(price);
		}

	}

	public PriceDTO addPrice(PriceDTO price) throws BadEntryException {
		return entityToDTO(priceRepository.save(DTOToEntity(price)));
	}

	public PriceDTO updatePrice(PriceDTO price) throws BadEntryException {
		return entityToDTO(priceRepository.save(DTOToEntity(price)));
	}

	public void deletePrice(Long priceId) throws PriceNotFoundException {
		priceRepository.deleteById(priceId);
	}

	public Price DTOToEntity(PriceDTO price) {
		Price entity = new Price();
		entity.setId(price.getId());
		entity.setPrice(price.getPrice());
		return entity;
	}

	public PriceDTO entityToDTO(Price price) {
		PriceDTO dto = new PriceDTO();
		dto.setId(price.getId());
		dto.setPrice(price.getPrice());
		return dto;
	}
}
