package com.enigma.wmb.util;

import com.enigma.wmb.dto.response.CommonResponse;
import com.enigma.wmb.dto.response.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {
    public static <T> ResponseEntity<CommonResponse<T>> buildResponse(HttpStatus status, String message, T data){
        CommonResponse<T> response = new CommonResponse<>(status.value(), message, data, null);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<CommonResponse<?>> buildPagingResponse(HttpStatus status, String message, Page<T> page){
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPageable().getPageNumber() + 1)
                .size(page.getSize())
                .build();

        CommonResponse<List<T>> response = new CommonResponse<>(
                status.value(),
                message,
                page.getContent(),
                pagingResponse
        );

        return ResponseEntity.status(status).body(response);
    };
}
