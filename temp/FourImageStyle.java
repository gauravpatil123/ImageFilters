import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class FourImageStyle {

    private int Width;
    private int Height;
    private int newWidth;
    private int newHeight;
    private ImageResource outImage;
    private ImageResource inImage;

    public FourImageStyle(ImageResource Image) {

        this.inImage = Image;
        this.Width = inImage.getWidth();
        this.Height = inImage.getHeight();
        this.newWidth = 2*Width;
        this.newHeight = 2*Height;
        this.outImage = new ImageResource(newWidth, newHeight);

    }


    private ImageResource FourStyles() {

        for(Pixel pixel : outImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            //System.out.println("Color Pixels "+px+","+py);
            if(px < Width && py < Height) {
                System.out.println("Color Pixels\t"+px+","+py);
                Pixel inPixel = inImage.getPixel(px, py);
                pixel.setRed(inPixel.getRed());
                pixel.setGreen(inPixel.getGreen());
                pixel.setBlue(inPixel.getBlue());
            }
            if(px >= Width && px < newWidth && py < Height) {
                greyImage(pixel, px, py);
            }
            if(px >=Width && px < newWidth && py >= Height && py < newHeight) {
                contrastImage(pixel, px, py);
            }
            if(px < Width && py >= Height && py < newHeight) {
                reverseImage(pixel, px, py);
            }

        }
        return outImage; 

    }

    private void greyImage(Pixel pixel, int px, int py) {

        System.out.println("Grey Pixels\t"+px+","+py);
        int Px = px - Width;
        Pixel inPixel = inImage.getPixel(Px, py);
        int sum = inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue();
        int avg = sum / 3;
        pixel.setRed(avg);
        pixel.setGreen(avg);
        pixel.setBlue(avg); 

    }

    private void contrastImage(Pixel pixel, int px, int py) {

        System.out.println("Contrast Pixels\t"+px+","+py);
        int Px = px - Width;
        int Py = py - Height;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int CRed = 255 - inPixel.getRed();
        int CGreen = 255 - inPixel.getGreen();
        int CBlue = 255 - inPixel.getBlue();
        pixel.setRed(CRed);
        pixel.setGreen(CGreen);
        pixel.setBlue(CBlue);    

    }

    private void reverseImage(Pixel pixel, int px, int py) {

        System.out.println("Reverse Pixels\t"+px+","+py);
        int Px = (Width - 1) - px;
        int Py = py - Height;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int red = inPixel.getRed();
        int green = inPixel.getGreen();
        int blue = inPixel.getBlue();
        pixel.setRed(red);
        pixel.setGreen(green);
        pixel.setBlue(blue);

    }

    private void Convert() {

        ImageResource FourImage = FourStyles();
        FourImage.draw();
        String ImageName = inImage.getFileName();
        String FSImage = "FSI-" + ImageName;
        FourImage.setFileName(FSImage);
        FourImage.save(); 

    }

    public static void main(String[] args) {

        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            FourImageStyle FIS = new FourImageStyle(inImage);
            FIS.Convert();
        }

    }

}
