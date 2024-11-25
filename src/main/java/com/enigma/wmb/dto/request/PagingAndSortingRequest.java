package com.enigma.wmb.dto.request;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PagingAndSortingRequest {
    private Integer page;
    private Integer size;
    private String sortBy;

    public Integer getPage(){
        return page <= 0 ? 0 : page - 1;
    }
}
