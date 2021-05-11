package com.sdistribuiti.supermercato.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sdistribuiti.supermercato.entity.Articoli;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ArticoliRepository  extends PagingAndSortingRepository<Articoli, String>
{

	Articoli findByCodArt(String codArt);

	List<Articoli> findByDescrizioneLike(String descrizione, Pageable pageable);

	@Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	List<Articoli> catchByDescrizioneLike(@Param("desArt") String descrizione);
	
	@Query(value="SELECT articolo FROM Articoli articolo JOIN articolo.barcode bar WHERE bar.barcode IN (:ean)")
	Articoli catchByBarcode(@Param("ean") String ean);
}
