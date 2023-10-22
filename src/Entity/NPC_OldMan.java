package Entity;

import java.util.Random;

import main.ForgottenArtifactQuest;
import main.GamePanel;
import main.Quest;

public class NPC_OldMan extends Entity {

	private ForgottenArtifactQuest quest;

	public NPC_OldMan(GamePanel gp, ForgottenArtifactQuest quest) {
        super(gp);
        this.quest = quest;

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
	

	// Add a method to set the quest
	public void setQuest(ForgottenArtifactQuest quest) {
	    this.quest = quest;
	}
	
	public Quest getQuest() {
	    return this.quest;
	}

	@Override
    public void speak() {
        int questStage = quest.getStage();
        switch (questStage) {
        case 0:
            // Initial dialogue to offer quest
            gp.ui.currentDialogue = "Ah, greetings, young adventurer.\nYou look like someone who seeks \npurpose.";
            quest.advanceStage();
         // When starting a quest
            ForgottenArtifactQuest newQuest = new ForgottenArtifactQuest("Find the Artifact", "Retrieve the artifact from the cave", 500);
            gp.player.getQuestJournal().addQuest(newQuest);
            break;
        case 1:
            // Dialogue after player accepts quest but before 3 slimes are defeated
            gp.ui.currentDialogue = "Have you rid the village of those\npesky slimes yet? Our crops are\ndepending on it!";
            break;
        case 2:
            // Dialogue after 3 slimes are defeated
            gp.ui.currentDialogue = "Ah, I see those slimes didn't \nstand a chance! Now, about that \nartifact...";
            quest.advanceStage();
            break;
        case 3:
            // Dialogue for telling the player the location of the cave
            gp.ui.currentDialogue = "The artifact is said to be in a \ncave to the north. But beware, the \npath is treacherous.";
            break;
        case 4:
            // Dialogue for when the player returns with the artifact
            gp.ui.currentDialogue = "You've returned, and with the artifact!\nYou've done this old man a great \nservice.";
            quest.advanceStage();
            gp.player.getQuestJournal().completeQuest(quest);  // Use the quest object already tracked in this class
            break;

        case 5:
            // Dialogue for after the quest is completed
            gp.ui.currentDialogue = "The village is forever in your debt.\nFeel free to visit me anytime, adventurer.";
            break;
        default:
            // Invalid stage
            break;
    }

		// CHARACTER SPECIFIC DIALOGUE can be entered here rather than VVV
		// super.speak(); no longer needed since we created a quest
	}
}
