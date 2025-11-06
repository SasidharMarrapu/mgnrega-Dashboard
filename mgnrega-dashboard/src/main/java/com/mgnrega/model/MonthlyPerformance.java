package com.mgnrega.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monthly_performance")
public class MonthlyPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;   //  Required for setDistrict()

    private int year;
    private int month;
    private int jobsCreated;
    private int householdsWorked;
    private double wagesPaid;
    private double paymentDelayDays;
  
}
