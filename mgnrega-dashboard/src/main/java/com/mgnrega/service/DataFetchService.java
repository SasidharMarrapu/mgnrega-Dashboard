package com.mgnrega.service;

import com.mgnrega.model.*;
import com.mgnrega.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;
import com.mgnrega.repository.DistrictRepository;


import java.time.LocalDate;

@Service
public class DataFetchService {

    private final PerformanceRepository performanceRepository;
    private final DistrictRepository districtRepository;

    public DataFetchService(PerformanceRepository performanceRepository, DistrictRepository districtRepository) {
        this.performanceRepository = performanceRepository;
        this.districtRepository = districtRepository;
    }

    public void fetchAndStoreData() {
        String apiKey = "579b464db66ec23bdd000001444e67795f2e41de72d712f268eab0af";  // replace with your data.gov.in key
        String url = "https://api.data.gov.in/resource/RESOURCE_ID?api-key=" + apiKey + "&format=json&limit=1000";

        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONObject json = new JSONObject(response);
            JSONArray records = json.getJSONArray("records");

            for (int i = 0; i < records.length(); i++) {
                JSONObject row = records.getJSONObject(i);

                String districtName = row.optString("district_name", "Unknown");

                // Find or create district
                District district = districtRepository.findByNameIgnoreCase(districtName);
                if (district == null) {
                    district = districtRepository.save(new District(null, districtName, "Uttar Pradesh"));
                }
                	
                // Create new performance entry
                MonthlyPerformance perf = new MonthlyPerformance();
                perf.setDistrict(district);
                perf.setYear(row.optInt("year"));
                perf.setMonth(row.optInt("month"));
                perf.setJobsCreated(row.optInt("jobs_created"));
                perf.setHouseholdsWorked(row.optInt("households_worked"));
                perf.setWagesPaid(row.optDouble("wages_paid"));
                perf.setPaymentDelayDays(row.optDouble("payment_delay_days"));

                performanceRepository.save(perf);
            }

            System.out.println(" Data successfully fetched and stored!");
        } catch (Exception e) {
            System.err.println(" Error fetching data: " + e.getMessage());
        }
    }
}
