package com.example.travelvalda.models;




public class Review {

    public String reviewId;
    public String propertyId;
    public String guestId;
    public String rating;
    public String comment;

    public Review() {
    }

    public Review(String reviewId, String propertyId, String guestId, String rating, String comment) {
        this.reviewId = reviewId;
        this.propertyId = propertyId;
        this.guestId = guestId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
