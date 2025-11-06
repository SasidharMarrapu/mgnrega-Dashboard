package com.mgnrega.repository;

import com.mgnrega.model.MonthlyPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<MonthlyPerformance, Long> {
    
    // Add this line 
    List<MonthlyPerformance> findByDistrictId(Long districtId);
}
