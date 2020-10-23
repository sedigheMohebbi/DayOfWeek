public enum Days {
    sunday(1), monday(2), tuesday(3), wednesday(4), thursday(5), friday(6), saturday(0);
    private final int value;

    Days(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
