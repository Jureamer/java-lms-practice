package nextstep.courses.domain;

import nextstep.qna.domain.Dimension;

public class CoverImage {
    private int size;
    private ImageType imageType;
    private Dimension dimension;
    private int MAX_SIZE = 1 * 1024 * 1024;

    public CoverImage(int size, ImageType imageType, int width, int height) {
        checkSize(size);
        this.size = size;
        this.imageType = imageType;
        this.dimension = new Dimension(width, height);
    }

    private void checkSize(int size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하이어야 합니다.");
        }
    }
}
