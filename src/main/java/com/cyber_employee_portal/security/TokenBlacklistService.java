package com.cyber_employee_portal.security;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklistService {

    // token -> expiry timestamp (so we know when it's safe to purge)
    private final Map<String, Long> blacklistedTokens = new ConcurrentHashMap<>();

    public void blacklistToken(String token, long expiryEpochMillis) {
        blacklistedTokens.put(token, expiryEpochMillis);
    }

    public boolean isBlacklisted(String token) {
        cleanupExpiredTokens();
        return blacklistedTokens.containsKey(token);
    }

    // Removes tokens that have already naturally expired,
    // since there's no point holding onto them anymore
    private void cleanupExpiredTokens() {
        long now = System.currentTimeMillis();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue() < now);
    }
}