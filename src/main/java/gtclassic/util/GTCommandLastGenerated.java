package gtclassic.util;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import gtclassic.GTConfig;
import gtclassic.GTMod;
import gtclassic.helpers.GTHelperWorld;
import ic2.core.IC2;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GTCommandLastGenerated extends CommandBase {

	public GTCommandLastGenerated() {
		aliases = Lists.newArrayList(GTMod.MODID, "GTLG", "gtlg");
	}

	private final List<String> aliases;

	@Override
	@Nonnull
	public String getName() {
		return "gtlg";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "gtlg";
	}

	@Override
	@Nonnull
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args)
			throws CommandException {
		if (!GTConfig.worldGenDebug) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED
					+ "Enable worldGenDebug in the GT config to use this command"));
			return;
		}
		if (!IC2.platform.isOp(sender.getCommandSenderEntity().getUniqueID())) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED
					+ "You do not have permission to use this command"));
			return;
		}
		if (GTHelperWorld.lastGenPos == null) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED
					+ "Last generated block pos is null"));
			return;
		}
		if (sender instanceof EntityPlayer) {
			GTCommandTeleporter.teleportToDimension((EntityPlayer) sender, GTHelperWorld.dim, GTHelperWorld.lastGenPos.getX(), GTHelperWorld.lastGenPos.getY(), GTHelperWorld.lastGenPos.getZ());
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			@Nullable BlockPos targetPos) {
		return Collections.emptyList();
	}
}
