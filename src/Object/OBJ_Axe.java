package Object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);

		type = type_axe;
		name = "Woodcutting Axe";
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name + "]\nAn axe used for \nchopping trees.";
	}

}
