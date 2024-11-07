import edu.duke.*;
import java.io.*;

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

    private int getBcInt(int BackColor) {
        
        if (BackColor < 10) {
            return 0;
        } else if (BackColor < 100) {
            return 0;
        } else if (BackColor > 99) {
            int Backint = (int) Math.floor(BackColor/100);
            return Backint;
        }
        return 0;
    }

    private int hideColor (int FrontColor, int BackColor) {
        //TODO: Add documentation
        //NOTE: try to add a image distortion knob to the hide image fxn
        //TODO: make algorithm to handle 2 & 1 digit pixel values
        //TODO: Try string concatenation method to get the hexadecimal math correct
        //NOTE: can also test the math by asshining parts to the hiiden image and checking

        int newColor;

        if (FrontColor < 10) {

            newColor = getBcInt(BackColor)  ;

        } else {

            newColor = (int) (Math.floor(FrontColor/10)*10) + getBcInt(BackColor);
        
        }

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

        String arg = args[0];

        if (arg.equals("hide")) {

            ImageResource fi = new ImageResource();
            ImageResource bi = new ImageResource();
            HideImage hi = new HideImage(fi, bi);
            hi.hideImage();

        }

    }

}
