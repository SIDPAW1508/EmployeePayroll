package com.seveneleven.employeepayroll.model;

public class DownloadToken {

    private long createdTime;

    private long expiryMillis;

    public DownloadToken() {

        createdTime = System.currentTimeMillis();

        expiryMillis = 60 * 1000;

    }

    public boolean isExpired() {

        long now = System.currentTimeMillis();

        return (now - createdTime) > expiryMillis;

    }

}
 