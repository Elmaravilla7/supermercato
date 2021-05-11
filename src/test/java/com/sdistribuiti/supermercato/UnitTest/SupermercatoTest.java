package com.sdistribuiti.supermercato.UnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.sdistribuiti.supermercato.entity.Articoli;
import com.sdistribuiti.supermercato.repository.ArticoliRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import com.sdistribuiti.supermercato.Supermercato;

@SpringBootTest()
@ContextConfiguration(classes = Supermercato.class)
public class SupermercatoTest
{
	
	@Autowired
	private ArticoliRepository articoliRepo;

	@Test
	public void TestFindByCodArt() throws Exception
	{
		Assertions.assertThat(articoliRepo.findByCodArt("80066987"))
				.extracting(Articoli::getDescrizione)
				.isEqualTo("Pane Fresco F.lli Rossi");
	}

	@Test
	public void TestCatchByDescrizioneLikeNoPag()
	{
		List<Articoli> items = articoliRepo.catchByDescrizioneLike("GHIACCIO%");
		assertEquals(1, items.size());
	}
	
	@Test
	public void TestCatchByDescrizioneLikePag()
	{
		List<Articoli> items = articoliRepo.findByDescrizioneLike("GHIACCIO%",PageRequest.of(0, 1));
		assertEquals(1, items.size());
	}


	@Test
	public void TestCatchByBarcode() throws Exception
	{
		Assertions.assertThat(articoliRepo.catchByBarcode("8008490000021"))
				.extracting(Articoli::getDescrizione)
				.isEqualTo("ACQUA ULIVETO 15 LT");
				
	}
	

}
