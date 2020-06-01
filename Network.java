package recognition;

import java.util.Scanner;

public class Network {
    private Scanner scanner ;

    public Network() {
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("1. Learn the network");
        System.out.println("2. Guess a number");
        System.out.println("Your choice:");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                Learn learn = new Learn("digits.weight");
                learn.setIteration(500);
                learn.start();
                break;
            case 2:
                Recognition recognition = new Recognition();
                int[] digit = getDigit();
                recognition.setDigits(digit);
                recognition.compute();
                int result = recognition.getDigit();
                System.out.println("This number is " + result);
        }
    }

    private int[] getDigit() {
        int[] digit = new int[15];
        for(int i = 0 ; i < 5 ; i++ ){
            String line = scanner.nextLine();

            for(int j = 0 ; j < line.length() ; j++ ){
                digit[i * 3 + j ] = line.charAt(j) == '_' ? 0 : 1;
            }
        }
        return digit;
    }

    public static void main(String[] args) {
        Network network = new Network();
        network.start();
    }
}
