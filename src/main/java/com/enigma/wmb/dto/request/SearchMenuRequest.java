package com.enigma.wmb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SearchMenuRequest extends PagingAndSortingRequest {
    private String query;
    private Long minPrice;
    private Long maxPrice;
}
