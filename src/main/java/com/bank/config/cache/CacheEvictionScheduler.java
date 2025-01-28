package com.bank.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheEvictionScheduler {

    // This will clear the "customers" cache every 15 seconds (only for testing)
    @Scheduled(fixedRate = 15000) // hours in milliseconds
    @CacheEvict(value = "customers", allEntries = true)
    public void evictCustomersCache() {
        log.info("Cache 'customers' has been cleared.");
    }
}
