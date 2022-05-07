import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    private BallCanvas canvas;
    public static final Pocket P = new Pocket( 100, 100);

    private static int ballCount = 0;
    static JLabel ballCountLabel = new JLabel("Balls in the pocket: 0");
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");


        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<8; i++){
                    Ball b = new Ball(canvas);
                    canvas.add(b);
                    BallThread thread = new BallThread(b);
                    thread.start();
                    System.out.println("Thread name = "
                            + thread.getName());
                }
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(ballCountLabel);
        content.add(buttonPanel, BorderLayout.SOUTH);

    }
    public static void Increment(){
        ballCount++;
        ballCountLabel.setText("Balls in the pocket: " + String.valueOf(ballCount));
    }

}