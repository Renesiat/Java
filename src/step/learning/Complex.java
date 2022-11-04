package step.learning;

import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import java.util.*;

//комплексні типи данних - массиви та колекції
@DemoClass(priority = 2)
public class Complex {
    private final Random random;

    public Complex() {
        random = new Random();
    }

    private void arrayDemo() {
        int[] arr1 = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.println(arr1[i] + " ");
        }
        System.out.println();
        int[] arr2 = new int[]{5, 4, 3, 2, 1};
        for (int i = 0; i < arr2.length; i++) {
            System.out.printf("arr2[%d]\n", i, arr2[i]);
        }
        System.out.println("------------------------------------");

        int[] arr3 = {4, 5, 6, 7, 8};
        for (int x : arr3) { //foreach
            System.out.print(x + " ");
        }
        System.out.println("\n------------------------------------");

        int[][] arr4 = new int[3][4];
        randArray(arr4);
        printArray(arr4);
        int[][] arr5 = {
                {1, 2, 3},
                {4, 5, 6, 7},
                {8, 9}
        };
        printArray(arr5);
    }

    private void printArray(int[][] arr) {
        for (int[] a : arr) {
            for (int x : a) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

    private void randArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(42);
            }
        }
    }

    private void collectionDemo() {
        List<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(40);
        arr.add(50);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println("\n------------------------------------");
        arr.set(3, 42);
        arr.remove(1);
        for (int x : arr
        ) {
            System.out.print(x + " ");
        }
        System.out.println("\n------------------------------------");
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("Hello", "Привіт");
        dictionary.put("Bye", "Бувай");
        dictionary.put("Hi", "Здоровеньки були");
        dictionary.put("Good", "Добре");
        for (String key:dictionary.keySet()
             ) {
            System.out.printf("%s-%s%n", key,dictionary.get(key));
        }

        Scanner kbScanner = new Scanner( System.in);
        String userInput = kbScanner.nextLine();
        System.out.printf("%s-%s%n", userInput, dictionary.get(userInput));
    }
@EntryPoint
    public void run() {
        arrayDemo();
        collectionDemo();
    }
}
