import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
//import Math.*;

//hiding image complete
//TODO: Remove Images from hidden image

public class HideImage {

    private ImageResource frontImage;
    private ImageResource backImage;
    private ImageResource HiddenImage;
    private int Fwidth;
    private int Fheight;
    private int Bwidth;
    private int Bheight;

    public HideImage(ImageResource FImage, ImageResource BImage) {

        this.frontImage = FImage;
        this.backImage = BImage;
        this.Fwidth = FImage.getWidth();
        this.Fheight = FImage.getHeight();
        this.Bwidth = BImage.getWidth();
        this.Bheight = BImage.getHeight();
        this.HiddenImage = new ImageResource(Fwidth, Fheight);

    }

    private ImageResource steganography() { //front image low quality deteriorate

        for(Pixel pixel : HiddenImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            Pixel FPixel = frontImage.getPixel(px, py);
            int Fred = FPixel.getRed();
            int Fgreen = FPixel.getGreen();
            int Fblue = FPixel.getBlue();
            Pixel HPixel = backImage.getPixel(px, py);
            int Bred = HPixel.getRed();
            int Bgreen = HPixel.getGreen();
            int Bblue = HPixel.getBlue();
            int red = hideColor(Fred, Bred);
            int green = hideColor(Fgreen, Bgreen);
            int blue = hideColor(Fblue, Bblue);
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
        return HiddenImage;

    }

    private int hideColor (int FrontColor, int BackColor) {

        int newColor = (int) ((Math.floor(FrontColor/16)*16) + Math.floor(BackColor/16));
        return newColor;

    }

    public void hideImage() {

        ImageResource HI = steganography();
        HI.draw();
        String FImageName = frontImage.getFileName();
        String BImageName = backImage.getFileName();
        String HImage = "Hidden-" + FImageName + "-" + BImageName;
        HI.setFileName(HImage);
        HI.save();

    }

    public static void main(String[] args) {

        ImageResource fi = new ImageResource();
        ImageResource bi = new ImageResource();

        HideImage hi = new HideImage(fi, bi);
        hi.hideImage();

    }

}
