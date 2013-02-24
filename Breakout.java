/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		breakoutSetup();
		breakoutGame();
	}
	
	/** Runs all of the setup programs; building the board, the bricks and the paddle. */
	private void breakoutSetup() {
		boardSetup();
		bricksSetup();
		paddleSetup();
		waitForClick();
	}
	
	/** Builds the board */
	private void boardSetup() {
		add(new GRect(0, 0, WIDTH, HEIGHT));
	}
	
	/** Builds the bricks between y = 70 and y = 70 + 36 + 80 = 186 */
	private void bricksSetup() {
		int xLocation;
		int yLocation;
		GRect currentBrick;
		for(int i = 1; i <= NBRICK_ROWS; i++) {
			for(int j = 1; j <= NBRICKS_PER_ROW; j++) {
				xLocation = (((BRICK_WIDTH + BRICK_SEP) * (j - 1)) + 2);
				yLocation = (((BRICK_HEIGHT + 4) * (i - 1)) + 70);
				currentBrick = new GRect(xLocation, yLocation, BRICK_WIDTH, BRICK_HEIGHT);
				if (((i + 1) / 2) == 1) {
					currentBrick.setColor(Color.RED);
				}
				else if (((i + 1) / 2) == 2) {
					currentBrick.setColor(Color.ORANGE);
				}
				else if (((i + 1) / 2) == 3) {
					currentBrick.setColor(Color.YELLOW);
				}
				else if (((i + 1) / 2) == 4) {
					currentBrick.setColor(Color.GREEN);
				}
				else if (((i + 1) / 2) == 5) {
					currentBrick.setColor(Color.CYAN);
				}
				currentBrick.setFilled(true);
				add(currentBrick);
			}
		}
	}
	
	/** Builds the paddle @ (((WIDTH/2) - (PADDLE_WIDTH/2) , HEIGHT - 30) */
	private void paddleSetup() {
		paddle = new GRect(((WIDTH - PADDLE_WIDTH) / 2), (HEIGHT - PADDLE_Y_OFFSET), PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	/** The game */
	private void breakoutGame() {
		if (rgen.nextBoolean(0.5)) ballXVelocity *= - 1;
		add(ball, ((WIDTH / 2) - BALL_RADIUS), ((HEIGHT / 2) - BALL_RADIUS));
		ball.setFilled(true);
		while (true) {
			ballMotion();
		}
	}
	
	private void ballMotion() {
		ball.move(ballXVelocity, ballYVelocity);
		if ((ball.getX() <= 0) || ((ball.getX() + (2 * BALL_RADIUS)) >= WIDTH)) {
			ballXVelocity *= -1;
		}
		if (((ball.getY() <= 0) || ((ball.getY() + (2 * BALL_RADIUS)) >= HEIGHT)) || checkForCollisio) {
			ballYVelocity *= -1;
		}
		pause(10);
	}
	
	private boolean checkForCollisionsX(GOval oval, int radius) {
		if ((getElementAt(oval.getX(), oval.getY() + (radius / 2)) != null) || ((getElementAt(oval.getX(), oval.getY() + (3 * radius / 2))) != null) || ((getElementAt(oval.getX() + (2 * radius), oval.getY() + (radius / 2)) != null) || (getElementAt(oval.getX() + (2 * radius), oval.getY() + (3 * radius / 2)) != null))) {
			
		}
	}
	
	private boolean checkForCollisionsY(GOval oval) {
	
	/** The paddle object */
	private GRect paddle;
	
	/** The ball object */
	GOval ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
	
	/** The random number generator */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** Velocity variables */
	int ballXVelocity = rgen.nextInt(1,3);
	int ballYVelocity = rgen.nextInt(1,3);
}
