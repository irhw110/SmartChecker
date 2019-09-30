import javax.swing.*;

import javafx.scene.image.Image;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;


public class UI{  
    JFrame f;
    JButton[][] arr; 
    String white_box, black_box, BPawn_AtWhiteBox, BKing_AtWhiteBox,WPawn_AtBlackBox, WKing_AtBlackBox; 
    public JButton setSize(JButton jb, int x){
        ImageIcon icon;
        if(x == 1) { //white
            icon = new ImageIcon("asset/white-box.png");   
        } else {
            icon = new ImageIcon("asset/black-box.png");    
        }
        try {
            java.awt.Image img = icon.getImage();
            java.awt.Image resizedImage = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            jb.setIcon(new ImageIcon(resizedImage));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return jb;
    }
    
    UI(){  
        f=new JFrame();
        arr = new JButton[8][8];
        
        //initiate all of image
        white_box = "asset/white-box.png"; //type 0
        black_box = "asset/black-box.png"; //type 5
        BPawn_AtWhiteBox = "asset/BPawn-AtWhiteBox.png"; //type 1
        BKing_AtWhiteBox = "asset/BKing-AtWhiteBox.png"; //type 2
        WPawn_AtBlackBox = "asset/WPawn-AtBlackBox.png"; //type 3
        WKing_AtBlackBox = "asset/WKing-AtBlackBox.png"; //type 4
        
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                arr[i][j] = new JButton();
            }    
        }
    }  

    public void updateUI(Board b){
        BPawn_AtWhiteBox = "asset/YPawn-AtWhiteBox.png"; //type 1
        BKing_AtWhiteBox = "asset/YKing-AtWhiteBox.png"; //type 2
        WPawn_AtBlackBox = "asset/GPawn-AtWhiteBox.png"; //type 3
        WKing_AtBlackBox = "asset/GKing-AtWhiteBox.png"; //type 4

        for (int i=7; i>=0; i--){
            for (int j=0; j<8; j++){
                //at the beginning must be assigned to enter the try catch
                ImageIcon icon = new ImageIcon(white_box);
                if(b.getPawn(i, j) == 0){
                    //ada yang item dan putih
                    if(((i%2 == 0) && (j%2 ==0)) || ((i%2 == 1) && (j%2 ==1))){
                        icon = new ImageIcon(white_box);
                    } else{
                        icon = new ImageIcon(black_box);
                    }
                    
                } else if (b.getPawn(i, j) == 1) {
                    icon = new ImageIcon(BPawn_AtWhiteBox);
                } else if (b.getPawn(i, j) == 2){
                    icon = new ImageIcon(BKing_AtWhiteBox);
                } else if (b.getPawn(i, j) == 3){
                    icon = new ImageIcon(WPawn_AtBlackBox);
                } else if (b.getPawn(i, j) == 4){
                    icon = new ImageIcon(WKing_AtBlackBox);
                }
                try {
                    java.awt.Image img = icon.getImage();
                    java.awt.Image resizedImage = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                    arr[i][j].setIcon(new ImageIcon(resizedImage));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                this.f.add(arr[i][j]);
            }    
        }

        f.setLayout(new GridLayout(8,8));  
        //setting grid layout of 3 rows and 3 columns  
      
        f.setSize(800,800);  
        f.setVisible(true); 
    }

    public static void main(String[] args) {  
        UI ui = new UI();
        Board b = new Board();  
        b.showBoard();
        ui.updateUI(b);
    }  
}  