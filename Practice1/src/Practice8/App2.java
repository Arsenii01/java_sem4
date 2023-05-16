package Practice8;

import java.lang.reflect.Array;
import java.util.List;

interface SortStrategy {
    void sort(List<Integer> array);
}
class BubbleSort implements SortStrategy {
    public void sort(List<Integer> array) {
        System.out.println("Пузырьковая");
    }
}

class QuickSort implements SortStrategy {
    public void sort(List<Integer> array) {
        System.out.println("Быстрая");
    }
}
class Sorter {
    protected SortStrategy sorter;

    public Sorter(SortStrategy sorter1) {
        sorter = sorter1;
    }

    public void sort(List<Integer> array) {
        sorter.sort(array);
    }
}

public class App2 {


}
