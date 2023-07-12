package net.consular.cataclysm.util;

public interface MagicUser {
    int getMana();

	int getMaxMana();

	void setMana(int amount);

	void addMana(int amount);

	boolean isManaVisible();

	void shouldShowMana(boolean shouldShowMana);
}
