/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Face_digit_Recognition;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author ajaygoel
 */
public class Driver {

    public static ArrayList<double[]> targets;
    public static ArrayList<double[]> inputs;
    public static NeuralNet brain;
    static String workingDir = System.getProperty("user.dir");
    static HashMap<Date, Double> hm;

    public Driver(int a) {
        targets = new ArrayList<double[]>();
        inputs = new ArrayList<double[]>();
        hm = new HashMap<Date, Double>();
        if (a == 1) {
            brain = new NeuralNet(2500, 1250, 10);
        } else {
            brain = new NeuralNet(784, 392, 10);
        }
    }

    public static int func(int x) {
        return x * 2;
    }
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public static void main(String args[]) throws FileNotFoundException, IOException {
        System.out.println("Hi! Welcome to the world of neural networks:");
        System.out.println("Please enter 1 for Digit Recognition.");
        System.out.println("Please enter 2 for Face Recognition.");
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt();

        targets = new ArrayList<double[]>();
        inputs = new ArrayList<double[]>();
        hm = new HashMap<Date, Double>();
        int correct = 0;
        switch (a) {
            case 1:
                brain = new NeuralNet(784, 392, 10);
                System.out.println("Training Started:");
                train(targets, inputs);
                for (int y = 30000; y < 41000; y++) {
                    double[] result_arr = brain.feedForward(inputs.get(y));
                    int max = maximum(result_arr);
                    double[] original_output = targets.get(y);
                    int ans = value_original(original_output);
                    System.out.println("Row: " + (y + 2) + ",     Predicted Value: " + max + ",     Original Value: " + ans);
                    if (ans == max) {
                        correct++;
                    }
                }
                int percent = correct * 100 / 11000;
                System.out.println("");
                System.out.println("The Percentage of correct predicted output is " + percent + "%");
                G(hm);
                break;
            case 2:
                brain = new NeuralNet(2500, 1250, 10);
                System.out.println("Training Started:");
                train2(targets, inputs);
                int size = 50;
                for (int r = 1; r < 35; r++) {
                    double[] inpu = ImageConversion.imageLoad(workingDir + "/Face_testimages/test" + r + ".png", size, size);
                    double[] result_arr = brain.feedForward(inpu);
                    int max = maximum(result_arr);
                    int[] original_data = {0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10};
                    System.out.println(r + " image,  The maximum value: " + result_arr[max] + ",    Predicted Subject number " + (max + 1) + ",    Original Subject Number: " + original_data[r]);
                    if (original_data[r] == max + 1) {
                        correct++;
                    }
                }
                System.out.println("The percentage of testing data correct =" + (correct * 100) / 34 + "%");
                G(hm);
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public static void train(ArrayList<double[]> targets, ArrayList<double[]> inputs) {
        String workingDir = System.getProperty("user.dir");
        //System.out.println(workingDir);
        String csvFile = workingDir + "/train.csv";

        Scanner scanner = null;
        String InputLine = "";

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(csvFile)));
            scanner.hasNextLine();
            String ar = scanner.nextLine();
            while (scanner.hasNextLine()) {
                InputLine = scanner.nextLine();
                String[] InArray = InputLine.split(",");
                double[] a = new double[10];
                double[] input = new double[784];

                for (int i = 0; i < InArray.length; i++) {
                    if (i == 0) {
                        int b = Integer.parseInt(InArray[i]);
                        for (int j = 0; j < 10; j++) {
                            if (j == b) {
                                a[j] = 1;
                            } else {
                                a[j] = 0;
                            }
                        }
                        targets.add(a);
                    } else {
                        input[i - 1] = Double.parseDouble(InArray[i]);
                    }
                }
                inputs.add(input);
            }

        } catch (Exception e) {
            System.out.print(e);
        }

        double err = 0;
        brain.learning_rate = 0.001;
        for (int l = 0; l < 25; l++) {
            for (int o = 0; o < 30000; o++) {
                err = brain.BackPropagate(inputs.get(o), targets.get(o));
                Date date = new Date();
                hm.put(date, err);
            }
            System.out.println("Iteration No. : " + (l + 1));
        }

        System.out.println("Training has been done!");
        //G(hm);
    }

    public static void train2(ArrayList<double[]> targets, ArrayList<double[]> inputs) {
        double err = 0;
        int size = 50;
        brain.learning_rate = 0.1;
        for (int l = 0; l < 100; l++) {
            for (int k = 1; k < 6; k++) {
                for (int j = 1; j < 11; j++) {
                    String pattern = workingDir + "/Face_images/" + j + "/" + k + ".png";
                    double[] input = ImageConversion.imageLoad(pattern, size, size);

                    if (input == null) {
                        System.out.println("Cant find " + pattern);
                        continue;
                    }
                    double[] output = new double[10];

                    for (int q = 0; q < 10; q++) {
                        output[q] = 0.0;
                    }
                    output[j - 1] = 1.0;
                    Date date = new Date();
                    err = brain.BackPropagate(input, output);
                    hm.put(date, err);
                    System.out.println("Iteration:" + l + "     Folder:" + j + "     Image:" + k + "     Error:" + err);
                }
            }
        }

        System.out.println("Training has been done!");
    }

    public static int maximum(double[] result_arr) {
        int max = 0;
        for (int z = 0; z < 10; z++) {
            if (result_arr[z] > result_arr[max]) {
                max = z;
            }
        }
        return max;
    }

    public static int value_original(double[] original_value) {
        for (int z = 0; z < 10; z++) {
            if (original_value[z] == 1) {
                return z;
            }
        }
        return 0;
    }

    public static void G(HashMap<Date, Double> hm) {

        TimeSeries s1 = new TimeSeries("Time vs Error:");
        TimeSeriesCollection dataset1 = new TimeSeriesCollection();
        final JFreeChart chart1 = ChartFactory.createTimeSeriesChart("Time vs Error",
                "Timestamp",
                "Error", dataset1,
                true,
                true,
                false);

        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            // System.out.println(mentry.getKey()+"-----"+mentry.getValue());
            s1.addOrUpdate(new Millisecond((Date) mentry.getKey()), (double) mentry.getValue());

        }
        dataset1.addSeries(s1);

        ChartFrame frame1 = new ChartFrame("Chart For Analysis", chart1);
        frame1.setVisible(true);
        frame1.setSize(800, 680);
        XYPlot p1 = chart1.getXYPlot();
        p1.setRangeGridlinePaint(Color.BLACK);

    }

}
