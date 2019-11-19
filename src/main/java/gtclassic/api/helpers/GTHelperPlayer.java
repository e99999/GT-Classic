package gtclassic.api.helpers;

import net.minecraft.entity.player.EntityPlayer;

public class GTHelperPlayer {

	/**
	 * Thanks to OpenMods/OpenBlocks for being MIT which allows me to use the code
	 * below
	 */
	public static int getPlayerXP(EntityPlayer player) {
		return (int) (getExperienceForLevel(player.experienceLevel) + (player.experience * player.xpBarCap()));
	}

	public static void addPlayerXP(EntityPlayer player, int amount) {
		int experience = getPlayerXP(player) + amount;
		player.experienceTotal = experience;
		player.experienceLevel = getLevelForExperience(experience);
		int expForLevel = getExperienceForLevel(player.experienceLevel);
		player.experience = (float) (experience - expForLevel) / (float) player.xpBarCap();
	}

	public static int getLevelForExperience(int targetXp) {
		int level = 0;
		while (true) {
			final int xpToNextLevel = xpBarCap(level);
			if (targetXp < xpToNextLevel)
				return level;
			level++;
			targetXp -= xpToNextLevel;
		}
	}

	public static int getExperienceForLevel(int level) {
		if (level == 0)
			return 0;
		if (level <= 15)
			return sum(level, 7, 2);
		if (level <= 30)
			return 315 + sum(level - 15, 37, 5);
		return 1395 + sum(level - 30, 112, 9);
	}

	public static int xpBarCap(int level) {
		if (level >= 30)
			return 112 + (level - 30) * 9;
		if (level >= 15)
			return 37 + (level - 15) * 5;
		return 7 + level * 2;
	}

	private static int sum(int n, int a0, int d) {
		return n * (2 * a0 + (n - 1) * d) / 2;
	}
}
