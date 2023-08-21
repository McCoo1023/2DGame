package Entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Object.OBJ_Shield_Wood;
import Object.OBJ_Sword_Normal;
import Object.OBJ_fireball;
import Object.OBJ_key;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - gp.tileSize / 2;
		screenY = gp.screenHeight / 2 - gp.tileSize / 2;

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y; // recall default values since x and y later change
		solidArea.width = 26;
		solidArea.height = 18;

		// attack area

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}

	public void setDefaultValues() {

		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";

		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		strength = 1; // more strength = more dmg done
		dexterity = 1; // more dexterity = less dmg recieved
		intellect = 1; // more intellect = higher spell dmg
		exp = 0; // guess
		nextLevelExp = 5;
		coin = 0; // broke
		charClass = 0;
		projectile = new OBJ_fireball(gp);
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		attack = getAttack(); // attack value is our strength * attack value of our weapon
		defence = getDefence(); // defence value is our dexterity * defence value of our shield/armor

	}

	public void setItems() {

		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_key(gp));
		inventory.add(new OBJ_key(gp));
		inventory.add(new OBJ_key(gp));
		inventory.add(new OBJ_key(gp));
		inventory.add(new OBJ_key(gp));
		inventory.add(new OBJ_key(gp));
	}

	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}

	public int getDefence() {
		return defence = dexterity * currentShield.defenceValue;
	}

	public void getPlayerImage() {

		/*
		 * up1 = setup("/player/boy_up_1"); up2 = setup("/player/boy_up_2"); down1 =
		 * setup("/player/boy_down_1"); down2 = setup("/player/boy_down_2"); left1 =
		 * setup("/player/boy_left_1"); left2 = setup("/player/boy_left_2"); right1 =
		 * setup("/player/boy_right_1"); right2 = setup("/player/boy_right_2");
		 */

		up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);

	}

	public void getPlayerAttackImage() {

		if (currentWeapon.type == type_sword) {
			attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
		}
		if (currentWeapon.type == type_axe) {
			attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
		}

	}

	@Override
	public void update() {

		if (attacking == true) {
			attacking();
		}

		else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true || keyH.enterPressed == true) {

			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) { // key presses for movement
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			// check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// check npc collision

			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// check monster collision

			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

			contactMonster(monsterIndex);

			// check event

			gp.eHandler.checkEvent();

			// if collision == false \\ player can not move
			if (collisionOn == false && keyH.enterPressed == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}

			if (keyH.enterPressed == true && attackCanceled == false) {
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}

			attackCanceled = false;

			gp.keyH.enterPressed = false;

			spriteCounter++;
			if (spriteCounter > 14) {
				if (spriteNum == 1) { // player image will be updating every 10 frames, while swapping sprites
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

		}

		if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotCounter == 30) {
			// default coordinates direction and user
			projectile.set(worldX, worldY, direction, true, this);

			// add to our array list
			gp.projectileList.add(projectile);

			shotCounter = 0;

			gp.playSE(10);
		}

		// this needs to be outside of key if statement
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}

		if (shotCounter < 30) {
			shotCounter++;
		}

	}

	public void attacking() {

		spriteCounter++;

		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		if (spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;

			// save current position bc attack animation is 1 tile in any direction from
			// player
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			// adjust x/y for attackArea
			switch (direction) {
			case "up":
				worldY -= attackArea.height;
				break;
			case "down":
				worldY += attackArea.height;
				break;
			case "left":
				worldX -= attackArea.width;
				break;
			case "right":
				worldX += attackArea.width;
				break;
			}

			// attack area becomes solid area
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			// check monster collision compared to our new worldX, Y and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack);
			// after we check collision, restore our world X, Y and solidArea
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;

		}
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

	public void pickUpObject(int i) {

		if (i != 999) {

			String text;

			if (inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[i]);
				gp.playSE(1);
				text = "You got a " + gp.obj[i].name + "!";
			} else {
				text = "Your inventory is full!";
			}
			gp.ui.addMessage(text);
			gp.obj[i] = null;
		}
	}

	public void interactNPC(int i) {

		if (gp.keyH.enterPressed == true) {

			if (i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
	}

	public void contactMonster(int i) {

		if (i != 999) {

			if (invincible == false && gp.monster[i].dying == false) {
				gp.playSE(6);
				int damage = gp.monster[i].attack - defence;
				if (damage < 0) {
					damage = 0;
				}
				life -= damage;
				invincible = true;
			}
		}
	}

	public void damageMonster(int i, int attack) {

		if (i != 999) {

			if (gp.monster[i].invincible == false) {

				gp.playSE(5);

				int damage = attack - gp.monster[i].defence;
				if (damage < 0) {
					damage = 0;
				}
				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + " damage done!");
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();

				if (gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
					gp.ui.addMessage("You killed the " + gp.monster[i].name + "!");
					gp.ui.addMessage("Exp + " + gp.monster[i].exp);
					exp += gp.monster[i].exp;
					checkLevelUp();
				}
			}
		}
	}

	public void checkLevelUp() {

		if (exp >= nextLevelExp) {

			level++;
			nextLevelExp = nextLevelExp * 3;
			maxLife += 2;
			if (gp.player.charClass == 1) {
				strength++;
			}
			if (gp.player.charClass == 2) {
				dexterity++;
			}
			if (gp.player.charClass == 3) {
				intellect++;
			}
			attack = getAttack();
			defence = getDefence();
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			if (charClass == 1) {
				gp.ui.currentDialogue = "You are now level: " + level + "!\nYour strength has increased!";
			}
			if (charClass == 2) {
				gp.ui.currentDialogue = "You are now level: " + level + "!\nYour dexterity has increased!";
			}
			if (charClass == 3) {
				gp.ui.currentDialogue = "You are now level: " + level + "!\nYour intellect has increased!";
			}
		}
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndex();
		if (itemIndex < inventory.size()) {

			Entity selectedItem = inventory.get(itemIndex);

			if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if (selectedItem.type == type_shield) {
				currentShield = selectedItem;
				defence = getDefence();

			}
			if (selectedItem.type == type_consumable) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		/*
		 * g2.setColor(Color.white); g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		 */

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch (direction) {
		case "up":
			if (attacking == false) {
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}
			}
			if (attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if (spriteNum == 1) {
					image = attackUp1;
				}
				if (spriteNum == 2) {
					image = attackUp2;
				}
			}
			break;
		case "down":
			if (attacking == false) {
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}
			}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackDown1;
				}
				if (spriteNum == 2) {
					image = attackDown2;
				}
			}
			break;
		case "left":
			if (attacking == false) {
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
			}
			if (attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if (spriteNum == 1) {
					image = attackLeft1;
				}
				if (spriteNum == 2) {
					image = attackLeft2;
				}
			}
			break;
		case "right":
			if (attacking == false) {
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
			}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackRight1;
				}
				if (spriteNum == 2) {
					image = attackRight2;
				}
			}
			break;
		}

		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.45f));
		}

		g2.drawImage(image, tempScreenX, tempScreenY, null);

		// reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// debug

//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible counter = " + invincibleCounter, 10, 400);

	}

}
