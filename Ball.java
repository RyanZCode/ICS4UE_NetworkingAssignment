package agarIOAssignment;

import java.awt.Color;
import java.awt.Graphics;

//A simple class to represent a ball in the game
public class Ball {
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

    public Ball(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.speed = 2;
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
    public void update() {
        x += xVelocity;
        y += yVelocity;
    }

    // Check if this ball intersects with another ball
    public boolean intersects(Ball other) {
        int dx = x - other.x;
        int dy = y - other.y;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance < radius + other.radius;
    }

    // Draw the ball on the screen
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        this.xVelocity = 0;
        this.yVelocity = 0;
        if (up) {
        	this.yVelocity = -speed;
        }
        if (down) {
        	this.yVelocity = speed;
        }
        if (right) {
        	this.xVelocity = speed;
        }
        if (left) {
        	this.xVelocity = -speed;
        }
        if (up && down) {
        	this.yVelocity = 0;
        }
        if (left && right) {
        	this.xVelocity = 0;
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
    	System.out.println(down);
    }
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        if (this.radius % 10 == 0) {
        	if (speed > 0.5) {
        		speed = Math.round((speed - 0.1) * 10) / 10.0;
        		System.out.println("new speed is " + speed);
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