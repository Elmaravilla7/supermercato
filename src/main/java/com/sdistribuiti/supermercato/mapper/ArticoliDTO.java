package com.sdistribuiti.supermercato.mapper;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sdistribuiti.supermercato.entity.Barcode;
import com.sdistribuiti.supermercato.entity.FamAssort;
import com.sdistribuiti.supermercato.entity.Ingredienti;
import com.sdistribuiti.supermercato.entity.Iva;

import lombok.Data;

@Data
public class ArticoliDTO
{
	private String codArt;

	private String descrizione;

	private String um;

	private String codStat;

	private Integer pzCart;

	private double pesoNetto;

	private String idStatoArt;

	private Date dataCreaz;

	private double prezzo = 0;
	
	private Set<Barcode> barcode = new HashSet<>();

	private Ingredienti ingredienti;

	private FamAssort famAssort;

	private Iva iva;
}
