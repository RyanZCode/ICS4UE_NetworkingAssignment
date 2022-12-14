package agarIOAssignment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

//A simple class to represent a ball in the game
public class Cell {
    private int x;
    private int y;
    private int radius;
    private double xVelocity;
    private double yVelocity;
    private Color color;
    private double speed;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    public Cell(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.speed = 5;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY() {
    	this.y = y;
    }
    
    // Update the ball's position based on its velocity
//    public void update() {
//        x += xVelocity;
//        y += yVelocity;
//    }

    // Check if this ball intersects with another ball
    public boolean intersects(Cell other) {
        double distance = Math.sqrt(Math.pow(other.getX() - x, 2) + (Math.pow(other.getY() - y, 2)));
        if (distance <= (this.radius - other.getRadius())) {
        	return true;
        }
        return false;
    }

    // Draw the ball on the screen
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        this.xVelocity = 0;
        this.yVelocity = 0;
        if (up) {
        	this.y -= speed;
        }
        if (down) {
        	this.y += speed;
        }
        if (right) {
        	this.x += speed;
        }
        if (left) {
        	this.x -= speed;
        }
        if (up && down) {
        	this.yVelocity = 0;
        }
        if (left && right) {
        	this.xVelocity = 0;
        }
        if (this.x - radius < 0) {
        	this.xVelocity = 0;
        	this.x = radius;
        } else if (this.x + radius > Const.WIDTH) {
        	this.xVelocity = 0;
        	this.x = Const.WIDTH - radius;
        }
        if (this.y - radius < 0) {
        	this.yVelocity = 0;
        	this.y = radius;
        } else if (this.y + radius > Const.HEIGHT) {
        	this.yVelocity = 0;
        	this.y = Const.HEIGHT - radius;
        }
    }
    
    public void left(boolean bool) {
    	this.left = bool;
    }
    
    public void right(boolean bool) {
    	this.right = bool;
    }
    
    public void up(boolean bool) {
    	this.up = bool;
    }
    
    public void down(boolean bool) {
    	this.down = bool;
    }
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        if (this.radius % 10 == 0) {
        	if (speed > 1) {
        		speed--;
        	}
        }
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
}