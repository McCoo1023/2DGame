package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap();

	}

	public void getTileImage() {

		// placeholder begin
		setup(0, "grass00", false); // set a placeholder image to
		setup(1, "grass00", false); // avoid nullPointer exception
		setup(2, "grass00", false); // from scanning our array
		setup(3, "grass00", false); // skip 0 - 9
		setup(4, "grass00", false); // this makes our shit text map
		setup(5, "grass00", false); // actually suitable for human
		setup(6, "grass00", false); // eyes while it is a text file
		setup(7, "grass00", false); // by avoiding messy lines in .txt
		setup(8, "grass00", false);
		setup(9, "grass00", false);

		// placehoders end

		setup(10, "grass00", false);
		setup(11, "grass01", false);
		setup(12, "water00", true);
		setup(13, "water01", true);
		setup(14, "water02", true);
		setup(15, "water03", true);
		setup(16, "water04", true);
		setup(17, "water05", true);
		setup(18, "water06", true);
		setup(19, "water07", true);
		setup(20, "water08", true);
		setup(21, "water09", true);
		setup(22, "water10", true);
		setup(23, "water11", true);
		setup(24, "water12", true);
		setup(25, "water13", true);
		setup(26, "road00", false);
		setup(27, "road01", false);
		setup(28, "road02", false);
		setup(29, "road03", false);
		setup(30, "road04", false);
		setup(31, "road05", false);
		setup(32, "road06", false);
		setup(33, "road07", false);
		setup(34, "road08", false);
		setup(35, "road09", false);
		setup(36, "road10", false);
		setup(37, "road11", false);
		setup(38, "road12", false);
		setup(39, "earth", false);
		setup(40, "wall", true);
		setup(41, "tree", true);

	}

	public void setup(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {

			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadMap() {

		try {
			InputStream is = getClass().getResourceAsStream("/maps/worldV2.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" "); // boundaries based on world map, not our screen map

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}

				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();

		} catch (Exception e) {

		}

	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX; // counts the distance between player and tile,
																			// tells drawImage to load tile in that
																			// coordinate (the -)
			int screenY = worldY - gp.player.worldY + gp.player.screenY; // off sets the difference of reaching corners,
																			// not displaying us out of bounds due to
																			// limitations (the +)

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) { // as long as a tile is WITHIN
																						// BOUNDARY it will be drawn

				g2.drawImage(tile[tileNum].image, screenX, screenY, null);

			}

			worldCol++; // increases the tiles to be drawn, if it reaches max we just move on to rows,
						// so all tiles are constantly drawn

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}

	}

}
