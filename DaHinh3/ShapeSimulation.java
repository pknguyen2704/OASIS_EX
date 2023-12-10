import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

class Shape {
    protected int x, y, width, height;
    protected Color color;
    protected int velocityX, velocityY;

    public Shape(int x, int y, int width, int height, Color color, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

    public void draw(Graphics g) {
        g.setColor(color);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

class Circle extends Shape {
    public Circle(int x, int y, int diameter, Color color, int velocityX, int velocityY) {
        super(x, y, diameter, diameter, color, velocityX, velocityY);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillOval(x, y, width, height);
    }
}

class RectangleShape extends Shape {
    public RectangleShape(int x, int y, int width, int height, Color color, int velocityX, int velocityY) {
        super(x, y, width, height, color, velocityX, velocityY);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillRect(x, y, width, height);
    }
}

class DrawingPanel extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();

    public DrawingPanel() {
        setFocusable(true);
        addKeyListener(new ShapeKeyListener());
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveShapes();
                checkCollisions();
                repaint();
            }
        });
        timer.start();
    }

    private void moveShapes() {
        for (Shape shape : shapes) {
            shape.move();
        }
    }

    private void checkCollisions() {
        for (Shape shape : shapes) {
            if (shape.x < 0 || shape.x + shape.width >= getWidth()) {
                shape.velocityX = -shape.velocityX;
            }
            if (shape.y < 0 || shape.y + shape.height >= getHeight()) {
                shape.velocityY = -shape.velocityY;
            }

            for (Shape otherShape : shapes) {
                if (shape != otherShape && shape.getBounds().intersects(otherShape.getBounds())) {
                    shape.velocityX = -shape.velocityX;
                    shape.velocityY = -shape.velocityY;
                }
            }
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    private class ShapeKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_C:
                    addRandomCircle();
                    break;
                case KeyEvent.VK_R:
                    addRandomRectangle();
                    break;
                case KeyEvent.VK_DELETE:
                    if (!shapes.isEmpty()) {
                        removeShape(shapes.get(shapes.size() - 1));
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        private void addRandomCircle() {
            Random random = new Random();
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int diameter = random.nextInt(50) + 20;
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            int velocityX = random.nextInt(5) + 1;
            int velocityY = random.nextInt(5) + 1;
            Circle circle = new Circle(x, y, diameter, color, velocityX, velocityY);
            addShape(circle);
        }

        private void addRandomRectangle() {
            Random random = new Random();
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int width = random.nextInt(50) + 20;
            int height = random.nextInt(50) + 20;
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            int velocityX = random.nextInt(5) + 1;
            int velocityY = random.nextInt(5) + 1;
            RectangleShape rectangle = new RectangleShape(x, y, width, height, color, velocityX, velocityY);
            addShape(rectangle);
        }
    }
}

public class ShapeSimulation {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingPanel drawingPanel = new DrawingPanel();
        frame.add(drawingPanel);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
