package recognition;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] weights = { 2,1,2,4,-4,4,2,-1,2};
        int bias = -5;

        Scanner scanner = new Scanner(System.in);
        Recognition recognize = new Recognition();
        int[] digits = new int[15];
        System.out.println("Input grid:");
        for(int i = 0 ; i < 5 ; i++){
            String line = scanner.nextLine();
            for(int j = 0 ; j < 3 ; j++  ){
                int  value = 0;
                if (line.charAt(j) == 'X'){
                    value = 1;
                }
                digits[i * 3 + j ] = value;

            }
        }

        recognize.setDigits(digits);
        recognize.compute();
        int digit = recognize.getDigit();
        System.out.println("This number is " + digit);
    }
}
