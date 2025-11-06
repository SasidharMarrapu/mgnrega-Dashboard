const BASE_URL = "http://localhost:8082/api"; // Backend API base URL

// ==========================
// 1️⃣ Fetch and display all districts
// ==========================
async function fetchAllDistricts() {
    try {
        const response = await fetch(`${BASE_URL}/districts`);
        if (!response.ok) throw new Error("Failed to fetch districts");

        const districts = await response.json();
        const districtList = document.getElementById("districtList");
        districtList.innerHTML = ""; // clear old data

        districts.forEach(district => {
            const div = document.createElement("div");
            div.className = "district-item";
            div.innerHTML = `
                <h3>${district.name}</h3>
                <p><strong>State:</strong> ${district.state}</p>
                <button onclick="fetchPerformance(${district.id}, '${district.name}')">View Performance</button>
            `;
            districtList.appendChild(div);
        });

    } catch (error) {
        console.error("Error fetching districts:", error);
        alert("Failed to fetch districts");
    }
}

// ==========================
// 2️⃣ Fetch performance data for a district
// ==========================
async function fetchPerformance(districtId, districtName) {
    try {
        const response = await fetch(`${BASE_URL}/performance/${districtId}`);
        if (!response.ok) throw new Error("Failed to fetch performance data");

        const performanceData = await response.json();
        const performanceDiv = document.getElementById("performanceData");
        performanceDiv.innerHTML = `<h2>Performance Data for ${districtName}</h2>`;

        if (performanceData.length === 0) {
            performanceDiv.innerHTML += "<p>No data available for this district.</p>";
            return;
        }

        const table = document.createElement("table");
        table.innerHTML = `
            <tr>
                <th>Year</th>
                <th>Month</th>
                <th>Jobs Created</th>
                <th>Households Worked</th>
                <th>Wages Paid</th>
                <th>Payment Delay (days)</th>
            </tr>
        `;

        performanceData.forEach(item => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${item.year}</td>
                <td>${item.month}</td>
                <td>${item.jobsCreated}</td>
                <td>${item.householdsWorked}</td>
                <td>${item.wagesPaid}</td>
                <td>${item.paymentDelayDays}</td>
            `;
            table.appendChild(row);
        });

        performanceDiv.appendChild(table);
    } catch (error) {
        console.error("Error fetching performance:", error);
        alert("Failed to fetch performance data");
    }
}

// ==========================
// 3️⃣ Auto-detect user district via Geolocation
// ==========================
async function detectUserDistrict() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(async (position) => {
            const { latitude, longitude } = position.coords;
            console.log("Your Location:", latitude, longitude);

            try {
                // Reverse geocode using OpenStreetMap API
                const response = await fetch(
                    `https://nominatim.openstreetmap.org/reverse?format=json&lat=${latitude}&lon=${longitude}`
                );
                const data = await response.json();

                const detectedDistrict =
                    data.address.county ||
                    data.address.city ||
                    data.address.state_district ||
                    data.address.state;

                alert(`📍 Detected District: ${detectedDistrict}`);

                // OPTIONAL: automatically match and fetch district data if available
                const districtsResponse = await fetch(`${BASE_URL}/districts`);
                const districts = await districtsResponse.json();

                const matchedDistrict = districts.find(d =>
                    detectedDistrict.toLowerCase().includes(d.name.toLowerCase())
                );

                if (matchedDistrict) {
                    fetchPerformance(matchedDistrict.id, matchedDistrict.name);
                } else {
                    alert("District data not found in the database.");
                }

            } catch (error) {
                console.error("Error detecting district:", error);
                alert("Unable to detect your district.");
            }
        }, (error) => {
            console.error("Geolocation error:", error);
            alert("Location access denied or unavailable.");
        });
    } else {
        alert("Geolocation is not supported by your browser.");
    }
}
