import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class JavaTest {
    public static void main(String[] args) {
        printBonusDatesBetween(1990, 2020);
    }

    static void printBonusDatesBetween(int fromYear, int toYear) {
        // Generate a set of valid years (from incl, to excl)
        Set<Integer> years = getNumbersBetween(fromYear, toYear - 1);
        Set<String> validYears = new HashSet<String>();
        /**
         * The only possibility for a date to be equal to its reversed value is to have
         * the month and day to equal the exact reverse of the year.
         * 
         * First we create all date (both correct and incorrect) by adding reverse year to itself
         * Then we check if we have valid dates and return them
         */

        for (Integer year : years) {
            String yearStr = String.valueOf(year);
            String reverseYear = reverseIntToStr(year);
            String month = reverseYear.substring(0, 2);
            String day = reverseYear.substring(2, 4);
            
            String date = String.format("%s-%s-%s", yearStr, month, day);
            if (checkIfDateValid(date)) {
                validYears.add(date);
            }
        }

        System.out.println(validYears);
    }

    static Set<Integer> getNumbersBetween(int from, int to) {
        Set<Integer> numberSet = new HashSet<Integer>();
        
        for (int i = from; i <= to ; i++) {
            numberSet.add(i);
        }

        return numberSet;
    }

    static String reverseIntToStr(int integer) {
        String reversedVal = "";
        int workVal = integer;

        while (workVal != 0) {
            int lastDigit = workVal % 10;
            reversedVal =  reversedVal + String.valueOf(lastDigit);
            workVal = workVal / 10;
        }

        return reversedVal;
    }

    static boolean checkIfDateValid(String date) {
        DateFormat dateObj = new SimpleDateFormat("yyyy-MM-dd");
        dateObj.setLenient(false);
        try {
            dateObj.parse(date);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
