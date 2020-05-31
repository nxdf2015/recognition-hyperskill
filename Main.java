package recognition;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] weights = { 2,1,2,4,-4,4,2,-1,2};
        int bias = -5;

        Scanner scanner = new Scanner(System.in);
        int[] digits = new int[9];

        System.out.println("Input grid:");
        for(int i = 0 ; i < 3 ; i++ ){
            String line = scanner.nextLine();
            for(int j = 0 ; j < 3 ; j++ ){
                if(line.charAt(j) == '_'){
                    digits[i * 3 +j] = 0;
                }
                else {
                    digits[i * 3 +j] = 1;
                }
            }
        }

        int sum = bias;
        for (int i = 0 ; i < digits.length ; i++ ){
            sum += weights[i] * digits[i];
        }
        System.out.print("This number is ");
        if (sum >= 0){
            System.out.println("0");
        }
        else {
            System.out.println("1");
        }

    }
}
