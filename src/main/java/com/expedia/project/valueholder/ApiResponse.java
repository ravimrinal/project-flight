package com.expedia.project.valueholder;

import java.util.List;

public class ApiResponse {

    List<FlightsResultsHolder> flights;

    public List<FlightsResultsHolder> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightsResultsHolder> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "flights=" + flights +
                '}';
    }
}

