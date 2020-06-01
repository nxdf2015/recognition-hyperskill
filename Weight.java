package recognition;

import java.io.Serializable;
import java.util.function.Supplier;

public class Weight implements Serializable {

    private double[][] weights;

   transient private Supplier<Double> generator;

    public Weight(int[][] models,int neuron , int pixels) {
        weights = new double[neuron][pixels];
        for(int i = 0 ; i < models.length ; i++ ){
            for(int j = 0 ; j <  models[0].length ; j++ ){
                weights[i][j] = models[i][j] == 1 ? 1 : -1;
            }
        }
    }

    public void setWeight(int neuron,int pixel, double value){
        weights[neuron][pixel] = value;
    }

    public double weight(int neuron , int pixel){
        return weights[neuron][pixel];
    }

    public double[]  weightOfNeuron(int neuron){
        return weights[neuron];
    }

    public Weight(Supplier<Double> generator,int neuron, int pixels) {
        weights = new double[neuron][pixels];
        this.generator = generator;

    }

    private void initialize(){
        for(int neuron = 0 ; neuron < weights.length ; neuron++ ){
            for (int pixel = 0 ; pixel < weights[0].length ; pixel++ ){
                weights[neuron][pixel] = generator.get();
            }
        }

    }

    public void updateWeight(int neuron, int pixel, double dw) {
        this.weights[neuron][pixel] += dw;
    }

    public static void show(double[][] weight){

        for(int i = 0 ; i < weight.length ; i++ ){
            for(double x : weight[i]){
                System.out.printf(" %f ",x);
            }
            System.out.println();
        }
      System.out.println();
  }



}
