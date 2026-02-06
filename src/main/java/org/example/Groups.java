package org.example;

import java.util.HashMap;
import java.util.Map;

public class Groups {
    private final Map<String, Group> groups;

    private Groups(Builder builder) {
        this.groups = builder.groups;
    }


    /**
     * @param hashedIdentifier A 64-bit hash (e.g., from MurmurHash3 or MD5)
     * @return The group name ("A", "B", "C", or "D"), or null if no group matches.
     */
    public String toGroup(Long hashedIdentifier) {
        // Example for imperative group search has O(n) time complexity.
        // For now are used functional style search via Stream API.
/*
        for (Map.Entry<String, Group> stringGroupEntry : groups.entrySet()) {
            String key = stringGroupEntry.getKey();
            Group<Double> value = stringGroupEntry.getValue();
            if (value.isInGroup(hashedIdentifier.doubleValue())) {
                return key;
            }
        }
*/
        return groups.entrySet().stream()
                .filter(e -> e.getValue().isInGroup(hashedIdentifier))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static class Builder {
        private final Long initStart;
        private final Long initEnd;
        private final Map<String, Group> groups = new HashMap<>();
        private long halfOfElementAmount;

        public Builder(Long initStart, Long initEnd) throws RuntimeException {
            if (initStart >= initEnd) {
                throw new RuntimeException("start must be less than end");
            }
            this.initStart = initStart;
            this.initEnd = initEnd;
            this.halfOfElementAmount = initEnd / 2 - initStart / 2;
        }

        public Builder addGroup(String groupName, double startValue, double endValue) {
            // Group range looks abit strange and have some incorrect calculation.
            // This is because I am trying to avoid long type overflow issue.
            // Have to use  BigInteger instead and add some unit tests.
            long groupStart = initStart;
            long groupEnd = initEnd;
            double groupRange = endValue - startValue;

            groupStart = startValue == 0.0 ?
                    initStart :
                    (long) ((initStart / 2 + halfOfElementAmount * startValue) * 2);
            groupEnd = endValue == 1.0 ?
                    initEnd :
                    (long) ((groupStart / 2 + halfOfElementAmount * groupRange) * 2);
            var group = new Group(groupStart, groupEnd);

            groups.put(groupName, group);
            return this;
        }

        public Groups build() {
            return new Groups(this);
        }
    }

}
