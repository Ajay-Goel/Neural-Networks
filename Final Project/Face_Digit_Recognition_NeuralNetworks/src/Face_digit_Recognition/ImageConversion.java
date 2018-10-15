/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Face_digit_Recognition;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ajaygoel
 */
public class ImageConversion {

    public static double[] imageLoad(String path, int sizex, int sizey) {
        File imageLocation = new File(path);

        BufferedImage bufferedImage;
        BufferedImage image;

        bufferedImage = new BufferedImage(
                sizex,
                sizey,
                BufferedImage.TYPE_BYTE_GRAY);

        try {
            image = ImageIO.read(imageLocation);
        } catch (IOException IOex) {
            System.out.println(path + " is not loaded");
            return null;
        }

        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        double[] data = new double[sizex * sizey];

        for (int k = 0; k < sizex; k++) {
            for (int l = 0; l < sizey; l++) {
                int[] p = new int[3];
                bufferedImage.getRaster().getPixel(k, l, p);

                if (p[0] > 128) {
                    data[k * sizex + l] = 0.0;
                } else {
                    data[k * sizex + l] = 1.0;
                }
            }
        }
        return data;
    }

}
//BufferedImage bufferedImage;	// Formatted image
//BufferedImage image; 	// Image uploaded
