package org.example;

import java.util.HashMap;
import java.util.Map;

public class Groups {
//public class Groups<T extends Number & Comparable<T>> {
//    private final T start;
//    private final T end;
//    private final Map<String, Group<T>> groups;
    private final Map<String, Group> groups;

//    private Groups(Builder<T> builder) {
    private Groups(Builder builder) {
//        this.start = builder.start;
//        this.end = builder.end;
        this.groups = builder.groups;
    }


    /**
     * @param hashedIdentifier A 64-bit hash (e.g., from MurmurHash3 or MD5)
     * @return The group name ("A", "B", "C", or "D"), or null if no group matches.
     */
//    public String toGroup(long hashedIdentifier) {
//    public String toGroup(T hashedIdentifier) {
    public String toGroup(Long hashedIdentifier) {
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
//                .filter(e -> e.getValue().isInGroup(hashedIdentifier.doubleValue()))
                .filter(e -> e.getValue().isInGroup(hashedIdentifier))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
        //        return null;
    }

//    public static class Builder<T extends Number & Comparable<T>> {
    public static class Builder {
//        private final T start;
        private final Long initStart;
//        private final T end;
        private final Long initEnd;
//        private final Map<String, Group<T>> groups = new HashMap<>();
        private final Map<String, Group> groups = new HashMap<>();
//        public Builder(T start, T end) throws RuntimeException {
        private long halfOfElementAmount;
        public Builder(Long initStart, Long initEnd) throws RuntimeException {
            if (initStart >= initEnd) {
                throw new RuntimeException("start must be less than end");
            }
            this.initStart = initStart;
            this.initEnd = initEnd;
            this.halfOfElementAmount = initEnd / 2 - initStart / 2;
        }

//        public Builder<T> addGroup(String groupName, Group<T> group) {
//        public Builder<T> addGroup(String groupName, Double startValue, Double endValue) {
        public Builder addGroup(String groupName, double startValue, double endValue) {
            /*
            */
//            T start = this.end - this.start;
            long groupStart = initStart;
            long groupEnd = initEnd;
//            if (startValue.compareTo(endValue) >= 0) {
//            if (initStart < 0 && initEnd >=0) {
//                long halfOfElementAmount = initEnd / 2 - initStart / 2;
                double groupRange = endValue - startValue;

//                groupStart = (startValue.compareTo(0.0) == 0) ? initStart : initStart + (long) (initStart * startValue);
                groupStart = startValue == 0.0 ?
                        initStart :
//                        initStart + (long) (halfOfElementAmount * startValue * 2);
                        (long) ((initStart / 2 + halfOfElementAmount * startValue) * 2);
            //                        initStart + (long) (initStart * startValue);
                groupEnd = endValue == 1.0 ?
                        initEnd :
                        (long) ((groupStart / 2 + halfOfElementAmount * groupRange) * 2);
//            }
            var group = new Group(groupStart, groupEnd);

            groups.put(groupName, group);
            return this;
        }

//        public Groups<T> build() {
        public Groups build() {
//            return new Groups<>(this);
            return new Groups(this);
        }
    }

}
