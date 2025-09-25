package ca.cloudace.backend.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {
    private final String BLACKLIST_PREFIX = "jwt:blacklist:";
    private final RedisTemplate<String, String> redisTemplate;
    private final long jwtExpirationMs;

    public TokenBlackListService(RedisTemplate<String, String> redisTemplate,
            @Value("${jwt.expiration-ms}") long jwtExpirationMs) {
        this.redisTemplate = redisTemplate;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public void blacklist(String token) {
        long ttl = jwtExpirationMs / 1000; // seconds
        redisTemplate.opsForValue().set(BLACKLIST_PREFIX + token, "1", ttl,
                TimeUnit.SECONDS);
    }

    public boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(BLACKLIST_PREFIX + token));

    }
}