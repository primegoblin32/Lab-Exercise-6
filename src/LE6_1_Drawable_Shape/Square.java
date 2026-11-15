package LE6_1_Drawable_Shape;

public class Square extends DrawableShape {
    private int sideLength;
    private char character;

    public Square() {
        this(0, 0, "BLACK", 5, '*');
    }

    public Square(int sideLength) {
        this(0, 0, "BLACK", sideLength, '*');
    }

    public Square(int centerX, int centerY, String color, int sideLength) {
        this(centerX, centerY, color, sideLength, '*');
    }

    public Square(int centerX, int centerY, String color,
                  int sideLength, char character) {
        super(centerX, centerY, color);
        setSideLength(sideLength);
        this.character = character;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        if (sideLength <= 0 || sideLength > 50) {
            throw new IllegalArgumentException(
                    "Side length must be between 1 and 50."
            );
        }

        this.sideLength = sideLength;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int computeArea() {
        return sideLength * sideLength;
    }

    public int computePerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < sideLength; row++) {
            for (int col = 0; col < sideLength; col++) {
                if (row == 0 || row == sideLength - 1 ||
                        col == 0 || col == sideLength - 1) {
                    result.append(character).append(' ');
                } else {
                    result.append("  ");
                }
            }

            result.append("\n");
        }

        return result.toString();
    }
}
