package com.enigma.wmb.service.impl;

import com.enigma.wmb.constant.MenuCategory;
import com.enigma.wmb.dto.request.MenuRequest;
import com.enigma.wmb.dto.response.MenuResponse;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.repository.MenuRepository;
import com.enigma.wmb.service.MenuService;
import com.enigma.wmb.util.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = Menu.builder()
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .category(MenuCategory.findByName(menuRequest.getCategory()))
                .build();

        menuRepository.save(menu);

        return toMenuResponse(menu);
    }

    @Override
    public MenuResponse getById(String id) {
        Menu menu = getOne(id);
        return toMenuResponse(menu);
    }

    @Override
    public Menu getOne(String id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
    }

    @Override
    public Page<MenuResponse> getAll(Integer page, Integer size, String sort) {
        if (page <= 0 ) page = 1;
        // pageable adalah interface yang menampung value halaman `(page)` dan data yg ditampilkan `(size)`
        // untuk membuat instance dari Pageable melalui implementasinya yaitu `PageRequest.of()`

        Sort sortBy = SortUtil.parseSort(sort);
        Pageable pageable = PageRequest.of((page - 1), size, sortBy);

        Page<Menu> menuPage = menuRepository.findAll(pageable);
        return menuPage.map(new Function<Menu, MenuResponse>() {
            @Override
            public MenuResponse apply(Menu menu) {
                return toMenuResponse(menu);
            }
        });
    }

    @Override
    public MenuResponse update(String id, MenuRequest menuRequest) {
        Menu menu = getOne(id);
        menu.setName(menuRequest.getName());
        menu.setPrice(menuRequest.getPrice());
        menu.setCategory(MenuCategory.findByName(menuRequest.getCategory()));
        menuRepository.save(menu);

        return toMenuResponse(menu);
    }

    @Override
    public void deleteById(String id) {
        Menu menu = getOne(id);
        menuRepository.delete(menu);
    }

    private MenuResponse toMenuResponse(Menu menu) {
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setId(menu.getId());
        menuResponse.setName(menu.getName());
        menuResponse.setPrice(menu.getPrice());
        menuResponse.setCategory(menu.getCategory().getName());

        return menuResponse;
    }
}
