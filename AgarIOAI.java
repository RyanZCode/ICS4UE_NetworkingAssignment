package agarIOAssignment;

	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;

	import javax.swing.JFrame;
	import javax.swing.JPanel;

	// The main class for the game
	public class AgarIOAI extends JPanel implements KeyListener {
	    // The width and height of the game window
	    private static final int WIDTH = 800;
	    private static final int HEIGHT = 600;

	    // The radius of the player's ball
	    private static final int PLAYER_RADIUS = 20;

	    // The maximum number of food particles on the screen at any given time
	    private static final int MAX_FOOD = 100;

	    // The player's ball
	    private Ball player;

	    // The list of food particles on the screen
	    private List<Ball> food;

	    // The random number generator
	    private Random random;

	    public AgarIOAI() {
	        // Initialize the player's ball
	        player = new Ball(WIDTH / 2, HEIGHT / 2, PLAYER_RADIUS, Color.BLUE);

	        // Initialize the list of food particles
	        food = new ArrayList<>();

	        
	     // Initialize the random number generator
	        random = new Random();
	        
	        // Add some initial food particles to the screen
	        for (int i = 0; i < MAX_FOOD; i++) {
	            food.add(generateRandomFood());
	        }

	        

	        // Add a key listener to the game to respond to user input
	        addKeyListener(this);
	        setFocusable(true);
	    }

	    // Generate a random food particle
	    private Ball generateRandomFood() {
	        // Generate random x and y coordinates for the food particle
	        int x = random.nextInt(WIDTH);
	        int y = random.nextInt(HEIGHT);

	        // Generate a random radius for the food particle
	        int radius = random.nextInt(10) + 5;

	        // Generate a random color for the food particle
	        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

	        return new Ball(x, y, radius, color);
	    }

	    // Update the game state
	    public void update() {
	        // Move the player's ball based on user input
	        player.update();

	        // Check if the player's ball has eaten any of the food particles
	        for (int i = 0; i < food.size(); i++) {
	            Ball f = food.get(i);
	            if (player.intersects(f)) {
	                // If the player's ball intersects with a food particle,
	                // remove the food particle and increase the player's radius
	                food.remove(i);
	                player.setRadius(player.getRadius() + 1);
	            }
	            // If there are fewer than the maximum number of food particles on the screen,
	            // add a new random food particle
	            if (food.size() < MAX_FOOD) {
	                food.add(generateRandomFood());
	            }
	        }
	    }

	        @Override
	        public void paint(Graphics g) {
	            super.paint(g);

	            // Draw the player's ball
	            player.draw(g);

	            // Draw the food particles
	            for (Ball f : food) {
	                f.draw(g);
	            }
	        }

	        @Override
	        public void keyTyped(KeyEvent e) {
	            // This method is not used in the game
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            // Handle user input to move the player's ball
	            int keyCode = e.getKeyCode();
	            if (keyCode == KeyEvent.VK_LEFT) {
	                player.left(true);
	            } if (keyCode == KeyEvent.VK_RIGHT) {
	                player.right(true);
	            } if (keyCode == KeyEvent.VK_UP) {
	                player.up(true);
	            } if (keyCode == KeyEvent.VK_DOWN) {
	                player.down(true);
	            }
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	            // When the user releases a key, stop moving the player's ball
	        	int keyCode = e.getKeyCode();
	            if (keyCode == KeyEvent.VK_LEFT) {
	            	player.left(false);
	            } else if (keyCode == KeyEvent.VK_RIGHT) {
	            	player.right(false);
	            } else if (keyCode == KeyEvent.VK_UP) {
	            	player.up(false);
	            } else if (keyCode == KeyEvent.VK_DOWN) {
	            	player.down(false);
	            }
	        }

	        public static void main(String[] args) {
	            // Create the game window
	            JFrame frame = new JFrame("Agario Game");
	            frame.setSize(WIDTH, HEIGHT);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setResizable(false);

	            // Add the game panel to the window
	            AgarIOAI game = new AgarIOAI();
	            frame.add(game);

	            // Display the window
	            frame.setVisible(true);

	            // Start the game loop
	            while (true) {
	                // Update the game state
	                game.update();

	                // Repaint the screen
	                game.repaint();

	                // Sleep for a short time to give the game a smooth animation
	                try {
	                    Thread.sleep(10);
	                } catch (InterruptedException e) {
	                    // Ignore the exception
	                }
	            }
	        }
}
