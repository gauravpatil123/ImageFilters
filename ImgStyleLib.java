import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class ImgStyleLib {

    private int Width;
    private int Height;
    private int newWidth;
    private int newHeight;
    public ImageResource outImage;
    public ImageResource inImage;

    public ImgStyleLib(ImageResource inImage) {

        this.inImage = inImage;
        this.Width = inImage.getWidth();
        this.Height = inImage.getHeight();
        this.outImage = new ImageResource(Width, Height);

    }

    public void log(String inp) {
        System.out.println(inp);
    }

    public String loader(int percent) {
        String out = "|> |  |  |  |  |  |  |  |  |  |";
        if(percent < 10) {
            out =  "|> |  |  |  |  |  |  |  |  |  |";
        } else if(percent < 20) {
            out =  "|==|> |  |  |  |  |  |  |  |  |";
        } else if(percent < 30) {
            out = "|==|==|> |  |  |  |  |  |  |  |";
        }
        return out;
    }

    public Pixel greyPix(Pixel pixel, int px, int py, int cx, int cy) {

        System.out.println("Grey Pixels\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int sum = inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue();
        int avg = sum / 3;
        pixel.setRed(avg);
        pixel.setGreen(avg);
        pixel.setBlue(avg);
        return pixel;

    }

    public Pixel contrastPix(Pixel pix, int px, int py, int cx, int cy) {

        System.out.println("Contrast Pixels\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Red = 255 - inPixel.getRed();
        int Green = 255 - inPixel.getGreen();
        int Blue = 255 - inPixel.getBlue();
        pix.setRed(Red);
        pix.setGreen(Green);
        pix.setBlue(Blue);
        return pix;

    }

    public Pixel PurplePix(Pixel pix, int px, int py, int cx, int cy) {

        System.out.println("Purple Pixels\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Green = inPixel.getGreen();
        pix.setRed(255);
        pix.setGreen(Green);
        pix.setBlue(255);
        return pix;

    }

    public Pixel SaffronPix(Pixel pix, int px, int py, int cx, int cy) {

        System.out.println("Saffron Pixels\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Red = 100 + inPixel.getRed();
        int Green = 60 + inPixel.getGreen();
        int Blue = 20 + inPixel.getBlue();
        pix.setRed(Red);
        pix.setGreen(Green);
        pix.setBlue(Blue);
        return pix;

    }

    public Pixel RedPix(Pixel pix, int px, int py, int cx, int cy) {

        System.out.println("Red Pixels\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Green = inPixel.getGreen();
        int Blue = inPixel.getBlue();
        pix.setRed(255);
        pix.setGreen(Green);
        pix.setBlue(Blue);
        return pix;

    }

    public Pixel ColorPix(Pixel pix, int px, int py, int cx, int cy, int R, int
            G, int B) {

        System.out.println("Color Pixel\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Red = R + inPixel.getRed();
        int Green = G + inPixel.getGreen();
        int Blue = B + inPixel.getBlue();
        pix.setRed(Red);
        pix.setGreen(Green);
        pix.setBlue(Blue);
        return pix;

    }

    public Pixel MirrorPix(Pixel pix, int px, int py, int cx, int cy) {

        System.out.println("Mirror Pixel\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Red = inPixel.getRed();
        int Green = inPixel.getGreen();
        int Blue = inPixel.getBlue();
        pix.setRed(Red);
        pix.setGreen(Green);
        pix.setBlue(Blue);
        return pix;

    }

    public Pixel RandomPix(Pixel pix, int px, int py, int cx, int cy) {
        
        System.out.println("Random Pixels\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Red = inPixel.getRed();
        int Green = inPixel.getGreen();
        int Blue = inPixel.getBlue();
        Random rand = new Random();
        int const_value = rand.nextInt(3);
        if (const_value == 0) {
            //changing R value
            int rand_fill_r = rand.nextInt(256);
            pix.setRed(rand_fill_r);
            pix.setGreen(Green);
            pix.setBlue(Blue);
        } else if (const_value == 1) {
            //changing G value
            int rand_fill_g = rand.nextInt(256);
            pix.setRed(Red);
            pix.setGreen(rand_fill_g);
            pix.setBlue(Blue);
        } else if (const_value == 2) {
            //changing B value
            int rand_fill_b = rand.nextInt(256);
            pix.setRed(Red);
            pix.setGreen(Green);
            pix.setBlue(rand_fill_b);

        }
        
        return pix;
    }

    public Pixel RandomPix2(Pixel pix, int px, int py, int cx, int cy) {
        
        System.out.println("Random Pixels type 2\t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Red = inPixel.getRed();
        int Green = inPixel.getGreen();
        int Blue = inPixel.getBlue();
        Random rand = new Random();
        int const_value = rand.nextInt(3);
        if (const_value == 0) {
            //const R value
            int rand_fill_g = rand.nextInt(256);
            int rand_fill_b = rand.nextInt(256);
            pix.setRed(Red);
            pix.setGreen(rand_fill_g);
            pix.setBlue(rand_fill_b);
        } else if (const_value == 1) {
            //const G value
            int rand_fill_r = rand.nextInt(256);
            int rand_fill_b = rand.nextInt(256);
            pix.setRed(rand_fill_r);
            pix.setGreen(Green);
            pix.setBlue(rand_fill_b);
        } else if (const_value == 2) {
            //const B value
            int rand_fill_r = rand.nextInt(256);
            int rand_fill_g = rand.nextInt(256);
            pix.setRed(rand_fill_r);
            pix.setGreen(rand_fill_g);
            pix.setBlue(Blue);

        }
        
        return pix;
    }

    public Pixel SwitchPix(Pixel pix, int px, int py, int cx, int cy) {
        
        System.out.println("Switch Pixels \t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        int Red = inPixel.getBlue();
        int Green = inPixel.getRed();
        int Blue = inPixel.getGreen();
        pix.setRed(Red);
        pix.setGreen(Green);
        pix.setBlue(Blue);
        return pix;
    }

    public Pixel SwitchRandPix(Pixel pix, int px, int py, int cx, int cy) {
        
        System.out.println("Switch Random Pixels \t"+px+","+py);
        int Px = px - cx;
        int Py = py - cy;
        Pixel inPixel = inImage.getPixel(Px, Py);
        Random rand = new Random();
        int const_value = rand.nextInt(3);

        if(const_value == 0) {
            int Red = inPixel.getBlue();
            int Green = inPixel.getRed();
            int Blue = inPixel.getGreen();
            pix.setRed(Red);
            pix.setGreen(Green);
            pix.setBlue(Blue);
        } else if (const_value == 1) {
            int Red = inPixel.getGreen();
            int Green = inPixel.getBlue();
            int Blue = inPixel.getRed();
            pix.setRed(Red);
            pix.setGreen(Green);
            pix.setBlue(Blue);
        } else if (const_value == 2) {
            int Red = inPixel.getRed();
            int Green = inPixel.getGreen();
            int Blue = inPixel.getBlue();
            pix.setRed(Red);
            pix.setGreen(Green);
            pix.setBlue(Blue);
        }
        
        return pix;
    }

}
