package com.expedia.project.controller;


import com.expedia.project.model.User;
import com.expedia.project.service.SearchService;
import com.expedia.project.service.UserService;
import com.expedia.project.valueholder.FlightsDto;
import com.expedia.project.valueholder.FlightsResultsHolder;
import com.expedia.project.model.BookingDetails;
import com.expedia.project.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class SearchController {
    @Autowired
    private UserService userService;
    private SearchService searchService;


    @Autowired
    BookingRepository bookingRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @RequestMapping("/search")
    public String searchFlight(@RequestParam(defaultValue = "nil") String departure,
                               @RequestParam(defaultValue = "nil") String arrival,
                               @RequestParam(defaultValue = "nil") String departureDate,
                               Model model) {

        List<FlightsResultsHolder> searchFlightResult;
        if (!departure.equals("nil") && (!arrival.equals("nil") && (!departureDate.equals("nil")))) {
            searchFlightResult = searchService.flightSearch(departure, arrival, departureDate);
            if (searchFlightResult.isEmpty()) {
                model.addAttribute("notFound", "No Flights Available!");
            } else {
                model.addAttribute("searchFlightResults", searchFlightResult);
            }
        } else {
            model.addAttribute("searchFlightResults", null);
        }
        return "flightSearchResults";
    }


    @RequestMapping("/bookFlights")
    public ModelAndView bookFlights(@RequestParam String airline,
                                    @RequestParam String origin,
                                    @RequestParam String destination,
                                    @RequestParam String departDate,
                                    @RequestParam String departTime,
                                    @RequestParam String arrDate,
                                    @RequestParam String arrTime,
                                    @RequestParam String tripLength,
                                    @RequestParam String type,
                                    @RequestParam Double amount
    ) {
        ModelAndView model = new ModelAndView();
        LOGGER.info("From:" + origin + " TO:" + destination + "Departure Date: " + departDate);
        FlightsDto bookFlight = searchService.storeFlight(airline, origin,
                destination, departDate,
                departTime, arrDate,
                arrTime, tripLength,
                type, amount);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("username", user.getName());

        model.addObject("flightBook", bookFlight);

        model.addObject("depcode", bookFlight.getDepartureAirportCode());
        model.addObject("arrcode", bookFlight.getArrivalAirportCode());
        model.addObject("price", bookFlight.getPrice());
        model.addObject("duration", bookFlight.getTripDuration());
        model.addObject("depdate", bookFlight.getDepartureDate());
        model.addObject("deptime", bookFlight.getDepartureTime());
        model.addObject("arrtime", bookFlight.getArrivalTime());
        model.addObject("class", bookFlight.getCabinClass());
        model.addObject("airline", bookFlight.getAirlines());
        model.addObject("arrdate", bookFlight.getArrivalDate());


        model.addObject("details", new BookingDetails());

        model.setViewName("bookedpage");
        return model;
    }


    @PostMapping("/successpage")
    public ModelAndView processBook(BookingDetails details) {
        ModelAndView modelAndView = new ModelAndView();
        bookingRepository.save(details);
        modelAndView.setViewName("successpage");
        return modelAndView;
    }

    @GetMapping(value = "/myBookings")
    public ModelAndView viewMyBookings() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("username", user.getName());
        modelAndView.addObject("userdetails", bookingRepository.getBookingDetailsByUserName(user.getName()));
        modelAndView.setViewName("myBookings");
        return modelAndView;
    }

}
