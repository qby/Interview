package com.qibenyu.algorithm;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class HashMap<K, V> implements IAlgorithm {

    private static final class MapEntry<K, V> {

        private K key;
        private V value;
        private int hash;
        private MapEntry<K, V> next;

    }

    private MapEntry<K, V>[] table = null;
    private int size = 0;

    private int capacity;
    private int threshold = 8;
    private float loadFactor = 0.75f;

    public HashMap() {
        this.capacity = 8;

        table = new MapEntry[capacity];

    }

    public HashMap(int capacity) {
        this.capacity = capacity;

        table = new MapEntry[capacity];
    }

    public void put(K key, V value) {

        int hash = hash(key);

        int index = getIndex(hash, table.length);

        MapEntry e = table[index];

        while (e != null) {
            Object o;
            if (e.hash == hash && ((o = e.key) == key || o.equals(key))) {
                e.value = value;
                return;
            }

            e = e.next;
        }

        addEntry(key, value, hash, index);

        size++;

    }

    private void resize(int newCapcity) {
        MapEntry<K, V>[] newTable = new MapEntry[newCapcity];

        transform(newTable);

        table = newTable;
        threshold = (int) (newCapcity * loadFactor);


    }

    private void transform(MapEntry<K, V>[] newTable) {

        for (MapEntry<K, V> entry : table) {

            while (entry != null) {
                MapEntry<K, V> next = entry.next;
                int index = getIndex(entry.hash, newTable.length);

                entry.next = newTable[index];

                newTable[index] = entry;

                entry = next;
            }
        }
    }

    private void addEntry(K key, V value, int hash, int index) {

        if (size > capacity && table[index] != null) {
            resize(table.length << 1);

            index = getIndex(hash, table.length);
        }

        createEntry(key, value, hash, index);

    }

    private void createEntry(K key, V value, int hash, int index) {
        MapEntry<K, V> entry = new MapEntry<>();
        entry.key = key;
        entry.value = value;
        entry.hash = hash;

        MapEntry e = table[index];
        table[index] = entry;
        entry.next = e;

        size++;
    }

    private int getIndex(int hash, int length) {
        return hash & length - 1;
    }

    private int hash(K key) {
        return key.hashCode();
    }

    V get(K key) {

        int hash = hash(key);

        int index = getIndex(hash, table.length);

        MapEntry<K, V> e = table[index];

        while (e != null) {
            if (e.hash == hash && (e.key == key || e.key.equals(key))) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    @NotNull
    @Override
    public String problem() {
        return "手写HashMap";
    }

    @NotNull
    @Override
    public String condition() {
        return "";
    }

    @NotNull
    @Override
    public String answer() {

        HashMap<String, Student> map = new HashMap<>(8);

//        for (int i = 0; i < 4; i++) {
//
//            map.put("i" + i, new Student("BaronQi" + i, i));
//        }
//
        StringBuilder builder = new StringBuilder();
//
//        for (int i = 0; i < 4; i++) {
//
//            Student s = map.get("i" + i);
//            builder.append(s.name).append(" , ").append(s.age).append("\n");
//        }

        map.put("Baron", new Student("zhang", 10));
        map.put("Baron", new Student("li", 20));
        Student s = map.get("Baron");
        builder.append(s);
        return builder.toString();
    }

    private static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @NonNull
        @Override
        public String toString() {
            return "name = " + this.name + " , age = " + age;
        }
    }

    @NotNull
    @Override
    public String thought() {
        return null;
    }

}
