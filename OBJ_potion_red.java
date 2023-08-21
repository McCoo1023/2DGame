package Object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_potion_red extends Entity {
	
	GamePanel gp;

	int value = 5;

	public OBJ_potion_red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = "Red Potion";
		down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nHeals your life by " + value + ".\nMay be useful in a pinch!";
	}
	
	public void use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the " + name +"!\nYour life has been recovered \nby " + value + "!";
		entity.life+=value;
		if (gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(2);
	}
	
	

}
