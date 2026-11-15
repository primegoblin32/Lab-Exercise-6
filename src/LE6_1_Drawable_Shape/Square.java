package LE6_1_Drawable_Shape;

public class Square extends DrawableShape {
    private int sideLength;

    public Square() {
        this(0, 0, "BLACK", 1);
    }

    public Square(int sideLength) {
        this(0, 0, "BLACK", sideLength);
    }

    public Square(int centerX, int centerY, String color, int sideLength) {
        super(centerX, centerY, color);
        setSideLength(sideLength);
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side length must be positive.");
        }

        this.sideLength = sideLength;
    }

    public int computeArea() {
        return sideLength * sideLength;
    }

    public int computePerimeter() {
        return 4 * sideLength;
    }

    public String draw() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < sideLength; row++) {
            for (int col = 0; col < sideLength; col++) {
                if (row == 0 || row == sideLength - 1 ||
                        col == 0 || col == sideLength - 1) {
                    result.append("* ");
                } else {
                    result.append("  ");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }
}

