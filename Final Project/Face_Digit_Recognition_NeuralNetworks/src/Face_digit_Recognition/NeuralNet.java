/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Face_digit_Recognition;

/**
 *
 * @author ajaygoel
 */
public class NeuralNet {

    /**
     * @param args the command line arguments
     */
    int input_nodes, hidden_nodes, output_nodes;
    Matrix weights_inp_hidden;
    Matrix weights_hidden_op;
    Matrix bias_hidden;
    Matrix bias_output;
    double learning_rate;

    public NeuralNet(int InputNodes, int HiddenNodes, int output) {
        this.input_nodes = InputNodes;
        this.hidden_nodes = HiddenNodes;
        this.output_nodes = output;
        this.weights_inp_hidden = new Matrix(this.hidden_nodes, this.input_nodes);
        this.weights_hidden_op = new Matrix(this.output_nodes, this.hidden_nodes);
        this.weights_hidden_op.random();
        this.weights_inp_hidden.random();
        this.bias_hidden = new Matrix(this.hidden_nodes, 1);
        this.bias_output = new Matrix(this.output_nodes, 1);
        this.bias_hidden.random();
        this.bias_output.random();
        //this.learning_rate = 0.1;

    }

    public double[] feedForward(double[] input) {
        Matrix inputs = Matrix.fromArray(input);
        //System.out.println(learning_rate);
        //System.out.println("**************************");
        //op of hidden layer// generating the hidden outputs
        //Matrix hidden = new Matrix(weights_inp_hidden.rows, weights_inp_hidden.cols);
        Matrix hidden = Matrix.multiply(this.weights_inp_hidden, inputs);
        hidden.add(this.bias_hidden);
        //activation function
        hidden.map();
        //Genrating the output
        Matrix output = Matrix.multiply(this.weights_hidden_op, hidden);
        output.add(this.bias_output);
        output.map();
        // sending back
        return output.toArr();
    }

    public double BackPropagate(double[] input, double[] target) {

        Matrix inputs = Matrix.fromArray(input);
        
        //op of hidden layer// generating the hidden outputs
        //Matrix hidden = new Matrix(weights_inp_hidden.rows, weights_inp_hidden.cols);
        Matrix hidden = Matrix.multiply(this.weights_inp_hidden, inputs);
        hidden.add(this.bias_hidden);
        
        //activation function
        hidden.map();
        
        //Genrating the output
        Matrix outputs = Matrix.multiply(this.weights_hidden_op, hidden);
        outputs.add(this.bias_output);
        outputs.map();
        //int[] output =this.feedForward(input);
        
        //Comnvert array to matrix object
        //Matrix outputs = Matrix.fromArray(output);
        Matrix targets = Matrix.fromArray(target);
        
        //Calculate the error
        //Error = targets-output
        Matrix output_errors = Matrix.subtract(targets, outputs);
        //Calculate gradient
        Matrix output_gradient = Matrix.dSigmoid(outputs);
        output_gradient.multiply(output_errors);
        output_gradient.multiply(learning_rate);

        //Calculate deltas
        Matrix hidden_trans = Matrix.transpose(hidden);
        Matrix Weights_hid_op_deltas = Matrix.multiply(output_gradient, hidden_trans);
        // System.out.println("w before");
        //weights_hidden_op.print();
        
        //Adjust the weights by deltas
        this.weights_hidden_op.add(Weights_hid_op_deltas);
        // System.out.println("w af");
        //weights_hidden_op.print();
        this.bias_output.add(output_gradient);
        
        //Calculating the hidden layers errors 
        Matrix Weights_hidden_op_trans = Matrix.transpose(this.weights_hidden_op);
        Matrix hidden_errors = Matrix.multiply(Weights_hidden_op_trans, output_errors);

        //calculate hidden gradient
        Matrix hidden_gradient = Matrix.dSigmoid(hidden);
        hidden_gradient.multiply(hidden_errors);
        hidden_gradient.multiply(this.learning_rate);

        //calculating input to hidden deltas
        Matrix inputs_trans = Matrix.transpose(inputs);
        Matrix Weights_in_hid_deltas = Matrix.multiply(hidden_gradient, inputs_trans);
        this.weights_inp_hidden.add(Weights_in_hid_deltas);
        this.bias_hidden.add(hidden_gradient);

        // Returning error for analysis in graph
        double out_err = 0;
        for (int l = 0; l < output_errors.rows; l++) {
            for (int p = 0; p < output_errors.cols; p++) {
                out_err += output_errors.matrix_array[l][p];
            }
        }
        return out_err / (output_errors.cols * output_errors.rows);
    }

}
