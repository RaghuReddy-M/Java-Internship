import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        add(new GamePanel());
        setTitle("Snake Game");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int TILE_SIZE = 10;
    private final int[] x = new int[100];
    private final int[] y = new int[100];
    private int snakeLength = 3;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = true;
    private Timer timer;
    private Random random = new Random();

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> direction = 'L';
                    case KeyEvent.VK_RIGHT -> direction = 'R';
                    case KeyEvent.VK_UP -> direction = 'U';
                    case KeyEvent.VK_DOWN -> direction = 'D';
                }
            }
        });
        spawnApple();
        timer = new Timer(150, this);
        timer.start();
    }

    private void spawnApple() {
        appleX = random.nextInt(30) * TILE_SIZE;
        appleY = random.nextInt(30) * TILE_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            g.setColor(Color.RED);
            g.fillRect(appleX, appleY, TILE_SIZE, TILE_SIZE);
            g.setColor(Color.GREEN);
            for (int i = 0; i < snakeLength; i++) {
                g.fillRect(x[i], y[i], TILE_SIZE, TILE_SIZE);
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Game Over", getWidth() / 2 - 30, getHeight() / 2);
        }
    }

    private void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U' -> y[0] -= TILE_SIZE;
            case 'D' -> y[0] += TILE_SIZE;
            case 'L' -> x[0] -= TILE_SIZE;
            case 'R' -> x[0] += TILE_SIZE;
        }
    }

    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            snakeLength++;
            spawnApple();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
        }
        repaint();
    }
}
