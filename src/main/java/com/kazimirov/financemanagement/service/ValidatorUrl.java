package com.kazimirov.financemanagement.service;

import java.net.URL;

public class ValidatorUrl {

    public static String validatorURI(String linkToProfile) {

        if (linkToProfile == null || linkToProfile.trim().isEmpty()) {
            return linkToProfile = null;
        }
        try {
            new URL(linkToProfile).toURI();
            return linkToProfile;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid URL: " + linkToProfile);
        }
    }
}
