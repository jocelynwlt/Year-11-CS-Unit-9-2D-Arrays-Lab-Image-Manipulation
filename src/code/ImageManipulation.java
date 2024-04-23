package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage image = new APImage("cyberpunk2077.jpg");
        image.draw();
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 30);
        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");


    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int height = image.getHeight();
        int width = image.getWidth();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Pixel pixel = image.getPixel(j, i);
                int average = getAverageColour(pixel);
                pixel.setRed(average);
                pixel.setBlue(average);
                pixel.setGreen(average);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int average = (pixel.getBlue() + pixel.getGreen() + pixel.getRed()) / 3;
        return average;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage grayScale = new APImage(pathOfFile);
        int avg = 0;
        for (int i = 0; i < grayScale.getWidth(); i ++) {
            for (int j = 0; j < grayScale.getHeight(); j ++) {
                avg = getAverageColour(grayScale.getPixel(i,j));
                if (avg < 128) {
                    avg = 0;
                } else {
                    avg = 255;
                }
                grayScale.getPixel(i,j).setRed(avg);
                grayScale.getPixel(i,j).setBlue(avg);
                grayScale.getPixel(i,j).setGreen(avg);
            }
        }
        grayScale.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage sourceImage = new APImage(pathToFile);
        APImage output = new APImage(pathToFile);
        int avg = 0;
        int avgLeft = 0;
        int avgBelow = 0;
        for (int i = 1; i < sourceImage.getWidth(); i ++) {
            for (int j = 0; j < sourceImage.getHeight() - 1; j ++) {
                avg = getAverageColour(sourceImage.getPixel(i,j));
                avgLeft = getAverageColour(sourceImage.getPixel(i-1,j));
                avgBelow = getAverageColour(sourceImage.getPixel(i,j+1));
                if (Math.abs(avg - avgLeft) > threshold || Math.abs(avg - avgBelow) > threshold) {
                    output.getPixel(i,j).setRed(0);
                    output.getPixel(i,j).setBlue(0);
                    output.getPixel(i,j).setGreen(0);
                } else {
                    output.getPixel(i,j).setRed(255);
                    output.getPixel(i,j).setBlue(255);
                    output.getPixel(i,j).setGreen(255);
                }
            }
        }
        output.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage sourceImage = new APImage(pathToFile);
        APImage output = new APImage(pathToFile);
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < sourceImage.getWidth() - 1; i ++) {
            for (int j = 0; j < sourceImage.getHeight() -1; j ++) {
                r = sourceImage.getPixel(i,j).getRed();
                g = sourceImage.getPixel(i,j).getGreen();
                b = sourceImage.getPixel(i,j).getBlue();
                output.getPixel(sourceImage.getWidth()-i,j).setRed(r);
                output.getPixel(sourceImage.getWidth()-i,j).setGreen(g);
                output.getPixel(sourceImage.getWidth()-i,j).setBlue(b);
            }
        }
        output.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage sourceImage = new APImage(pathToFile);
        APImage output = new APImage(sourceImage.getHeight(),sourceImage.getWidth());
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < sourceImage.getWidth() - 1; i ++) {
            for (int j = 0; j < sourceImage.getHeight()- 1; j ++) {
                r = sourceImage.getPixel(i,j).getRed();
                g = sourceImage.getPixel(i,j).getGreen();
                b = sourceImage.getPixel(i,j).getBlue();
                output.getPixel(output.getWidth()-j,i).setRed(r);
                output.getPixel(output.getWidth()-j,i).setGreen(g);
                output.getPixel(output.getWidth()-j,i).setBlue(b);
            }
        }
        output.draw();
    }
}
