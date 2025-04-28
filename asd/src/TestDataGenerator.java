import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TestDataGenerator {
    private static final String TEST_FILE = "src//test/patience_sort_tests.txt";
    private static final Random random = new Random();


    private static final int NUM_TESTS = 50;
    private static final int MIN_SIZE = 100;
    private static final int MAX_SIZE = 10_000;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 100_000;

    public static void main(String[] args) {
        generateTestFile();
        System.out.println("Тестовые данные сохранены в: " + TEST_FILE);
    }

    private static void generateTestFile() {
        try (PrintWriter writer = new PrintWriter(TEST_FILE)) {

            for (int i = 1; i <= NUM_TESTS; i++) {
                int size = MIN_SIZE + random.nextInt(MAX_SIZE - MIN_SIZE + 1);
                List<Integer> testData = generateRandomList(size);

                writer.println("// Тест " + i + " (" + size + " элементов)");
                writer.println(convertListToString(testData));
                writer.println(); // Пустая строка между тестами
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    private static List<Integer> generateRandomList(int size) {
        return random.ints(size, MIN_VALUE, MAX_VALUE + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    private static String convertListToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(" ");

            }
        }
        return sb.toString();
    }
}