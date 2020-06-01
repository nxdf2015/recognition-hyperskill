package recognition;

import java.io.*;
import java.util.Random;
import java.util.function.Function;

public class Learn implements Serializable {


    public static Function<Double,Double> s = x -> 1 / ( 1 + Math.exp(-1 *  x));
    private final int[][] models;
    private final int number_pixels;
    private final double speed;
    private final String nameFile;


    Weight  weight;
    private int iteration = 100;


    public Learn(String nameFile){
        this.nameFile = nameFile;
        models = Models.models;
        number_pixels = Models.number_pixels;
        speed = 0.5;
        Random random = new Random(1);
        weight = new Weight(() -> random.nextGaussian(),10, 15);

    }

    public void setIteration(int iteration){
        this.iteration = iteration;
    }

    public double o(int neuron){
        double[]  p = weight.weightOfNeuron(neuron);
        int[]  digit = models[neuron];
        double sum = 0   ; // do not use bias
        for(int k = 0 ; k < number_pixels ; k++){
            sum += p[k]  * digit[k];
        }

        return s.apply(sum);
    }



    public  double   deltaWeigth(int pixel, int neuron){
        double sum = 0 ;
        for(int model = 0 ; model < models.length ; model++ ){
            sum += speed * models[model][pixel] * (models[neuron][pixel] - o(neuron));
        }
        return sum / 10;
  }

  public double[]  updateWeightNeuron(int neuron){
        double[] dw = new double[15];

        for(int pixel = 0 ; pixel < 15 ; pixel++ ){
           dw[pixel]= deltaWeigth(pixel,neuron);
        }

        return dw;
  }

  public void updateWeight(){
        double[][] dw = new double[10][15];
        for(int neuron  = 0 ; neuron < 10 ; neuron++ ){
            dw[neuron] = updateWeightNeuron(neuron);
        }

        for(int neuron = 0 ; neuron < models.length ; neuron++ ){
            for(int pixel = 0 ; pixel < number_pixels ; pixel++ ){
                weight.updateWeight(neuron,pixel,dw[neuron][pixel]);
            }
        }
    }


  public void start(){

        int n = 0;
        while(n < iteration ){
            updateWeight();
            n++;

        }
        saveWeight();

  }

    private void saveWeight() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(new File(nameFile))))) {
            out.writeObject(weight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
