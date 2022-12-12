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
	public class AgarIOClient extends JPanel implements KeyListener {
	    // The player's ball
	    private Cell player;

	    // The list of food particles on the screen
	    private List<Cell> food;

	    // The random number generator
	    private Random random;

	    public AgarIOClient() {
	        // Initialize the player's ball
	        player = new Cell(Const.WIDTH / 2, Const.HEIGHT / 2, Const.STARTING_RADIUS, Color.BLUE);

	        // Initialize the list of food particles
	        food = new ArrayList<>();
	        
	     // Initialize the random number generator
	        random = new Random();
	        
	        // Add some initial food particles to the screen
	        for (int i = 0; i < Const.MAX_FOOD; i++) {
	            food.add(generateRandomFood());
	        }

	        // Add a key listener to the game to respond to user input
	        addKeyListener(this);
	        setFocusable(true);
	    }

	    // Generate a random food particle
	    private Cell generateRandomFood() {
	        // Generate random x and y coordinates for the food particle
	        int x = random.nextInt(Const.WIDTH);
	        int y = random.nextInt(Const.HEIGHT);

	        // Generate a random radius for the food particle
	        int radius = random.nextInt(10) + 5;

	        // Generate a random color for the food particle
	        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

	        return new Cell(x, y, radius, color);
	    }

	    // Update the game state
	    public void update() {
	        // Move the player's ball based on user input
	        //player.update();

	        // Check if the player's ball has eaten any of the food particles
	        for (int i = 0; i < food.size(); i++) {
	            Cell f = food.get(i);
	            if (player.intersects(f) && player.getRadius() < Const.MAX_RADIUS) {
	                // If the player's ball intersects with a food particle,
	                // remove the food particle and increase the player's radius
	                food.remove(i);
	                player.setRadius(player.getRadius() + 1);
	            }
	            // If there are fewer than the maximum number of food particles on the screen,
	            // add a new random food particle
	            if (food.size() < Const.MAX_FOOD) {
	                food.add(generateRandomFood());
	            }
	        }
	    }

	        @Override
	        public void paint(Graphics g) {
	            super.paint(g);

	            
	            // Draw the food particles
	            for (Cell f : food) {
	                f.draw(g);
	            }
	            
	         // Draw the player's ball
	            player.draw(g);
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
	            frame.setSize(Const.WIDTH, Const.HEIGHT);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setResizable(true);

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
	                    Thread.sleep(Const.FRAME_PERIOD);
	                } catch (InterruptedException e) {
	                    // Ignore the exception
	                }
	            }
	        }
}
