//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
import java.awt.Color;
import java.util.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel {

    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[] pics;
    int xloc = 0;
    int yloc = 0;
    int xDirect, yDirect;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500; // change back to 500 maybe
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	
    	picNum = (picNum + 1) % frameCount;
    	/*if(xloc <= (frameWidth-imgWidth) || yloc <= (frameWidth-imgHeight)) {
    		g.drawImage(pics[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
    	}
    		g.drawImage(pics[picNum], xloc, yloc, Color.blue,this);
    	*/
    	// Logic for x
    	//g.drawImage(pics[picNum], xloc += (xIncr * xDirect), yloc += (yIncr * yDirect), Color.red, this);

    	if(xloc <= 0) { //turn right
    		xDirect = 1;
    	}
    	else if(xloc + imgWidth >= frameWidth) { //t
    		xDirect = -1;
    	}
    	
    	//Logic for y
    	if(yloc <= 0) {
    		yDirect = 1;
    	}
    	else if(yloc + imgHeight >= frameHeight) {
    		yDirect = -1;
    	}    	
    	// x, y location is from the lop left of the image
    	g.drawImage(pics[picNum], xloc += (xIncr * xDirect), yloc += (yIncr * yDirect), Color.gray, this);
    	System.out.println(xloc + " " + yloc);

    		
    	
    		
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

    //Constructor: get image, segment and store in array
    public Animation(){
    	//ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    	//images.add(createImage("orc_jump_south.png"));
    	BufferedImage img = createImage("orc_forward_northwest.png");
    	pics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		pics[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
    
    //Read image from file and return
    private BufferedImage createImage(String file){
    	BufferedImage bufferedImage;
    	String filepath = "images/orc/";
    	try {
    		bufferedImage = ImageIO.read(new File(filepath + file));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
}