import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class JavaTest {
    public static void main(String[] args) {
        try {
            printBonusDatesBetween(2010, 2015);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    static void printBonusDatesBetween(int fromYear, int toYear) throws Exception {
        // Generate a set of valid years (from incl, to excl)
        
        if (fromYear < 0 || toYear > 9999) {
            throw new Exception("Invalid years provided. Valid values are from 0 to 9999");
        }
        
        // Use set for uniqueness
        Set<Integer> years = getNumbersBetween(fromYear, toYear - 1);

        // User Array List for sorting
        ArrayList<String> validYears = new ArrayList<String>();

        /**
         * The only possibility for a date to be equal to its reversed value is to have
         * the month and day to equal the exact reverse of the year.
         * 
         * First we create all date (both correct and incorrect) by adding reverse year to itself
         * Then we check if we have valid dates and return them
         */

        for (Integer year : years) {
            String yearStr = String.valueOf(year);
            String reverseYearUnPadded = reverseIntToStr(year);
            
            // Make sure to pad zeroes if string length is not 4
            String reverseYear = String.format("%1$" + 4 + "s", reverseYearUnPadded).replace(' ', '0');

            String month = reverseYear.substring(0, 2);
            String day = reverseYear.substring(2, 4);
            
            String date = String.format("%s-%s-%s", yearStr, month, day);
            if (checkIfDateValid(date)) {
                validYears.add(date);
            }
        }

        Collections.sort(validYears, new SortByStringDate());
        
        for (String validYear : validYears) {
            System.out.println(validYear);
        }
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

class SortByStringDate implements Comparator<String> 
{
    public int compare(String a, String b) 
    {
        return Integer.parseInt(replaceDashes(a)) > Integer.parseInt(replaceDashes(b)) ? 1 : -1;
    }

    private String replaceDashes(String str) {
        return str.replaceAll("-", "");
    }
}
