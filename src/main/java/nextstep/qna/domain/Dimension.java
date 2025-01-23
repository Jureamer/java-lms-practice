package nextstep.qna.domain;

public class Dimension {
    private int width;
    private int height;

    public Dimension(int width, int height) {
        validateDimension(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateDimension(int width, int height) {
        if (width < 300) {
            throw new IllegalArgumentException("가로는 300 픽셀 이상이어야 합니다.");
        }

        if (height < 200) {
            throw new IllegalArgumentException("세로는 200 픽셀 이상이어야 합니다.");
        }

        if ((float) width / height != 3.0 / 2.0) {
            throw new IllegalArgumentException("가로 세로 비율은 3:2이어야 합니다.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
