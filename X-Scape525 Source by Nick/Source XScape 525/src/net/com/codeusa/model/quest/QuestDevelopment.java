package net.com.codeusa.model.quest;

import net.com.codeusa.model.Player;

public class QuestDevelopment implements Quest {

	Player player;

	/**
	 * Class constructor
	 */
	public QuestDevelopment(Player player) {
		this.player = player;
	}

	/**
	 * Quest Scroll opening system.
	 */
	public void addQuestScroll() {
		if (getOwner() == null)
			return;
		/**
		 * All String id's on interface 275 will be vanished from 22 till 310.
		 */
		for (int i = 22; i < 311; i++)
			getOwner().getActionSender().setString(getOwner(), "", 275, i);
		/**
		 * Simple switch statement which uses quest ids.
		 */
		switch (getOwner().questId) {

			case QUEST_1:
				getOwner().getActionSender().setString(getOwner(), "Monkey madness", 275, 2);
			break;
		}
		setQuestId(RESET_ID);
		getOwner().getActionSender().showInterface(getOwner(), 275);
	}

	/**	
	 * Set the quest colors if player already started the quest.
	 */
	public void setQuestColors() {
		switch (getOwner().questStage) {

			case 0:
				getOwner().getActionSender().setString(getOwner(), "Monkey madness", 259, 2);
			break;
		}
	}

	/**
	 * Setting quest types.
	 */
	public void setQuestId(int questId) {
		getOwner().questId = questId;
	}

	Player getOwner() {
		return player;
	}

	private static final int RESET_ID = 0;
	private static final int QUEST_1 = 1;

}