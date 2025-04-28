import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//
//        List<Integer> arr = new ArrayList<>();
//        arr.add(6);
//        arr.add(12);
//        arr.add(2);
//        arr.add(8);
//        arr.add(3);
//        arr.add(7);
//        arr.add(7);
//        int asd = PatienceSort.getIterationsCount();
//        arr = PatienceSort.patienceSorting(arr);
//
//        for (Integer result : arr) {
//            System.out.print(result + " ");
//        }

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
                        PatienceSortTester.processTest(testNumber, currentTest);
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
                PatienceSortTester.processTest(testNumber, currentTest);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Файл с тестами не найден: " + testFile);
        }
    }
}