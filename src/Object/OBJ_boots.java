package Object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_boots extends Entity {

	public OBJ_boots(GamePanel gp) {
		super(gp);

		name = "Boots";
		down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
	}
}
