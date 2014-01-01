package graphic;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

@SuppressWarnings("serial")
public class Graphic extends JPanel {
	ArrayList<Cube> cubes = new ArrayList<Cube>();
	boolean maxReached = false;
	
	public Graphic() {
		setBackground(Color.WHITE);
		init();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//TODO: paint things
		
		
		g.setColor(Color.BLACK);
		for(Cube temp : cubes){
			g2.fill(temp.draw());
		}
	}
	
	public void init() {
		cubes.add(new Cube(20, 20, 100, 100, 2, 5));
	}
	
	public void update() {
		int tempVelX = 0, tempVelY = 0, tempPosX = 0, tempPosY = 0, tempWidth = 0, tempHeight = 0, tempIndex = 0;
		if(!cubes.isEmpty()) {
			for(Cube c : cubes){		
				c.Move();
				if(c.getPosX() <= MouseInfo.getPointerInfo().getLocation().getX() && c.getPosX() + c.getWidth() >= MouseInfo.getPointerInfo().getLocation().getX()) {
					if(c.getPosY() <= MouseInfo.getPointerInfo().getLocation().getY() && c.getPosY() + c.getHeight() >= MouseInfo.getPointerInfo().getLocation().getY()) {
						System.out.println("Mouse inside cube");
						
						tempVelX = c.getVelX();
						tempVelY = c.getVelY();
						tempPosX = c.getPosX();
						tempPosY = c.getPosY();
						tempWidth = c.getWidth();
						tempHeight = c.getHeight();
						
						c.isSplit = true;
					}
				}
			}
		}
		for(Cube c : cubes) {
			if(c.isSplit) {
				tempIndex = cubes.indexOf(c);
			}
		}
		if(cubes.size() >= 10) {
			maxReached = true;
		}
		if(cubes.get(tempIndex).isSplit){
			if(!maxReached) {
				cubes.remove(tempIndex);
				cubes.add(new Cube(tempPosX + 20, tempPosY + 20, tempWidth - (tempWidth/2), tempHeight - (tempHeight/2), tempVelX, tempVelY));
				cubes.add(new Cube(tempPosX - 20, tempPosY - 20, tempWidth - (tempWidth/2), tempHeight - (tempHeight/2), -tempVelX, -tempVelY));
			} else {
				cubes.remove(tempIndex);
			}
		}
		if(cubes.size()<= 0) {
			System.out.println("You won!");
			System.exit(0);
		}
			
		//System.out.println("X: " + MouseInfo.getPointerInfo().getLocation().getX() + " Y: " + MouseInfo.getPointerInfo().getLocation().getY());
		this.repaint();
		//System.out.println(Frame.getScreenHeight() + " , " + Frame.getScreenWidth());
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
}
