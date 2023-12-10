import java.util.Arrays;

interface SortingStrategy {
    void sort(int[] array);
}

class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("Bubble Sort: " + Arrays.toString(array));
    }
}

class SelectionSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        System.out.println("Selection Sort: " + Arrays.toString(array));
    }
}

class SortingContext {
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void performSort(int[] array) {
        sortingStrategy.sort(array);
    }
}

public class SortingApp {
    public static void main(String[] args) {
        int[] arrayToSort = {5, 2, 8, 1, 7};

        SortingContext sortingContext = new SortingContext();

        sortingContext.setSortingStrategy(new BubbleSort());
        sortingContext.performSort(Arrays.copyOf(arrayToSort, arrayToSort.length));

        sortingContext.setSortingStrategy(new SelectionSort());
        sortingContext.performSort(Arrays.copyOf(arrayToSort, arrayToSort.length));
    }
}
