package recognition;

public class Recognition {
    public static int[][] models ={
            {1,1,1, 1,0,1, 1,0,1, 1,0,1, 1,1,1 },
            {0,1,0, 0,1,0, 0,1,0, 0,1,0, 0,1,0 },
            {1,1,1, 0,0,1, 1,1,1, 1,0,0, 1,1,1 },
            {1,1,1, 0,0,1, 1,1,1, 0,0,1, 1,1,1 },
            {1,0,1, 1,0,1, 1,1,1, 0,0,1, 0,0,1 },
            {1,1,1, 1,0,0, 1,1,1, 0,0,1, 1,1,1 },
            {1,1,1, 1,0,0, 1,1,1, 1,0,1, 1,1,1 },
            {1,1,1, 0,0,1, 0,0,1, 0,0,1, 0,0,1 },
            {1,1,1, 1,0,1, 1,1,1, 1,0,1, 1,1,1 },
            {1,1,1, 1,0,1, 1,1,1, 0,0,1, 1,1,1 }
    };
    public  static int[] bias = { -1 , 6,0,0,2,0,-1,3,-2,-1 };
    private int[] digits;
    private int[] output;


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
