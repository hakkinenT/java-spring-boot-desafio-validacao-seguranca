package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO dto){
        dto = cityService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        List<CityDTO> cities = cityService.findAll();
        return ResponseEntity.ok(cities);
    }
}
