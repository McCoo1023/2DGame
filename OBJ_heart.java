package Object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_heart extends Entity {

	public OBJ_heart(GamePanel gp) {
		super(gp);

		name = "Heart";
		image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);

	}

}