package com.expedia.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookingdetails")
public class BookingDetails {
    @Id
    @GenericGenerator(name = "pnr", strategy = "com.expedia.project.configuration.PrefixGenerator")
    @GeneratedValue(generator = "pnr")
    @Column(name="pnr",length = 64)
    private String pnr;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "passportNumber")
    private String passportNumber;
    @Column(name = "travellerType")
    private String travellerType;
    @Column(name = "noOfPassengers")
    private int noOfPassengers;
    @Column(name = "mobileNumber")
    private long mobileNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "cardNumber")
    private long cardNumber;
    @Column(name = "nameOnCard")
    private String nameOnCard;
    @Column(name = "expiryDate")
    private String expiryDate;
    @Column(name = "userName")
    private String userName;
    @Column(name = "departure_airport_code")
    private String departure_airport_code;
    @Column(name = "arrival_airport_code")
    private String arrival_airport_code;
    @Column(name = "price")
    private Double price;
    @Column(name = "trip_duration")
    private String trip_duration;
    @Column(name = "departure_date")
    private String departure_date;
    @Column(name = "departure_time")
    private String departure_time;
    @Column(name = "arrival_time")
    private String arrival_time;
    @Column(name = "cabin_class")
    private String cabin_class;
    @Column(name = "airlines")
    private String airlines;
    @Column(name = "arrival_date")
    private String arrival_date;

    public String getDeparture_airport_code() {
        return departure_airport_code;
    }

    public void setDeparture_airport_code(String departure_airport_code) {
        this.departure_airport_code = departure_airport_code;
    }

    public String getArrival_airport_code() {
        return arrival_airport_code;
    }

    public void setArrival_airport_code(String arrival_airport_code) {
        this.arrival_airport_code = arrival_airport_code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTrip_duration() {
        return trip_duration;
    }

    public void setTrip_duration(String trip_duration) {
        this.trip_duration = trip_duration;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getCabin_class() {
        return cabin_class;
    }

    public void setCabin_class(String cabin_class) {
        this.cabin_class = cabin_class;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }


    public BookingDetails(String firstName, String lastName, int age, String passportNumber, String travellerType, int noOfPassengers, long mobileNumber, String email, long cardNumber, String nameOnCard, String expiryDate, String userName, String departure_airport_code, String arrival_airport_code, Double price, String trip_duration, String departure_date, String departure_time, String arrival_time, String cabin_class, String airlines, String arrival_date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passportNumber = passportNumber;
        this.travellerType = travellerType;
        this.noOfPassengers = noOfPassengers;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.userName = userName;
        this.departure_airport_code = departure_airport_code;
        this.arrival_airport_code = arrival_airport_code;
        this.price = price;
        this.trip_duration = trip_duration;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.cabin_class = cabin_class;
        this.airlines = airlines;
        this.arrival_date = arrival_date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(String travellerType) {
        this.travellerType = travellerType;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
