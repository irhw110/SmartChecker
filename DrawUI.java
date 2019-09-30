import javax.swing.*;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class DrawUI {

	JLabel[][] lbl;
	public DrawUI()  {
  
	  lbl = new JLabel[8][8];
  
	  for (int i = 0; i < 8; i++)  {
		for (int j = 0; j < 8; j++)  {
		  lbl[i][j] = new JLabel();
		}
	  }
	}
  
	public JLabel[][] getLabel()  {
	  return lbl;
	}
  
	public void UpdateUI(char[][] draw)  {
	    String filename= "asset/itb.png";
		
		try{
			BufferedImage bf = ImageIO.read(new File(filename));
			ImageIcon icon = new ImageIcon(bf);
			lbl[0][0].setIcon(icon);
	    }
		catch(IOException e){}
	}
}
  