import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class StarBridgeOverlay {

    int xDrag;
    int yDrag;
    int xPress;
    int yPress;

    public static void main(String[] args) {
        new StarBridgeOverlay();
    }

    public StarBridgeOverlay() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

//                System.out.println("Blah");

                MyFrame frame = new MyFrame("Arly's Star Bridge Overlay");
                frame.setLayout(new GridLayout(1, 2));
                frame.setUndecorated(true);
                frame.setBackground(new Color(0, 255, 0, 0));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setAlwaysOnTop(true);
                frame.setVisible(true);

                frame.addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        xDrag = e.getX();
                        yDrag = e.getY();

                        JFrame sFrame = (JFrame) e.getSource();
                        sFrame.setLocation(sFrame.getLocation().x + xDrag - xPress,
                                sFrame.getLocation().y + yDrag - yPress);
                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {
                        xPress = e.getX();
                        yPress = e.getY();
                    }
                });
            }
        });
    }

    public class MyFrame extends JFrame {

        private MyPanel panel;

        public MyFrame(String title) {
            super(title);
            panel = new MyPanel();
            add(panel);
        }

    }

    public class MyPanel extends JPanel {

        final int WIDTH = 141;
        final int HEIGHT = 197;
        final int SQUARE_SIZE = 14;


        public MyPanel() {
            setBackground(Color.BLACK);
            setOpaque(false);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, 1));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(new Color(0, 162, 232));

            for (int i = 0; i < HEIGHT / SQUARE_SIZE + 1; i++) {
                g2d.drawLine(0, i * SQUARE_SIZE, WIDTH, i * SQUARE_SIZE);
            }
            for (int j = 0; j < WIDTH / SQUARE_SIZE + 1; j++) {
                g2d.drawLine(j * SQUARE_SIZE, 0, j * SQUARE_SIZE, HEIGHT);
            }

            g2d.dispose();
        }
    }

}