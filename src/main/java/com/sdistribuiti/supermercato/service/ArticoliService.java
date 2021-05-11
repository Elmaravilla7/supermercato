package com.sdistribuiti.supermercato.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sdistribuiti.supermercato.mapper.ArticoliDTO;
import com.sdistribuiti.supermercato.entity.Articoli;

public interface ArticoliService 
{
	public Iterable<Articoli> catchAll();

	public void inserisci(Articoli articolo);

	public void elimina(Articoli articolo);

	public Articoli catchFromCodArt(String codArt);

	public ArticoliDTO catchFromCodArtDTO(String codArt);

	public List<Articoli> catchFromDescrizione(String descrizione, Pageable pageable);

	public List<ArticoliDTO> catchFromDescrizione(String descrizione);

	public Articoli SelByBarcode(String barcode);

	public ArticoliDTO SelByBarcodeDTO(String barcode);
	

	


}
