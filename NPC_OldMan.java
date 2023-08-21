package Entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = (3 / 2);

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);

	}

	public void setDialogue() {

		dialogues[0] = "Hello, traveler. Who are you?";
		dialogues[1] = "I'm searching for an adventurer \nto help me.";
		dialogues[2] = "Really?! You would be willing to \nhelp?!";
		dialogues[3] = "I haven't even told you what \nit was yet!";
		// dialogues[0] = "Hello, traveler. Who are you?";

	}

	@Override
	public void setAction() {

		actionLockCounter++;

		if (actionLockCounter == 95) {

			Random random = new Random();
			int i = random.nextInt(100) + 1; // pick up number 1-100 (+1 to avoid 0-99)

			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}

			actionLockCounter = 0;

		}
	}

	@Override
	public void speak() {

		// CHARACTER SPECIFIC DIALOGUE can be entered here rather than VVV
		super.speak();
	}
}
