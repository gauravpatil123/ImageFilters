import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

public class ImageStyle {

    public ImageResource Image;
    public int Width;
    public int Height;

    public ImageStyle(ImageResource Image) {

        this.Image = Image;
        this.Width = Image.getWidth();
        this.Height = Image.getHeight();

    }

    public void log(String inp) {
        System.out.println(inp);
    }

    public int calculateProgress(int Height, int py) {

        float percent = (float)py/(float)Height;
        percent = percent * 100;
        //log(String.valueOf(percent));
        return (int)percent;
    }

    public String loader(int percent) {
        String out = "|> |  |  |  |  |  |  |  |  |  |";
        if(percent < 10) {
            out =  "|> |  |  |  |  |  |  |  |  |  |";
        } else if(percent < 20) {
            out = "|==|> |  |  |  |  |  |  |  |  |";
        } else if(percent < 30) {
            out = "|==|==|> |  |  |  |  |  |  |  |";
        } else if(percent < 40) {
            out = "|==|==|==|> |  |  |  |  |  |  |";
        } else if (percent < 50) {
            out = "|==|==|==|==|> |  |  |  |  |  |";   
        } else if(percent < 60) {
            out = "|==|==|==|==|==|> |  |  |  |  |";
        } else if (percent < 70) {
            out = "|==|==|==|==|==|==|> |  |  |  |";
        } else if (percent < 80) {
            out = "|==|==|==|==|==|==|==|> |  |  |";
        } else if (percent < 90) {
            out = "|==|==|==|==|==|==|==|==|> |  |";
        } else if (percent < 100) {
            out = "|==|==|==|==|==|==|==|==|==|> |";
        } else if (percent == 100) {
            out = "|==|==|==|==|==|==|==|==|==|==|";
        }
        return out;
    }

    public void pbar(int Height, int py) {
        int progress = calculateProgress(Height, py);
        String pbar = loader(progress);
        log(pbar);
    }

    public void sbar(int Height, int py) {
        long startTime = System.currentTimeMillis();
        long total = (long) Height;
        long current = (long) py;
        printProgress(startTime, total, current);
    }

    private static void printProgress(long startTime, long total, long current) {
        long eta = current == 0 ? 0 : 
            (total - current) * (System.currentTimeMillis() - startTime) / current;
    
        String etaHms = current == 0 ? "N/A" : 
                String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                        TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));
    
        StringBuilder string = new StringBuilder(140);   
        int percent = (int) (current * 100 / total);
        string
            .append('\r')
            .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
            .append(String.format(" %d%% [", percent))
            .append(String.join("", Collections.nCopies(percent, "=")))
            .append('>')
            .append(String.join("", Collections.nCopies(100 - percent, " ")))
            .append(']')
            .append(String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
            .append(String.format(" %d/%d, ETA: %s", current, total, etaHms));
    
        System.out.print(string);
    }

    public ImageResource greyScale(){ 
        ImageResource GreyImage = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for(Pixel pixel : GreyImage.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            // pbar(Height, py);
            sbar(Height, py);
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
            pbar(Height, py);
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
            pbar(Height, py);
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
            pbar(Height, py);
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
            pbar(Height, py);
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
            pbar(Height, py);
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
            pbar(Height, py);
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
            pbar(Height, py);
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

    public ImageResource RandomPix() {
        ImageResource RDP = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for (Pixel pixel : RDP.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pbar(Height, py);
            pixel = ISL.RandomPix(pixel, px, py, 0, 0);
        }
        return RDP;
    }

    public ImageResource RandomPix2() {
        ImageResource RDP2 = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for (Pixel pixel : RDP2.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pbar(Height, py);
            pixel = ISL.RandomPix(pixel, px, py, 0, 0);
        }
        return RDP2;
    }

    public ImageResource SwitchPix() {
        ImageResource SPX = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for (Pixel pixel : SPX.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pbar(Height, py);
            pixel = ISL.SwitchPix(pixel, px, py, 0, 0);
        }
        return SPX;
    }

    public ImageResource SwitchRandPix() {
        ImageResource SPRX = new ImageResource(Width, Height);
        ImgStyleLib ISL = new ImgStyleLib(Image);
        for (Pixel pixel : SPRX.pixels()) {
            int px = pixel.getX();
            int py = pixel.getY();
            pbar(Height, py);
            pixel = ISL.SwitchRandPix(pixel, px, py, 0, 0);
        }
        return SPRX;
    }

    public void convert(String Filter) {

        if(Filter.equals("grayScale")) {
            ImageResource GreyImage = greyScale();
            GreyImage.draw();
            String ImageName = Image.getFileName();
            String GSImage = "GS-"+ImageName;
            GreyImage.setFileName(GSImage);
            GreyImage.save();
        }

        if(Filter.equals("Purple")) {
            ImageResource PImage = PurpleFilter();
            PImage.draw();
            String ImageName = Image.getFileName();
            String PI = "PI-"+ImageName;
            PImage.setFileName(PI);
            PImage.save();
        }

        if(Filter.equals("Saffron")) {
            ImageResource SImage = SaffronFilter();
            SImage.draw();
            String ImageName = Image.getFileName();
            String SI = "SI-"+ImageName;
            SImage.setFileName(SI);
            SImage.save();
        }

        if(Filter.equals("Color")) {
            ImageResource CImage = ColorFilter(100, 60, 20);
            CImage.draw();
            String ImageName = Image.getFileName();
            String CI = "CI-"+ImageName;
            CImage.setFileName(CI);
            CImage.save();
        }

        if(Filter.equals("Side_Mirror")) {
            ImageResource SMI = SideMirror();
            SMI.draw();
            String ImageName = Image.getFileName();
            String SI = "SMI-"+ImageName;
            SMI.setFileName(SI);
            SMI.save();
        }

        if(Filter.equals("Top_Mirror")) {
            ImageResource TMI = TopMirror();
            TMI.draw();
            String ImageName = Image.getFileName();
            String TI = "TMI-"+ImageName;
            TMI.setFileName(TI);
            TMI.save();
        }

        if(Filter.equals("Contrast")) {
            ImageResource CTI = ContrastFilter();
            CTI.draw();
            String ImageName = Image.getFileName();
            String CI = "CTI-"+ImageName;
            CTI.setFileName(CI);
            CTI.save();
        }

        if(Filter.equals("Indian_Flag")) {
            ImageResource IFI = IndianFlag();
            IFI.draw();
            String ImageName = Image.getFileName();
            String IF = "IFI-"+ImageName;
            IFI.setFileName(IF);
            IFI.save();
        }
        
        if(Filter.equals("RandomPix")) {
            ImageResource RDI = RandomPix();
            RDI.draw();
            String ImageName = Image.getFileName();
            String RI = "RDI_"+ImageName;
            RDI.setFileName(RI);
            RDI.save();
        }

        if(Filter.equals("RandomPix2")) {
            ImageResource RDI2 = RandomPix();
            RDI2.draw();
            String ImageName = Image.getFileName();
            String RI2 = "RDI2_"+ImageName;
            RDI2.setFileName(RI2);
            RDI2.save();
        }

        if(Filter.equals("SwitchPix")) {
            ImageResource SPX = SwitchPix();
            SPX.draw();
            String ImageName = Image.getFileName();
            String SI = "SPX_"+ImageName;
            SPX.setFileName(SI);
            SPX.save();
        }

        if(Filter.equals("SwitchRandPix")) {
            ImageResource SPRX = SwitchRandPix();
            SPRX.draw();
            String ImageName = Image.getFileName();
            String SRI = "SPRX_"+ImageName;
            SPRX.setFileName(SRI);
            SPRX.save();
        }

    }

    public static void main(String[] args) {

        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {

            ImageResource inImage = new ImageResource(f);

            for (String arg : args) {

                System.out.println("Started conversion for "+arg);
                ImageStyle IS = new ImageStyle(inImage);
                IS.convert(arg);
                System.out.println("Ended conversion for "+arg);
                    
            }

        }

    }

}
