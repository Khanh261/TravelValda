package com.example.travelvalda.models;




public class Booking {

    public int bookingId;
    public int propertyId;
    public int guestId;
    public String startDate;
    public String endDate;
    public String status;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
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

    public Booking(int bookingId, int propertyId, int guestId, String startDate, String endDate, String status) {
        this.bookingId = bookingId;
        this.propertyId = propertyId;
        this.guestId = guestId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}
