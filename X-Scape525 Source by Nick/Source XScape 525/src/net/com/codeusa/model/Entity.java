package net.com.codeusa.model;

/**
 * @author Fudge <runescapejw191@yahoo.com> <rune-server.org>
 */

interface Entity {

	public int getX();

	public int getY();

	public int getHeight();

	public int getPlayerId();

	public String getMessageString(int economyId);

	public int getLoginSpellbook();

	public int getLoginSpecial();

	public void updatePlayer(boolean updateIsNeccesary);

	public void checkAmount();

	public void playerWalk(int x, int y, int emote, int delay);

	public void appendExperience(int amount, int skillId);

	public void resetAttack();

	public void appendDeath();

	public boolean skillCapeEquiped();

	public void loadStaticObjects();

	public void setGodWarsStrings();

	public void convertPlayerToNpc(int npcType);

	public void updatePlayerAppearance(int walkAnim, int standAnim, int runAnim);

	public void appendToBanned(String player);

	public boolean clanWarsFightArea();

}