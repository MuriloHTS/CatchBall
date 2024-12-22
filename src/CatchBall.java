import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CatchBall extends JPanel implements ActionListener, KeyListener {
    private int bolaEixoX = 300, bolaEixoY = 0, diametroDaBola = 20;
    private int baldeEixoX = 260, baldeEixoY = 500, larguraDoBalde = 70, alturaDoBalde = 90;
    private int velocidadeDaBola = 2;
    private int pontuacao = 0;
    private Timer timer;

    public CatchBall() {
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.fillOval(bolaEixoX, bolaEixoY, diametroDaBola, diametroDaBola);

        g.setColor(Color.BLUE);
        g.fillRect(baldeEixoX, baldeEixoY, larguraDoBalde, alturaDoBalde);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + pontuacao, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bolaEixoY += velocidadeDaBola;

        if (bolaEixoY + diametroDaBola >= baldeEixoY && bolaEixoX + diametroDaBola >= baldeEixoX && bolaEixoX <= baldeEixoX + larguraDoBalde) {
            pontuacao++;
            bolaEixoY = 0;
            bolaEixoX = (int) (Math.random() * (getWidth() - diametroDaBola));
        }

        if (bolaEixoY > getHeight()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Pontuação final: " + pontuacao);
            System.exit(0);
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT && baldeEixoX > 0) {
            baldeEixoX -= 15;
        }

        if (key == KeyEvent.VK_RIGHT && baldeEixoX < getWidth() - larguraDoBalde) {
            baldeEixoX += 15;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Catch the Ball Game");
        CatchBall game = new CatchBall();

        frame.add(game);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
