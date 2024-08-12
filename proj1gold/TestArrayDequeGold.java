import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class TestArrayDequeGold {

    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();
        HashSet<Integer> map = new HashSet<>();

        Integer a = null;
        Integer b = null;
//        Integer counter = 0;
        List<String> callSeqs = new ArrayList<>();
        Integer selection;
        Boolean hasAddedItem;
        while (true) {
//            counter++;

            selection = StdRandom.uniform(1, 5);

            Integer number;
            do {
                number = StdRandom.uniform(0, Integer.MAX_VALUE - 1);
            } while (!map.add(number));

            hasAddedItem = false;
            switch (selection) {
                case 1:
                    studentDeque.addFirst(number);
                    solutionDeque.addFirst(number);
                    hasAddedItem = true;
                    callSeqs.add("addFirst(" + number + ")");
                    break;
                case 2:
                    studentDeque.addLast(number);
                    solutionDeque.addLast(number);
                    hasAddedItem = true;
                    callSeqs.add("addLast(" + number + ")");
                    break;
                case 3:
                    if (!studentDeque.isEmpty() && !solutionDeque.isEmpty()) {
                        a = studentDeque.removeFirst();
                        b = solutionDeque.removeFirst();
                        callSeqs.add("removeFirst()");
                    }
                    break;
                case 4:
                    if (!studentDeque.isEmpty() && !solutionDeque.isEmpty()) {
                        a = studentDeque.removeLast();
                        b = solutionDeque.removeLast();
                        callSeqs.add("removeLast()");
                    }
                    break;
            }

            if (!hasAddedItem) {
//                System.out.println(counter);
                assertEquals(String.join("\n", callSeqs),b, a);
            }
        }
    }
}
