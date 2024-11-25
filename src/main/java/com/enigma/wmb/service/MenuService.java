package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.MenuRequest;
import com.enigma.wmb.dto.request.SearchMenuRequest;
import com.enigma.wmb.dto.response.MenuResponse;
import com.enigma.wmb.entity.Menu;
import org.springframework.data.domain.Page;


public interface MenuService {
    MenuResponse createMenu(MenuRequest menuRequest);
    MenuResponse getById(String id);
    Menu getOne(String id);
    /*Page<MenuResponse> getAll(Integer page, Integer size, String sort);*/
    Page<MenuResponse> getAll(SearchMenuRequest searchMenuRequest);
    MenuResponse update(String id, MenuRequest menuRequest);
    void deleteById(String id);
}
