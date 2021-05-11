package com.sdistribuiti.supermercato.service;

import com.sdistribuiti.supermercato.repository.ArticoliRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sdistribuiti.supermercato.mapper.ArticoliDTO;
import com.sdistribuiti.supermercato.entity.Articoli;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService
{
	@Autowired
    ArticoliRepository articoliRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Iterable<Articoli> catchAll()
	{
		return articoliRepo.findAll();
	}

	@Override
	@Transactional
	public void inserisci(Articoli articolo)
	{
		articoliRepo.save(articolo);
	}

	@Override
	@Transactional
	public void elimina(Articoli articolo)
	{
		articoliRepo.delete(articolo);
	}

	public Articoli catchFromCodArt(String codArt) {
		return articoliRepo.findByCodArt(codArt);
	}

	@Override
	public ArticoliDTO catchFromCodArtDTO(String codArt)
	{
		Articoli articoli = articoliRepo.findByCodArt(codArt);

		ArticoliDTO artDto = null;


		if (articoli != null)
		{
			artDto =  mapper.map(articoli, ArticoliDTO.class);

			artDto.setDescrizione(artDto.getDescrizione().trim());
			artDto.setUm(artDto.getUm().trim());
			artDto.setIdStatoArt(artDto.getIdStatoArt().trim());

		}

		return artDto;

		
	}

	@Override
	public List<Articoli> catchFromDescrizione(String descrizione, Pageable pageable)
	{
		return articoliRepo.findByDescrizioneLike(descrizione, pageable);
	}

	@Override
	public List<ArticoliDTO> catchFromDescrizione(String descrizione)
	{
		List<Articoli> art = articoliRepo.catchByDescrizioneLike(descrizione);
		
		art.forEach(e -> e.setIdStatoArt(e.getIdStatoArt().trim()));
		art.forEach(e -> e.setUm(e.getUm().trim()));
		art.forEach(e -> e.setDescrizione(e.getDescrizione().trim()));
		
		return art.stream().map(source -> mapper.map(source, ArticoliDTO.class)).collect(Collectors.toList());

	}

	@Override
	public Articoli SelByBarcode(String barcode)
	{
		return articoliRepo.catchByBarcode(barcode);
	}

	
	@Override
	public ArticoliDTO SelByBarcodeDTO(String barcode)
	{
		Articoli articoli = articoliRepo.catchByBarcode(barcode);

		ArticoliDTO artDto = null;

		if (articoli != null)
		{
			artDto =  mapper.map(articoli, ArticoliDTO.class);

			artDto.setDescrizione(artDto.getDescrizione().trim());
			artDto.setUm(artDto.getUm().trim());
			artDto.setIdStatoArt(artDto.getIdStatoArt().trim());

		}

		return artDto;
		

		
	}
	
	
	



}
