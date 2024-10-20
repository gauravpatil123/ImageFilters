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
    //NOTE: write binary math for decryption
    //TODO: use args in main fxn to determine encryption or decryption

    private int unhideColor (int PixColor) {
        //TODO: documenting math and test
        int newColor = (int) (PixColor % 10);
        String nc = Integer.toString(newColor);
        nc = nc + "00";
        newColor = Integer.parseInt(nc);
        return newColor;
    }
}
