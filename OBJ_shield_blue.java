package Object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_shield_blue extends Entity {

	public OBJ_shield_blue(GamePanel gp) {
		super(gp);

		type = type_shield;
		name = "Blue Shield";
		down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
		defenceValue = 3/2;
		description = "[" + name + "]\nA reliable and \nblue shield.";
	}
}
