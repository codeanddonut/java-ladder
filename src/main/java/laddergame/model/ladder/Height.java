package laddergame.model.ladder;

public class Height {
    private static final int MIN_HEIGHT = 1;

    private final int height;

    public Height(int height) {
        this.height = (height >= MIN_HEIGHT) ? height : MIN_HEIGHT;
    }

    public int get() {
        return this.height;
    }
}