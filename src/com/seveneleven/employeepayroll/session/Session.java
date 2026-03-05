package com.seveneleven.employeepayroll.session;

public class Session {

    private String username;

    private long loginTime;

    private static final long SESSION_TIMEOUT = 5 * 60 * 1000; // 5 minutes

    public Session(String username) {

        this.username = username;

        this.loginTime = System.currentTimeMillis();

    }

    public boolean isExpired() {

        long current = System.currentTimeMillis();

        return (current - loginTime) > SESSION_TIMEOUT;

    }

    @Override

    public String toString() {

        return "Session active for user: " + username;

    }

}
 