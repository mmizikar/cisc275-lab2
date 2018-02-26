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
    BufferedImage[][] pics;
    int xloc = 0;
    int yloc = 0;
    int xDirect, yDirect;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500; // change back to 500 maybe
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    int direction = 5;  //update if starting direction changes
    int slope;
    final int EAST = 0;
    final int NORTH = 1;
    final int NORTHEAST = 2;
    final int NORTHWEST = 3;
    final int SOUTH = 4;
    final int SOUTHEAST = 5;
    final int SOUTHWEST = 6;
    final int WEST = 7;
    

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	
    	picNum = (picNum + 1) % frameCount;
    	
    	if(xloc <= 0) { //hits left side of window
    		xDirect = 1;

    		switch(direction) {
    		case(NORTHWEST) :
    			direction = NORTHEAST;
    			break;
    			
    		case(SOUTHWEST) :
    			direction = SOUTHEAST;
    			break;
    			
    		case(WEST):
    			direction = EAST;
    			break;
    		}
    		
    	}
    	else if(xloc + imgWidth >= frameWidth) { // hits right side of window
    		xDirect = -1;
    		
    		switch(direction) {
    		case(NORTHEAST) :
    			direction = NORTHWEST;
    			break;
    			
    		case(SOUTHEAST) :
    			direction = SOUTHWEST;
    			break;
    			
    		case(EAST):
    			direction = WEST;
    			break;
    		}
    		
    	}
    	else if(yloc <= 0) { //hits top of window
    		yDirect = 1;
    		switch(direction) {
    		case(NORTHWEST):
    			direction = SOUTHWEST;
    			break;
    			
    		case(NORTHEAST):
    			direction = SOUTHEAST;
    			break;
    			
    		case(NORTH):
    			direction = SOUTH;
    			break;
    		
    		}
    	}
    	else if(yloc + imgHeight >= frameHeight) { //hits bottom of window
    		yDirect = -1;
    		switch(direction) {
    		case(SOUTHWEST):
    			direction = NORTHWEST;
    			break;
    			
    		case(SOUTHEAST):
    			direction = NORTHEAST;
    			break;
    			
    		case(SOUTH):
    			direction = NORTH;
    			break;
    		}

    	}
    	
    	// x, y location is from the lop left of the image
    	g.drawImage(pics[direction][picNum], xloc += (xIncr * xDirect), yloc += (yIncr * yDirect), Color.gray, this);
    	//System.out.println((xloc + imgWidth )+ " " + (yloc + imgHeight));
    	//System.out.println(direction);

    		
    	
    		
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
    	
    	BufferedImage[] img = new BufferedImage[8];
    	img[0] = createImage("images/orc/orc_forward_east.png");
    	img[1] = createImage("images/orc/orc_forward_north.png");
    	img[2] = createImage("images/orc/orc_forward_northeast.png");
    	img[3] = createImage("images/orc/orc_forward_northwest.png");
    	img[4] = createImage("images/orc/orc_forward_south.png");
    	img[5] = createImage("images/orc/orc_forward_southeast.png");
    	img[6] = createImage("images/orc/orc_forward_southwest.png");
    	img[7] = createImage("images/orc/orc_forward_west.png");
    	pics = new BufferedImage[8][10];
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < frameCount; j++)
    			pics[i][j] = img[i].getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
    	}
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    	// Changed pics to a 2D array in order to store the different directions for the images
    }  
    
    //Read image from file and return
    private BufferedImage createImage(String file){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(file));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    	// Changed the parameters of the method to allow loading of other strings
    }
}