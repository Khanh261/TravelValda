package com.example.travelvalda.models;




public class Booking {

    public String bookingId;
    public String propertyId;
    public String guestId;
    public String startDate;
    public String endDate;
    public String status;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Booking() {
    }

    public Booking(String bookingId, String propertyId, String guestId, String startDate, String endDate, String status) {
        this.bookingId = bookingId;
        this.propertyId = propertyId;
        this.guestId = guestId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}
