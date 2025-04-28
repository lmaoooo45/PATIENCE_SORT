import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PatienceSortTester {
    public static void main(String[] args) {
        String testFile = "src//test/patience_sort_tests.txt";

        try (Scanner scanner = new Scanner(new File(testFile))) {
            int testNumber = 0;
            List<Integer> currentTest = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Пропускаем комментарии и пустые строки
                if (line.startsWith("//") || line.isEmpty()) {
                    // Если начинается новый тест, обрабатываем предыдущий
                    if (!currentTest.isEmpty()) {
                        testNumber++;
                        processTest(testNumber, currentTest);
                        currentTest.clear();
                    }
                    continue;
                }

                // Добавляем числа из строки в текущий тест
                String[] numbers = line.split("\\s+");
                for (String numStr : numbers) {
                    if (!numStr.isEmpty()) {
                        currentTest.add(Integer.parseInt(numStr));
                    }
                }
            }

            // Обработка последнего теста
            if (!currentTest.isEmpty()) {
                testNumber++;
                processTest(testNumber, currentTest);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Файл с тестами не найден: " + testFile);
        }
    }

    public static void processTest(int testNumber, List<Integer> testData) {
        System.out.println("\n--- Тест номер " + testNumber + " (размер: " + testData.size() + " элементов) ---");
        // первые 10 элементов для проверки
        System.out.println("Неотсортированные элементы: " + testData);

        // Замер времени выполнения
        long startTime = System.nanoTime();
        List<Integer> sorted = PatienceSort.patienceSorting(new ArrayList<>(testData));
        long duration = (System.nanoTime() - startTime); // в миллисекундах

        System.out.println("Результат:");

        System.out.println("- Время выполнения: " + duration + " мс");

        // Выводим первые 10 отсортированных элементов

//        System.out.println(testData.size() + "," + duration + "," + PatienceSort.getIterationsCount());
        System.out.println("Отсортированные элементы " + sorted);
    }
}