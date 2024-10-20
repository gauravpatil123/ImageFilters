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

}
