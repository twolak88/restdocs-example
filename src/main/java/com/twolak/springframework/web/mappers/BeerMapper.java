/**
 * 
 */
package com.twolak.springframework.web.mappers;

import org.mapstruct.Mapper;

import com.twolak.springframework.domain.Beer;
import com.twolak.springframework.web.model.BeerDto;

/**
 * @author twolak
 *
 */
@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto BeerToBeerDto(Beer beer);

    Beer BeerDtoToBeer(BeerDto dto);
}
