import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PatienceSort {
        private static int iterationsCount = 0;

        public static List<Integer> mergePiles(List<Stack<Integer>> piles) {
            List<Integer> result = new ArrayList<>();

            while (!piles.isEmpty()) { // O(k)
                int minu = Integer.MAX_VALUE; // минимальный элемент из верхушек куч

                int index = -1;

                for (int i = 0; i < piles.size(); i++) { // O(n) проходим по всем кучам и ищем минимальный элемент
                    if (!piles.get(i).isEmpty() && minu > piles.get(i).peek()) { // O(k) переносим элемент в итоговый массив
                        minu = piles.get(i).peek();
                        index = i;
                    }
                }

                result.add(piles.get(index).pop());

                if (piles.get(index).isEmpty()) {
                    piles.remove(index); // O(n)
                }
            }
            return result;
        }



    public static List<Integer> patienceSorting(List<Integer> arr) {

        List<Stack<Integer>> piles = new ArrayList<>(); // создаем список из куч

        Stack<Integer> first = new Stack<>(); // создаем первую кучу
        first.push(arr.get(0));
        piles.add(first);
        iterationsCount++;

        for (int i = 1; i < arr.size(); i++) { // O(n)
            boolean flag = false; // попал ли элемент в какую-то кучу
            iterationsCount++;

            for (int j = 0; j < piles.size(); j++) { // O(k)
                iterationsCount++;
                if (arr.get(i) < piles.get(j).peek()) {
                    piles.get(j).push(arr.get(i));
                    flag = true;
                    break;
                }
            }

            if (flag == false) {
                Stack<Integer> temp = new Stack<>(); // создаем новый стек
                temp.push(arr.get(i));
                piles.add(temp);
                iterationsCount++;
            }
        }

        System.out.println("Всего итераций: " + iterationsCount);
        return mergePiles(piles); // O(n^2)
    }
    public static int getIterationsCount() {
        return iterationsCount;
    }
}
