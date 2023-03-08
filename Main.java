import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.imageio.*;

public class Main {
    static boolean pixorper=true;
    public static void main(String[] args) {
        final DecimalFormat decfor = new DecimalFormat("0.00");
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 40));
        frame.setSize(screenWidth, screenHeight);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setOpaque(false);
        layeredPane.setBounds(0, 0, screenWidth, screenHeight);
        frame.setContentPane(layeredPane);
        JPanel exitbuttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        exitbuttonPanel.setOpaque(false);
        exitbuttonPanel.setBounds(screenWidth-50, 0, 50, 50);
        JPanel minimisebuttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        minimisebuttonPanel.setOpaque(false);
        minimisebuttonPanel.setBounds(screenWidth-150, 0, 50, 50);
        JPanel resizebuttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5,5));
        resizebuttonPanel.setOpaque(false);
        resizebuttonPanel.setBounds(screenWidth-100, 0, 50, 50);
        JButton piopeButton=new JButton("Pixels");
        piopeButton.setFocusable(false);
        piopeButton.setBackground(new Color(20,20,20));
        piopeButton.setForeground(new Color(200,200,200));
        piopeButton.setBounds(50,50,100,30);
        piopeButton.setFocusPainted(false);
        layeredPane.add(piopeButton, JLayeredPane.PALETTE_LAYER);
        piopeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(piopeButton.getText().equals("Pixels")) {
                    pixorper=false;
                    piopeButton.setText("Percentage");
                }else {
                    pixorper = true;
                    piopeButton.setText("Pixels");
                }
            }
        });
        try {
            File imageFilee = new File("C:\\Users\\gabri\\IdeaProjects\\coordgiver\\src\\exit-button.png");
            Image imagee = ImageIO.read(imageFilee);
            int scaledWidth = (int) (imagee.getWidth(null)*0.25 );
            int scaledHeight = (int) (imagee.getHeight(null)*0.25);
            ImageIcon closeIcon = new ImageIcon(imagee.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH));
            JButton closeButton = new JButton(closeIcon);
            closeButton.setOpaque(false);
            closeButton.setContentAreaFilled(false);
            closeButton.setBorderPainted(false);
            closeButton.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            exitbuttonPanel.add(closeButton);
            layeredPane.add(exitbuttonPanel, JLayeredPane.DEFAULT_LAYER);
            File imageFilem = new File("C:\\Users\\gabri\\IdeaProjects\\coordgiver\\src\\minimise-button.png");
            Image imagem = ImageIO.read(imageFilem);
            ImageIcon minimiseIcon = new ImageIcon(imagem.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH));
            JButton minimiseButton = new JButton(minimiseIcon);
            minimiseButton.setOpaque(false);
            minimiseButton.setContentAreaFilled(false);
            minimiseButton.setBorderPainted(false);
            minimiseButton.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
            minimiseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setExtendedState(Frame.ICONIFIED);
                }
            });
            minimisebuttonPanel.add(minimiseButton);
            layeredPane.add(minimisebuttonPanel, JLayeredPane.PALETTE_LAYER);
            File imageFiler = new File("C:\\Users\\gabri\\IdeaProjects\\coordgiver\\src\\resize-button.png");
            Image imager = ImageIO.read(imageFiler);
            ImageIcon resizeIcon = new ImageIcon(imager.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH));
            JButton resizeButton = new JButton(resizeIcon);
            resizeButton.setOpaque(false);
            resizeButton.setContentAreaFilled(false);
            resizeButton.setBorderPainted(false);
            resizeButton.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
            resizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setResizable(true);
                }
            });
            resizebuttonPanel.add(resizeButton);
            layeredPane.add(resizebuttonPanel, JLayeredPane.PALETTE_LAYER);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JLabel label = new JLabel();
        label.setForeground(Color.WHITE);
        layeredPane.add(label, JLayeredPane.PALETTE_LAYER);
        layeredPane.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                if (pixorper) {
                    label.setText("      X: " + decfor.format(p.x * 100.0 / frame.getWidth()) + "%, Y: " + decfor.format(p.y * 100.0 / frame.getHeight()) + '%');
                } else
                    label.setText("      X: " + p.x + ", Y: " + p.y);

                if (p.x > frame.getWidth() / 2){
                    if (p.y > frame.getHeight() / 2) {
                        label.setBounds(p.x - label.getPreferredSize().width, p.y - label.getPreferredSize().height, label.getPreferredSize().width, label.getPreferredSize().height);
                    } else
                        label.setBounds(p.x - label.getPreferredSize().width, p.y, label.getPreferredSize().width, label.getPreferredSize().height);
            }
                else
                if (p.y > frame.getHeight() / 2) {
                    label.setBounds(p.x , p.y - label.getPreferredSize().height, label.getPreferredSize().width, label.getPreferredSize().height);
                } else
                    label.setBounds(p.x, p.y, label.getPreferredSize().width, label.getPreferredSize().height);
            }
        });
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }
}