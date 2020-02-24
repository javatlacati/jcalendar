package com.toedter.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDifferentiator {

    private static void createPngImage(BufferedImage image, String fileName) throws IOException {
        ImageIO.write(image, "png", new File(fileName));
    }

    private static void createJpgImage(BufferedImage image, String fileName) throws IOException {
        ImageIO.write(image, "jpg", new File(fileName));
    }

    public static void createDiffImage(BufferedImage referenceImage, BufferedImage anotherImage, String fileName) throws IOException {
        int height = referenceImage.getHeight();
        int width = referenceImage.getWidth();
        BufferedImage rImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    int pixelC = anotherImage.getRGB(x, y);
                    int pixelB = referenceImage.getRGB(x, y);
                    if (pixelB == pixelC ) {
                        rImage.setRGB(x, y,  referenceImage.getRGB(x, y));
                    } else {
                        int a= 0xff |  referenceImage.getRGB(x, y)>>24 ,
                                r= 0xff &  referenceImage.getRGB(x, y)>>16 ,
                                g= 0,
                                b= 0;

                        int modifiedRGB=a<<24|r<<16|g<<8|b;
                        rImage.setRGB(x,y,modifiedRGB);
                    }
                } catch (Exception e) {
                    // handled hieght or width mismatch
                    rImage.setRGB(x, y, 0x80ff0000);
                }
            }
        }
        String fileExtenstion = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        if (fileExtenstion.toUpperCase().contains("PNG")) {
            createPngImage(rImage, fileName);
        } else {
            createJpgImage(rImage, fileName);
        }
    }
}
