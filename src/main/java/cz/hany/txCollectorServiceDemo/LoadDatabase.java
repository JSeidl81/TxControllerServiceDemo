package cz.hany.txCollectorServiceDemo;

import cz.hany.txCollectorServiceDemo.model.Transaction;
import cz.hany.txCollectorServiceDemo.repository.TransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TransactionsRepository repository) {

        return args -> {
            if (repository.getTransactionById(3L).isPresent()) {
                log.info("Database contains demo data.");
            } else {
                Map<String, String> map1 = new HashMap<>();
                map1.put("m1key1", "m1val1");
                map1.put("m1key2", "m1val2");
                map1.put("m1key3", "m1val3");
                Map<String, String> map2 = new HashMap<>();
                map2.put("m2key1", "m2val1");
                map2.put("m2key2", "m2val2");
                map2.put("m2key3", "m2val3");

                log.info("Preloading " + repository.save(new Transaction(3L, LocalDateTime.now(), "typ1", "actor1", map1)));
                log.info("Preloading " + repository.save(new Transaction(5L, LocalDateTime.now(), "typ2", "actor2", map2)));
            }
        };
    }
}