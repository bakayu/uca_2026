
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerComparators {
    Map<Integer, Boolean> isPrimeNumberCache = new HashMap<>();

    class UCAStudent implements Comparable<UCAStudent> {
        String studentName;
        double CGPA;

        public UCAStudent(String studentName, double CGPA) {
            this.studentName = studentName;
            this.CGPA = CGPA;
        }

        @Override
        public String toString() {
            return studentName + " " + CGPA;
        }

        @Override
        public int compareTo(UCAStudent that) {
            // low CGPA on top
            // if same then lex ordering of names
            if (this.CGPA != that.CGPA) {
                return Double.compare(this.CGPA, that.CGPA);
            }
            return this.studentName.compareTo(that.studentName);
        }
    }

    public void main() {
        List<Integer> list1 = new ArrayList<>(List.of(1, 10, -1, 99, 2, -100));
        List<Integer> list2 = new ArrayList<>(List.of(2, 3, 100, 900, 600, 23));

        // positives on top then negatives
        Collections.sort(list1, (e1, e2) -> {
            if (e1 < 0 && e2 > 0) {
                return 1;
            }
            if (e1 > 0 && e2 < 0) {
                return -1;
            }

            return Integer.compare(e1, e2);
        });

        // prime numbers on top, then smaller on top
        // if no prime numbers, bigger one on top;
        Collections.sort(list2, (e1, e2) -> {
            if (isPrime(e1) && !isPrime(e2)) {
                return -1;
            }
            if (!isPrime(e1) && isPrime(e2)) {
                return 1;
            }
            if (isPrime(e1) && isPrime(e2)) {
                return Integer.compare(e1, e2);
            }
            return Integer.compare(e2, e1);
        });

        System.out.println(list1);
        System.out.println(list2);

        List<UCAStudent> list3 = new ArrayList<>(List.of(
                new UCAStudent("A", 8.0d),
                new UCAStudent("B", 6.7d),
                new UCAStudent("D", 6.7d),
                new UCAStudent("E", 6.7d),
                new UCAStudent("C", 8.0d)));
        Collections.sort(list3);
        System.out.println(list3);
    }

    boolean isPrime(int num) {
        if (num <= 1)
            return false;
        if (num == 2 || num == 3)
            return true;
        if (num % 2 == 0 || num % 3 == 0)
            return false;

        if (isPrimeNumberCache.containsKey(num))
            return isPrimeNumberCache.get(num);

        for (int i = 5; i * i < num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                isPrimeNumberCache.put(num, false);
                return false;
            }
        }

        isPrimeNumberCache.put(num, true);
        return true;
    }
}
