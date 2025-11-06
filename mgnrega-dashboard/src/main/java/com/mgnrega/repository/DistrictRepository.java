package com.mgnrega.repository;

import com.mgnrega.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    District findByNameIgnoreCase(String name);
}
