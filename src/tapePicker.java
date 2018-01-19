import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import java.awt.*;

class tapePicker {
    public static void main(String[] args) {
        int i=0,j=0;
        int rgb=0,r=0,g=0,b=0;
        BufferedImage img = null;
        BufferedImage blank = null;
        try {
            img = ImageIO.read(new File("tc4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            blank = ImageIO.read(new File("slate.bmp"));
        }catch (IOException e){
            e.printStackTrace();
        }

        for(i=0;i<img.getHeight();i++){
            for(j=0;j<img.getWidth();j++){
                rgb=img.getRGB(j,i);
                r = (rgb >> 16) & 0xff;
                g = (rgb >> 8) & 0xff;
                b =  rgb & 0xff;
                if(r > 10 && g > 10 && b > 10 ) {
                    if (r > g && r > b) {
                        if (r >= g + b) {
                            blank.setRGB(j, i, new Color(r, g, b, 255).getRGB());
                        }
                    } else if (g > r && g > b) {
                        if (g >= r + b) {
                            blank.setRGB(j, i, new Color(r, g, b, 255).getRGB());
                        }
                    } else if (b > g && b > r) {
                        if (b >= g + r) {
                            blank.setRGB(j, i, new Color(r, g, b, 255).getRGB());
                        }
                    }
                }
            }
        }


        try {
            File outputfile = new File("edited.bmp");
            ImageIO.write(blank, "bmp", outputfile);
        }catch (IOException e){
            System.out.println(e);
        }

        ImageIcon icon = new ImageIcon(blank);
        JLabel label = new JLabel(icon);
        JFrame frame = new JFrame("SuperWindow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label,BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
}
