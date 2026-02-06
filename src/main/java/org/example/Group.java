package org.example;

public record Group(long start, long end) {
//public record Group<T extends Number & Comparable<T>>(T start, T end) {
/*
    public boolean intersects(Group<T> other) {
        return other != null && other.start != null && other.end != null;
    }
*/

//    public boolean isInGroup(T element) {
    public boolean isInGroup(long element) {
//        return element.compareTo(start) >= 0 && element.compareTo(end) < 0;
        return element >= start && element < end;
    }
}
