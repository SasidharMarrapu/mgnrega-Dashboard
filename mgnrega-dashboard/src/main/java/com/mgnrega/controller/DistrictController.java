package com.mgnrega.controller;

import com.mgnrega.model.District;
import com.mgnrega.repository.DistrictRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/districts")
@CrossOrigin(origins = "*") // <-- VERY IMPORTANT
public class DistrictController {

    private final DistrictRepository repo;

    public DistrictController(DistrictRepository repo) {
        this.repo = repo;
    }

    @GetMapping	
    public List<District> getAllDistricts() {
        return repo.findAll();
    }
}
