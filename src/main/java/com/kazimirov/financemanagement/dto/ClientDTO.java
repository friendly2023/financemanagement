package com.kazimirov.financemanagement.dto;

public class ClientDTO {

    private Long id;
    private String name;
    private String linkToProfile;
    private String note;
    private int numberOfOrders;
    private int amountOrders;

    public ClientDTO(Long id,
                     String name,
                     String linkToProfile,
                     String note,
                     int numberOfOrders,
                     int amountOrders) {
        this.id = id;
        this.name = name;
        this.linkToProfile = linkToProfile;
        this.note = note;
        this.numberOfOrders = numberOfOrders;
        this.amountOrders = amountOrders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkToProfile() {
        return linkToProfile;
    }

    public void setLinkToProfile(String linkToProfile) {
        this.linkToProfile = linkToProfile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public int getAmountOrders() {
        return amountOrders;
    }
}
