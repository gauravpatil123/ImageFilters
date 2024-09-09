import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ImageStyle {

    public ImageResource Image;
    public int Width;
    public int Height;

    public ImageStyle(ImageResource Image) {

        this.Image = Image;
        this.Width = Image.getWidth();
        this.Height = Image.getHeight();

    }

    public ImageResource greyScale(){ 
        ImageResource GreyImage = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : GreyImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pixel = ISL.greyPix(pixel, px, py, 0, 0);
        }
        return GreyImage;
    }

    public ImageResource ContrastFilter() {
        ImageResource ContrastImage = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : ContrastImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pixel = ISL.contrastPix(pixel, px, py, 0, 0);
        }
        return ContrastImage;
    }

    public ImageResource PurpleFilter() {
        ImageResource PImage = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : PImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pixel = ISL.PurplePix(pixel, px, py, 0, 0);
        }
        return PImage;
    }

    public ImageResource SaffronFilter() {
        ImageResource SImage = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : SImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pixel = ISL.SaffronPix(pixel, px, py, 0, 0);
        }
        return SImage;
    }

    public ImageResource ColorFilter(int R, int G, int B) {
        ImageResource CImage = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : CImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pixel = ISL.ColorPix(pixel, px, py, 0, 0, R, G, B);
        }
        return CImage;
    }

    public ImageResource SideMirror() {
        ImageResource SMI = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : SMI.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pixel = ISL.MirrorPix(pixel, px, py, (2*px) - Width + 1, 0);
        }
        return SMI;
    }

    public ImageResource TopMirror() {
        ImageResource TMI = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : TMI.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pixel = ISL.MirrorPix(pixel, px, py, 0, (2*py) - Height + 1);
        }
        return TMI;
    }

    public ImageResource IndianFlag() {
        ImageResource IFI = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : IFI.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            if(py <= (Height/3) ) {
                pixel = ISL.SaffronPix(pixel, px, py, 0, 0);
            }
            if(py >= (2 * Height / 3) ) {
                pixel = ISL.ColorPix(pixel, px, py, 0, 0, 0, 255, 0);
            }
            if(py > (Height / 3) && py < (2 * Height / 3 ) ) {
                pixel = ISL.ColorPix(pixel, px, py, 0, 0, 200, 200, 200);
            }
        }
        return IFI;
    }

    public void convert(String Filter) {

        if(Filter == "grayScale") {
            ImageResource GreyImage = greyScale();
            GreyImage.draw();
            String ImageName = Image.getFileName();
            String GSImage = "GS-"+ImageName;
            GreyImage.setFileName(GSImage);
            GreyImage.save();
        }
        
        if(Filter == "Purple") {
            ImageResource PImage = PurpleFilter();
            PImage.draw();
            String ImageName = Image.getFileName();
            String PI = "PI-"+ImageName;
            PImage.setFileName(PI);
            PImage.save();
        }

        if(Filter == "Saffron") {
            ImageResource SImage = SaffronFilter();
            SImage.draw();
            String ImageName = Image.getFileName();
            String SI = "SI-"+ImageName;
            SImage.setFileName(SI);
            SImage.save();
        }

        if(Filter == "Color") {
            ImageResource CImage = ColorFilter(100, 60, 20);
            CImage.draw();
            String ImageName = Image.getFileName();
            String CI = "CI-"+ImageName;
            CImage.setFileName(CI);
            CImage.save();
        }

        if(Filter == "Side_Mirror") {
            ImageResource SMI = SideMirror();
            SMI.draw();
            String ImageName = Image.getFileName();
            String SI = "SMI-"+ImageName;
            SMI.setFileName(SI);
            SMI.save();
        }

        if(Filter == "Top_Mirror") {
            ImageResource TMI = TopMirror();
            TMI.draw();
            String ImageName = Image.getFileName();
            String TI = "TMI-"+ImageName;
            TMI.setFileName(TI);
            TMI.save();
        }

        if(Filter == "Contrast") {
            ImageResource CTI = ContrastFilter();
            CTI.draw();
            String ImageName = Image.getFileName();
            String CI = "CTI-"+ImageName;
            CTI.setFileName(CI);
            CTI.save();
        }

        if(Filter == "Indian_Flag") {
            ImageResource IFI = IndianFlag();
            IFI.draw();
            String ImageName = Image.getFileName();
            String IF = "IFI-"+ImageName;
            IFI.setFileName(IF);
            IFI.save();
        } 

    }

    public static void main(String[] args) {

        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {

            //String option = args[0]; // Make this work
            //for loop iterate string arguments

            ImageResource inImage = new ImageResource(f);

            // String arg = args[0];
            // System.out.println("Started conversion for "+arg);
            // ImageStyle IS = new ImageStyle(inImage);
            // IS.convert(arg);
            // System.out.println("Ended conversion");

            ImageStyle IS = new ImageStyle(inImage);
            IS.convert("Side_Mirror");

            /*
            ImageResource inImage = new ImageResource(f);
            ImageStyle IS = new ImageStyle(inImage);
            IS.convert("grayScale");
            */

            // ImageStyle IS = new ImageStyle(inImage);
            // IS.convert("Contrast");

            // ImageStyle IS2 = new ImageStyle(inImage);
            // IS2.convert("grayScale");

            // ImageStyle IS3 = new ImageStyle(inImage);
            // IS3.convert("Color");

        }

    }

}
