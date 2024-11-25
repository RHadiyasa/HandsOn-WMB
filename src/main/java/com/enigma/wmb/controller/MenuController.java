package com.enigma.wmb.controller;

import com.enigma.wmb.constant.Constant;
import com.enigma.wmb.dto.request.MenuRequest;
import com.enigma.wmb.dto.request.SearchMenuRequest;
import com.enigma.wmb.dto.response.MenuResponse;
import com.enigma.wmb.dto.response.PagingResponse;
import com.enigma.wmb.service.MenuService;
import com.enigma.wmb.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = Constant.MENU_API)
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<?> createMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse createdMenu = menuService.createMenu(menuRequest);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Menu Created", createdMenu);
    }

    @GetMapping
    public ResponseEntity<?> getAllMenus(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                         @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                         @RequestParam(name = "sort", required = false) String sortBy,
                                         @RequestParam(name = "q", required = false) String query,
                                         @RequestParam(name = "minPrice", required = false) Long minPrice,
                                         @RequestParam(name = "maxPrice", required = false) Long maxPrice) {
        /*List<MenuResponse> menus = menuService.getAll();*/
        SearchMenuRequest pageRequest = SearchMenuRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .query(query)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();

        Page<MenuResponse> menus = menuService.getAll(pageRequest);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Success Get All Menus", menus);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        MenuResponse menuResponse = menuService.getById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Success get menu", menuResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable String id, @RequestBody MenuRequest menuRequest) {
        MenuResponse updatedMenu = menuService.update(id, menuRequest);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Success update menu", updatedMenu);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable String id) {
        menuService.deleteById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Success delete menu", null);
    }
}
