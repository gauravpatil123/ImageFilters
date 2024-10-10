import edu.duke.*;
import org.apache.commons.csv.*;
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

    public static void main(String[] args) {

    }

}
