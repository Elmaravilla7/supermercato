package com.sdistribuiti.supermercato.controller;

import com.sdistribuiti.supermercato.mapper.*;
import com.sdistribuiti.supermercato.utility.exception.*;
import com.sdistribuiti.supermercato.service.*;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
@RequestMapping("api/articoli")
@CrossOrigin(origins="http://localhost:5051")
public class ArticoliController {

    @Autowired
    private ArticoliService articoliServ;

    @Autowired
    private ResourceBundleMessageSource errMessage;

    private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

    @GetMapping(value = "/ricerca/fromcodice/{codart}", produces = "application/json")
    public ResponseEntity<ArticoliDTO> searchByCod(@PathVariable("codart") String codArt)
            throws NotFoundException
    {
        return new ResponseEntity<ArticoliDTO>(articoliServ.catchFromCodArtDTO(codArt), HttpStatus.OK);
    }

    @GetMapping(value = "/ricerca/frombarcode/{barcode}", produces = "application/json")
    public ResponseEntity<ArticoliDTO> searchByBarcode(@PathVariable("barcode") String barCode)
            throws NotFoundException
    {
        return new ResponseEntity<ArticoliDTO>(articoliServ.catchFromBarcodeDTO(barCode), HttpStatus.OK);

    }

    @GetMapping(value = "/ricerca/fromdescrizione/{descrizione}", produces = "application/json")
    public ResponseEntity<List<ArticoliDTO>> searchByDesc(@PathVariable("descrizione") String descr)
            throws Exception
    {

        return new ResponseEntity<List<ArticoliDTO>>(articoliServ.catchFromDescrizioneDTO("%" + descr.toUpperCase() + "%"), HttpStatus.OK);
    }





}