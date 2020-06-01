package recognition;

import java.io.*;

public class Recognition {
    public   int[][] models ;
    public  static int[] bias = { -1 , 6,0,0,2,0,-1,3,-2,-1 };
    private int[] digits;
    private int[] output;
    private Weight weight;
    private String nameFile = "digits.weight";

    public Recognition( ) {
        models = Models.models;
        loadWeight(nameFile);
    }



    private void setDefaultWeight() {
        weight = new Weight(Models.models , 10 , 15);
    }

    private void loadWeight(String nameFile) {
        try(ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(
                                new File(nameFile)))))
        {
            weight = (Weight) in.readObject();
        } catch (FileNotFoundException e) {
             setDefaultWeight();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setDigits(int[] digits) {
        this.digits = digits;

    }

    public void compute(){
        output = new int[10];
        for(int i = 0 ; i < models.length ; i++ ){
            int sum = bias[i];
            int[] model = models[i];
            for (int j = 0; j < 15 ; j++ ){
                int weight = model[j] == 0 ? -1 : 1;
                sum += digits[j] *weight;
            }
            output[i] = sum;
        }
    }

    public int getDigit(){
        int m = 0;
        for(int i = 0 ; i  < models.length ; i++ ){
            m = output[m] < output[i] ? i : m ;

        }
        return m;
    }

}
