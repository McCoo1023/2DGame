package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	final int origTileSize = 16; // 16 x 16 tile
	final int scale = 3; // sets the scale of origTileSize (makes 16x16 look like 48x48)
	public final int tileSize = origTileSize * scale; // 48x48 tiles

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;

	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide these two set the size of our game screen
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

	// WORLD SETTINGS

	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	// FPS

	int FPS = 60;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this); // initiate collision checker
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread; // THREAD used for game loop

	// entity and object
	public Player player = new Player(this, keyH); // allows player to use key handler in this class
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();

	// GAME STATE
	public int gameState; // different drawings/input
	public final int playState = 1; // enter could be combat key
	public final int pauseState = 2; // enter could be select key
	public final int dialogueState = 3;
	public final int titleState = 0;
	public final int characterState = 4;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}

	public void setupGame() {

		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		// playMusic(0);
		gameState = titleState;

	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime(); // RUN is our game loop, system is using nano seconds divided into
											// milliseconds to keep us at 60 FPS.
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
				// System.out.println("FPS:" + drawCount);
				// System.out.println("x:" + player.worldX + "y:" + player.worldY); DEBUG
				drawCount = 0;
				timer = 0;

			}
		}
	}

	public void update() {

		if (gameState == playState) {
			player.update();
			// NPC

			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}

			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					if (monster[i].alive == true && monster[i].dying == false) {
						monster[i].update();
					}
					if (monster[i].alive == false) {
						monster[i] = null;
					}
				}
			}
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					if (projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if (projectileList.get(i).alive == false) {
						projectileList.remove(i);
					}
				}
			}

		}
		if (gameState == pauseState) {
			// nothing yet
		}

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g; // painting our program

		// DEBUG
		long drawStart = 0;

		if (keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}

		// TITLE SCREEN
		if (gameState == titleState) {

			ui.draw(g2);
		}

		// OTHERS

		else {

			// TILE
			tileM.draw(g2);

			// ADD ENTITIES TO THE LIST
			entityList.add(player);

			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					entityList.add(npc[i]);
				}
			}

			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					entityList.add(obj[i]);
				}
			}

			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}

			// SORT
			Collections.sort(entityList, new Comparator<Entity>() {
				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});

			// draw entities

			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}

			// empty entities list

			entityList.clear();

			// UI
			ui.draw(g2);

		}

		// debug
		if (keyH.checkDrawTime == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw time: " + passed, 10, 400);
			System.out.println("Draw time:" + passed);
		}
		
		// Display current column and row for debugging
	    int currentColumn = player.worldX / tileSize;
	    int currentRow = player.worldY / tileSize;
	    g2.setColor(Color.white);
	    g2.drawString("Column: " + currentColumn + ", Row: " + currentRow, 10, 20);

		g2.dispose();

	}

	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {
		music.stop();
	}

	public void playSE(int i) {

		se.setFile(i);
		se.play();

	}

}
