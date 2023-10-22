package main;

import Entity.Player;

public abstract class Quest {
    protected String questName;
    protected String description;
    protected boolean isCompleted;
    protected int rewardXP;

    public Quest(String questName, String description, int rewardXP) {
        this.questName = questName;
        this.description = description;
        this.isCompleted = false;
        this.rewardXP = rewardXP;
    }

    public abstract boolean checkCompletion(Player player);

    public void completeQuest(Player player) {
        if (!isCompleted && checkCompletion(player)) {
            isCompleted = true;
            player.gainXP(rewardXP);
        }
    }

    public String getQuestName() {
        return questName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
    
    public int getRewardXP() {
        return this.rewardXP;
    }

}
