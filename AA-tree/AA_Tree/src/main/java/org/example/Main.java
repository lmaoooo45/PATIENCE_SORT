package org.example;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
public class Main {
    public static void main(String[] args) {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Random random = new Random();
        int[] array = new int[10000];
        ArrayList<AATreeOperations.OperationStats> insertStats= new ArrayList<>();
        ArrayList<AATreeOperations.OperationStats> findStats = new ArrayList<>();
        ArrayList<AATreeOperations.OperationStats> deleteStats = new ArrayList<>();

        AATreeOperations.AATree tree = new AATreeOperations.AATree();


        // ген массива из 10000 эл
        for (int i = 0; i < 10000; i++ ){
            array[i] = random.nextInt(10000);
        }

        // vstavka elementov
        for (int value : array) {
            long startTime = System.nanoTime();
            tree.insert(value);
            long endTime = System.nanoTime();
            insertStats.add(new AATreeOperations.OperationStats(endTime - startTime, tree.operationCount));
        }

        // поиск 100 случ эл с замерами
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < 10000; i++) indices.add(i);
        Collections.shuffle(indices,random);
        for (int i = 0; i < 100; i++) {
            int value = array[indices.get(i)];
            long startTime = System.nanoTime();
            tree.find(value);
            long endTime = System.nanoTime();
            findStats.add(new AATreeOperations.OperationStats(endTime - startTime, tree.operationCount));
        }

        // удаление 1000 эл

        Collections.shuffle(indices, random);
        for (int i = 0; i < 1000; i++) {
            int value = array[indices.get(i)];
            long startTime = System.nanoTime();
            tree.delete(value);
            long endTime = System.nanoTime();
            deleteStats.add(new AATreeOperations.OperationStats(endTime - startTime, tree.operationCount));
        }
        double avgInsertTime = insertStats.stream().mapToLong(s -> s.time).average().orElse(0);
        double avgInsertOps = insertStats.stream().mapToInt(s -> s.operations).average().orElse(0);
        double avgFindTime = findStats.stream().mapToLong(s -> s.time).average().orElse(0);
        double avgFindOps = findStats.stream().mapToInt(s -> s.operations).average().orElse(0);
        double avgDeleteTime = deleteStats.stream().mapToLong(s -> s.time).average().orElse(0);
        double avgDeleteOps = deleteStats.stream().mapToInt(s -> s.operations).average().orElse(0);

        System.out.printf("Среднее время вставки: " + avgInsertTime + " нс, Среднее количество операций: %.2f%n", avgInsertTime, avgInsertOps);
        System.out.printf("Среднее время поиска: " + avgFindTime + " нс, Среднее количество операций: %.2f%n", avgFindTime, avgFindOps);
        System.out.printf("Среднее время удаления: " + avgDeleteTime + " нс, Среднее количество операций: %.2f%n", avgDeleteTime, avgDeleteOps);
    }
}