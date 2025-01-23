package nextstep.courses.domain;

import java.awt.*;

public class CoverImage {
    private int size;
    private ImageType imageType;
    private Dimension dimension;

    public CoverImage(int size, ImageType imageType, int width, int height) {
        checkSize(size);
        this.size = size;
        this.imageType = imageType;
        this.dimension = new Dimension(width, height);
    }

    private void checkSize(int size) {
    }
}
