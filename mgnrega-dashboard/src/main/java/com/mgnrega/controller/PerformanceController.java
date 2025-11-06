package com.mgnrega.controller;

import com.mgnrega.model.MonthlyPerformance;
import com.mgnrega.repository.PerformanceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/performance")
@CrossOrigin(origins = "*")
public class PerformanceController {

    private final PerformanceRepository performanceRepository;

    public PerformanceController(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    @GetMapping("/{districtId}")
    public List<MonthlyPerformance> getPerformance(@PathVariable Long districtId) {
        return performanceRepository.findByDistrictId(districtId);
    }

    //  Add this method
    @GetMapping("/all")
    public List<MonthlyPerformance> getAllPerformances() {
        return performanceRepository.findAll();
    }
}
