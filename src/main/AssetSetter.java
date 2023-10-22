package main;

import Entity.NPC_OldMan;
import Object.OBJ_Axe;
import Object.OBJ_key;
import Object.OBJ_potion_red;
import Object.OBJ_shield_blue;
import monster.MON_GreenSlime;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;

	}

	public void setObject() {

		int i = 0;
		gp.obj[i] = new OBJ_key(gp);
		gp.obj[i].worldX = gp.tileSize * 25;
		gp.obj[i].worldY = gp.tileSize * 23;
		i++;
		gp.obj[i] = new OBJ_key(gp);
		gp.obj[i].worldX = gp.tileSize * 21;
		gp.obj[i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[i] = new OBJ_key(gp);
		gp.obj[i].worldX = gp.tileSize * 26;
		gp.obj[i].worldY = gp.tileSize * 21;
		i++;
		gp.obj[i] = new OBJ_Axe(gp);
		gp.obj[i].worldX = gp.tileSize * 33;
		gp.obj[i].worldY = gp.tileSize * 21;
		i++;
		gp.obj[i] = new OBJ_shield_blue(gp);
		gp.obj[i].worldX = gp.tileSize * 37;
		gp.obj[i].worldY = gp.tileSize * 9;
		i++;
		gp.obj[i] = new OBJ_potion_red(gp);
		gp.obj[i].worldX = gp.tileSize * 37;
		gp.obj[i].worldY = gp.tileSize * 12;
		i++;
	}

	public void setNPC() {

		gp.npc[0] = new NPC_OldMan(gp, gp.forgottenArtifactQuest);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;

	}

	public void setMonster() {

		int i = 0;

		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 23;
		gp.monster[i].worldY = gp.tileSize * 36;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 23;
		gp.monster[i].worldY = gp.tileSize * 37;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 24;
		gp.monster[i].worldY = gp.tileSize * 37;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 32;
		gp.monster[i].worldY = gp.tileSize * 42;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 38;
		gp.monster[i].worldY = gp.tileSize * 35;
		i++;

	}

}
