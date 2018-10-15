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
public class Matrix {

    int rows, cols;
    double[][] matrix_array;

    public Matrix(int row, int columns) {
        this.rows = row;
        this.cols = columns;
        matrix_array = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix_array[i][j] = 0;
            }
        }
    }

    public void print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.printf("%2f\t", this.matrix_array[i][j]);

            }
        }

    }

    public static Matrix fromArray(double[] arr) {
        Matrix m = new Matrix(arr.length, 1);
        for (int i = 0; i < arr.length; i++) {
            m.matrix_array[i][0] = arr[i];
        }
        return m;
    }

    public void random() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix_array[i][j] = (Math.random() * 2 - 1); // value between -1 and 1
            }
        }
    }

    public void add(int n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix_array[i][j] += n;
            }
        }
    }

    public void add(Matrix n) {

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix_array[i][j] += n.matrix_array[i][j];
            }
        }

    }

    public static Matrix subtract(Matrix a, Matrix b) {
        //return a-b
        Matrix result = new Matrix(a.rows, a.cols);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                result.matrix_array[i][j] = a.matrix_array[i][j] - b.matrix_array[i][j];
            }
        }
        return result;

    }

    public void multiply(double n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix_array[i][j] *= n;
            }
        }
    }

    public void multiply(Matrix m) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix_array[i][j] *= m.matrix_array[i][j];
            }
        }
    }

    public static Matrix multiply(Matrix n, Matrix b) {
        if (n.cols != b.rows) {
            return null;
        }
        Matrix result = new Matrix(n.rows, b.cols);

        for (int i = 0; i < n.rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                //Dot Productof values in col
                double sum = 0;
                for (int k = 0; k < n.cols; k++) {
                    sum += n.matrix_array[i][k] * b.matrix_array[k][j];
                }
                result.matrix_array[i][j] = sum;
            }
        }
        return result;
    }

    public static Matrix transpose(Matrix a) {
        Matrix result = new Matrix(a.cols, a.rows);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                result.matrix_array[j][i] = a.matrix_array[i][j];
            }
        }
        return result;
    }

    public void map() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                double currentVal = this.matrix_array[i][j];
                this.matrix_array[i][j] = (1 / (1 + Math.exp(-currentVal)));

            }
        }
    }

    public double[] toArr() {
        double[] arr = new double[rows];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                arr[i] = this.matrix_array[i][j];
            }
        }
        return arr;
    }

    public static Matrix dSigmoid(Matrix a) {
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                double currentVal = a.matrix_array[i][j];
                a.matrix_array[i][j] = currentVal * (1 - currentVal);
            }
        }
        return a;
    }
}
