package com.toedter.matchers;

import com.toedter.util.ImageDifferentiator;
import net.avh4.util.imagecomparison.ImageComparison;
import net.avh4.util.imagecomparison.ImageComparisonResult;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DirectImageLooksLikeMatcher extends DiagnosingMatcher<Object> {

    private final BufferedImage referenceImage;
    private final Dimension referenceImageSize;

    private DirectImageLooksLikeMatcher(BufferedImage referenceImage, Dimension referenceImageSize) {
        this.referenceImage = referenceImage;
        this.referenceImageSize = referenceImageSize;
    }

    protected boolean matches(Object item, Description mismatchDescription) {
        ImageComparisonResult result = ImageComparison.compare(item, this.referenceImage);
        if (result.isEqual()) {
            return true;
        } else if (this.referenceImage == null) {
            mismatchDescription.appendText("approval image ");
            mismatchDescription.appendText(" doesn't exist -- expected to find it in ");
            return false;
        } else {
            mismatchDescription.appendText("images don't match: ");
            mismatchDescription.appendText(result.getFailureMessage());
            try {
                File imagesDir = new File(System.getProperty("differingImagesFolder"));
                if (!imagesDir.exists()) {
                    imagesDir.mkdir();
                }
                Random r = new Random();
                String fileName = imagesDir + "/comparisionFailed"+r.nextInt(12383)+".png";
                System.out.println("diff image should be created at " + fileName);
                ImageDifferentiator.createDiffImage(referenceImage, (BufferedImage) item, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public void describeTo(Description description) {
        if (this.referenceImage == null) {
            description.appendText("reference image to exist");
        } else {
            description.appendText(String.format("something that looks like (%dx%d)", this.referenceImageSize.width, this.referenceImageSize.height));
        }

    }

    public static Matcher<Object> looksLike(BufferedImage referenceImage, Dimension referenceImageSize) {
        return new DirectImageLooksLikeMatcher(referenceImage, referenceImageSize);
    }
}
