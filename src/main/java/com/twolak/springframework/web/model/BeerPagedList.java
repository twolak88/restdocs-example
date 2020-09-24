/**
 * 
 */
package com.twolak.springframework.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * @author twolak
 *
 */
public class BeerPagedList extends PageImpl<BeerDto>{
	
	private static final long serialVersionUID = -9098529818767699154L;

	public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDto> content) {
        super(content);
    }
}
