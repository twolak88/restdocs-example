/**
 * 
 */
package com.twolak.springframework.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.twolak.springframework.domain.Beer;

/**
 * @author twolak
 *
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
