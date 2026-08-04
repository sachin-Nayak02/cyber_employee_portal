package com.cyber_employee_portal.dto;

public class RatingDto {
    private Double rating;
    private String feedbackNote;

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public String getFeedbackNote() { return feedbackNote; }
    public void setFeedbackNote(String feedbackNote) { this.feedbackNote = feedbackNote; }
}
