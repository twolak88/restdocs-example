/**
 * 
 */
package com.twolak.springframework.web.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twolak.springframework.repositories.BeerRepository;
import com.twolak.springframework.web.mappers.BeerMapper;
import com.twolak.springframework.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

/**
 * @author twolak
 *
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;
    
    @GetMapping
    public ResponseEntity<List<BeerDto>> getBeers() {
    	return new ResponseEntity<>(StreamSupport.stream(beerRepository.findAll().spliterator(), false)
    			.map(beerMapper::BeerToBeerDto).collect(Collectors.toList()),HttpStatus.OK);
    }
    
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerMapper.BeerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> saveNewBeer(@RequestBody @Validated BeerDto beerDto){

        beerRepository.save(beerMapper.BeerDtoToBeer(beerDto));

        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
    
    @PutMapping("/{beerId}")
    public ResponseEntity<?> updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
