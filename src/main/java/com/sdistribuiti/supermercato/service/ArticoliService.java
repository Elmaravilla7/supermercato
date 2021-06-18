package com.sdistribuiti.supermercato.service;

import java.util.List;

import com.sdistribuiti.supermercato.utility.exception.NotFoundException;
import org.springframework.data.domain.Pageable;

import com.sdistribuiti.supermercato.mapper.ArticoliDTO;
import com.sdistribuiti.supermercato.entity.Articoli;

public interface ArticoliService 
{
	public Iterable<Articoli> catchAll();

	public void inserisci(Articoli articolo) throws IllegalArgumentException;

	public void elimina(String codArt) throws NotFoundException;

	public Articoli catchFromCodArt(String codArt);

	public ArticoliDTO catchFromCodArtDTO(String codArt) throws NotFoundException;

	public List<Articoli> catchFromDescrizione(String descrizione, Pageable pageable);

	public List<ArticoliDTO> catchFromDescrizioneDTO(String descrizione) throws Exception;


	public Articoli catchFromBarcode(String barcode);

	public ArticoliDTO catchFromBarcodeDTO(String barcode) throws NotFoundException;
	

	


}
