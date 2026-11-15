package LE6_1_Drawable_Shape;

public abstract class DrawableShape {
    private int centerX;
    private int centerY;
    private String color;

    public DrawableShape() {
        this(0, 0, "BLACK");
    }

    public DrawableShape(int centerX, int centerY, String color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public String getColor() {
        return color;
    }

    public void move(int dx, int dy) {
        centerX += dx;
        centerY += dy;
    }
}
