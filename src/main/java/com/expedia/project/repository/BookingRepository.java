package com.expedia.project.repository;

import com.expedia.project.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetails,Integer> {

    public List<BookingDetails> getBookingDetailsByUserName(String userName);


}
