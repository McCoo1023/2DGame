package main;

import Entity.Player;
import monster.MON_GreenSlime;

public class ForgottenArtifactQuest extends Quest {
    
    private int stage;

    public ForgottenArtifactQuest(String questName, String description, int rewardXP) {
        super(questName, description, rewardXP);
        this.stage = 0;  // Initial stage
    }

    @Override
    public boolean checkCompletion(Player player) {
        // Assuming that stage 1 requires killing 3 slimes
        if (stage == 1) {
            if (player.getSlimesDefeated() >= 3) {
                advanceStage();  // Move on to the next stage of the quest
                return true;
            }
        }
        // Add more conditions for other stages if needed
        return false;
    }


    public void advanceStage() {
        stage++;
    }

    public int getStage() {
        return stage;
    }
    
    public void performStage(Player player, int slimesDefeated, boolean artifactRetrieved) {
        switch (stage) {
            case 0:
                // Stage 0: Speak to the Old Man to accept the quest.
                // This will be handled in the NPC_OldMan class.
                break;
            case 1:
                // Stage 1: Defeat 3 Green Slimes to collect keys.
                if (slimesDefeated >= 3) {
                    advanceStage();
                }
                break;
            case 2:
                // Stage 2: Return the keys to the Old Man to get the cave's location.
                // This will be handled in the NPC_OldMan class.
                break;
            case 3:
                // Stage 3: Go to the cave and retrieve the artifact.
                if (artifactRetrieved) {
                    advanceStage();
                }
                break;
            case 4:
                // Stage 4: Return the artifact to the Old Man.
                // This will be handled in the NPC_OldMan class.
                break;
            case 5:
                // Stage 5: Quest is complete.
                if (!isCompleted()) {
                    completeQuest(player);
                }
                break;
            default:
                // Invalid stage.
                break;
        }
    }

}
