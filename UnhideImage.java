import edu.duke.*;
import java.io.*;

public class UnhideImage {
    private ImageResource MixedImage;
    private ImageResource HiddenImage;
    private int Width;
    private int Height;

    public UnhideImage(ImageResource MImage) {

        this.MixedImage = MImage;
        this.Width = MImage.getWidth();
        this.Height = MImage.getHeight();
        this.HiddenImage = new ImageResource(Width, Height);

    }

    //TODO: add function to decrypt image
    //TODO: use args in main fxn to determine encryption or decryption

    private ImageResource UnhideImage() {

        for(Pixel pixel : HiddenImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            Pixel MPixel = MixedImage.getPixel(px, py);
            int Mred = MPixel.getRed();
            int Mgreen = MPixel.getGreen();
            int Mblue = MPixel.getBlue();
            int Hred = unhideColor(Mred);
            int Hgreen = unhideColor(Mgreen);
            int Hblue = unhideColor(Mblue);
            pixel.setRed(Hred);
            pixel.setGreen(Hgreen);
            pixel.setBlue(Hblue);
        }

        return HiddenImage;

    }

    private int unhideColor (int PixColor) {
        //TODO: documenting math and test
        int newColor = (int) (PixColor % 10);
        String nc = Integer.toString(newColor);
        nc = nc + "00";
        newColor = Integer.parseInt(nc);
        return newColor;
    }

    public void extractImage() {
        ImageResource HI = UnhideImage();
        HI.draw();
        String MImageName = MixedImage.getFileName();
        String HImageName = "Hidden-" + MImageName;
        HI.setFileName(HImageName);
        HI.save();
    }

}
