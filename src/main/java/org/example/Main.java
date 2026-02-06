package org.example;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var groups = new Groups.Builder(-100L, 99L)
                .addGroup("A", 0.0, 0.1)
                .addGroup("B", 0.1, 0.4)
                .addGroup("C", 0.4, 0.65)
                .addGroup("D", 0.65, 1.0)
                .build();
        logger.info("Groups: {}", groups);

        Arrays.asList(-101L, -100L, -89L, -80L, -35L, 10L, 90L, 99L).forEach
                (e -> logger.info("Element {} to group {}", e, groups.toGroup(e))
        );

        var groups2 = new Groups.Builder(0L, 99L)
                .addGroup("A", 0.0, 0.1)
                .addGroup("B", 0.1, 0.4)
                .addGroup("C", 0.4, 0.65)
                .addGroup("D", 0.65, 1.0)
                .build();
        logger.info("Groups2: {}", groups2);

        var groups3 = new Groups.Builder(0L, 100L)
                .addGroup("A", 0.0, 0.1)
                .addGroup("B", 0.1, 0.4)
                .addGroup("C", 0.4, 0.65)
                .addGroup("D", 0.65, 1.0)
                .build();
        logger.info("Groups3: {}", groups3);

        var groups4 = new Groups.Builder(Long.MIN_VALUE, Long.MAX_VALUE)
                .addGroup("A", 0.0, 0.1)
                .addGroup("B", 0.1, 0.4)
                .addGroup("C", 0.4, 0.65)
                .addGroup("D", 0.65, 1.0)
                .build();
        logger.info("Groups4: {}", groups4);

    }
}