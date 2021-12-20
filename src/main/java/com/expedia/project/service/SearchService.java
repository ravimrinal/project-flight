package com.expedia.project.service;

import com.expedia.project.valueholder.FlightsDto;
import com.expedia.project.valueholder.ApiResponse;
import com.expedia.project.valueholder.FlightsResultsHolder;
import com.expedia.project.model.BookingDetails;
import com.expedia.project.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {

    private final RestTemplate restTemplate;
    private FlightsDto flightsDto;
    private BookingRepository bookingRepository;

    @Autowired
    public SearchService(RestTemplate restTemplate, FlightsDto flightsDto, BookingRepository bookingRepository) {

        this.restTemplate = restTemplate;
        this.flightsDto = flightsDto;
        this.bookingRepository = bookingRepository;

    }



    public List<FlightsResultsHolder> flightSearch(String departureAirport, String arrivalAirport, String departureDate) {


        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        header.set("Accept", "application/json");
        header.set("X-RapidAPI-Key","883d71e0bfmshe586ae0a4eb4048p12cf2bjsn15163c986c8c" );
        header.set("X-RapidAPI-Host", "google-flights-search.p.rapidapi.com");
        HttpEntity<String> entity = new HttpEntity<>(header);



        final String url = "https://google-flights-search.p.rapidapi.com/search";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("departure_airport_code", departureAirport)
                .queryParam("arrival_airport_code", arrivalAirport)
                .queryParam("departure_date", departureDate);

        ResponseEntity<ApiResponse> apiResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ApiResponse.class
        );

        List<FlightsResultsHolder> flightSearchResults;
        try {
            flightSearchResults = apiResponse.getBody().getFlights().stream().limit(5).collect(Collectors.toList());
            flightSearchResults.forEach(e -> System.out.println(
                      " Airline Name : " + e.getAirlines() +"\n"
                    + " Departure Airport : " + e.getDeparture_airport_code()+"\n"
                    + " Arrival Airport " + e.getArrival_airport_code()+"\n"
                    + " Departure Date :" + e.getDeparture_date()+"\n"
                    + " Departure Time :" + e.getDeparture_time()+"\n"
                    + " Arrival Date :" + e.getArrival_date()+"\n"
                    + " Arrival Time :" + e.getArrival_time()+"\n"
                    + " Trip Duration :" + e.getTrip_duration()+"\n"
                    + " Cabin Class :" + e.getCabin_class()+"\n"
                    + " Price :" + e.getPrice()));



        } catch (NullPointerException e) {
            return null;
        }

       return flightSearchResults;

    }


    public FlightsDto storeFlight(String airlines, String departure, String arrival, String departureDate, String departureTime,
                                String arrivalDate, String arrivalTime, String tripDuration, String cabinClass, Double price) {

        flightsDto.setAirlines(airlines);
        flightsDto.setDepartureAirportCode(departure);
        flightsDto.setArrivalAirportCode(arrival);
        flightsDto.setDepartureDate(departureDate);
        flightsDto.setDepartureTime(departureTime);
        flightsDto.setArrivalDate(arrivalDate);
        flightsDto.setArrivalTime(arrivalTime);
        flightsDto.setTripDuration(tripDuration);
        flightsDto.setCabinClass(cabinClass);
        flightsDto.setPrice(price);
        return flightsDto;
    }

    public BookingDetails saveDetails(BookingDetails bookingDetails){
        return bookingRepository.save(bookingDetails);
    }
}