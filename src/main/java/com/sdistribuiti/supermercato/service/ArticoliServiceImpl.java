package com.sdistribuiti.supermercato.service;

import com.sdistribuiti.supermercato.repository.ArticoliRepository;
import com.sdistribuiti.supermercato.utility.exception.NotFoundException;
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
	public void elimina(String codArt)throws NotFoundException
	{
		Articoli art= articoliRepo.findByCodArt(codArt);

		if(art==null){
			throw new NotFoundException("L'articolo non esite!");
		}

		articoliRepo.delete(art);
	}

	public Articoli catchFromCodArt(String codArt) {
		return articoliRepo.findByCodArt(codArt);
	}

	@Override
	public ArticoliDTO catchFromCodArtDTO(String codArt) throws NotFoundException {

		if(!articoliRepo.existsById(codArt)){
			throw new NotFoundException("L'articolo non è stato trovato!");
		}

		ArticoliDTO artDTO = mapper.map(articoliRepo.findByCodArt(codArt), ArticoliDTO.class);;
		artDTO.setDescrizione(artDTO.getDescrizione().trim());
		artDTO.setUm(artDTO.getUm().trim());
		artDTO.setIdStatoArt(artDTO.getIdStatoArt().trim());

		return artDTO;

	}

	@Override
	public List<ArticoliDTO> catchFromDescrizioneDTO(String descrizione) throws NotFoundException {

		try{
			List<Articoli> art = articoliRepo.catchByDescrizioneLike(descrizione);
			art.forEach(e -> e.setIdStatoArt(e.getIdStatoArt().trim()));
			art.forEach(e -> e.setUm(e.getUm().trim()));
			art.forEach(e -> e.setDescrizione(e.getDescrizione().trim()));
			return art.stream().map(source -> mapper.map(source, ArticoliDTO.class)).collect(Collectors.toList());

		}catch (Exception e){
			throw new NotFoundException("Non è stato trovato alcun articolo avente descrizione");
		}

	}

	@Override
	public ArticoliDTO catchFromBarcodeDTO(String barcode) throws NotFoundException {


		Articoli art = articoliRepo.catchByBarcode(barcode);

		if (art == null) {
			throw new NotFoundException("L'articolo con codice non è stato trovato!");
		}

		ArticoliDTO artDTO =  mapper.map(art, ArticoliDTO.class);
		artDTO.setDescrizione(artDTO.getDescrizione().trim());
		artDTO.setUm(artDTO.getUm().trim());
		artDTO.setIdStatoArt(artDTO.getIdStatoArt().trim());

		return artDTO;

	}




	@Override
	public List<Articoli> catchFromDescrizione(String descrizione, Pageable pageable)
	{
		return articoliRepo.findByDescrizioneLike(descrizione, pageable);
	}

	@Override
	public Articoli catchFromBarcode(String barcode)
	{
		return articoliRepo.catchByBarcode(barcode);
	}

	

	
	
	



}
