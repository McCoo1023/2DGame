package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
	public boolean showQuestJournal = false;

	// debug
	boolean checkDrawTime = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		// TITLE STATE
		if (gp.gameState == gp.titleState) {

			if (gp.ui.titleScreenState == 0) {

				if (code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				}
				if (code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
					}
				}
				if (code == KeyEvent.VK_ENTER) {

					if (gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = 1;
					}
					if (gp.ui.commandNum == 1) {
						// add later
					}
					if (gp.ui.commandNum == 2) {
						System.exit(0);
					}
				}
			}

			else if (gp.ui.titleScreenState == 1) {

				if (code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				}
				if (code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}
				}

				if (code == KeyEvent.VK_ENTER) {

					if (gp.ui.commandNum == 0) {
						System.out.println("Do some fighter specific stuff");
						gp.player.charClass = 1;
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if (gp.ui.commandNum == 1) {
						System.out.println("Do some rogue specific stuff");
						gp.player.charClass = 2;
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if (gp.ui.commandNum == 2) {
						System.out.println("Do some wizard specific stuff");
						gp.player.charClass = 3;
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if (gp.ui.commandNum == 3) {
						System.out.println("Back");
						gp.ui.titleScreenState = 0;
					}
				}
			}

		}

		// PLAY STATE
		else if (gp.gameState == gp.playState) {

			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true; // maybe switch to arrow keys and use WSAD as functions?
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			if (code == KeyEvent.VK_L) {
			    // Toggle the display of the quest journal
			    showQuestJournal = !showQuestJournal;
			}
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			if (code == KeyEvent.VK_C) {
				gp.gameState = gp.characterState;
			}
				if(gp.player.charClass == 3) {
					if (code == KeyEvent.VK_F) {
						shotKeyPressed = true;
					}
				
			}

			// debug
			if (code == KeyEvent.VK_T) {
				if (checkDrawTime == false) {
					checkDrawTime = true;
				} else if (checkDrawTime == true) {
					checkDrawTime = false;
				}
			}
		}

		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}

		// DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {

			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}

		// character state
		else if (gp.gameState == gp.characterState) {
			if (code == KeyEvent.VK_W) {
				if (gp.ui.slotRow != 0) {
					gp.ui.slotRow--;
					gp.playSE(9);
				}
			}
			if (code == KeyEvent.VK_A) {
				if (gp.ui.slotCol != 0) {
					gp.ui.slotCol--;
					gp.playSE(9);
				}
			}
			if (code == KeyEvent.VK_S) {
				if (gp.ui.slotRow != 3) {
					gp.ui.slotRow++;
					gp.playSE(9);
				}
			}
			if (code == KeyEvent.VK_D) {
				if (gp.ui.slotCol != 4) {
					gp.ui.slotCol++;
					gp.playSE(9);
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				gp.player.selectItem();
			} else if (code == KeyEvent.VK_C) {
				gp.gameState = gp.playState;
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}

		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}

		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}

		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}

	}

}
