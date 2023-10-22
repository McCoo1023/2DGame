package main;

import java.util.ArrayList;
import main.Quest;

public class QuestJournal {
    private ArrayList<Quest> activeQuests;
    private ArrayList<Quest> completedQuests;

    public QuestJournal() {
        activeQuests = new ArrayList<>();
        completedQuests = new ArrayList<>();
    }

    public void addQuest(Quest quest) {
        activeQuests.add(quest);
    }

    public void completeQuest(Quest quest) {
        activeQuests.remove(quest);
        completedQuests.add(quest);
    }

    public ArrayList<Quest> getActiveQuests() {
        return activeQuests;
    }

    public ArrayList<Quest> getCompletedQuests() {
        return completedQuests;
    }
}
