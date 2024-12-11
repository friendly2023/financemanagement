package com.kazimirov.financemanagement.entity;

import java.net.URL;

public class UrlValidator {

    static public boolean validator(String linkToProfile) {
        try {
            new URL(linkToProfile).toURI();
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid URL: " + linkToProfile);
        }
    }
}
