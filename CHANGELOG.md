# Changelog

# 1.08 (Not Released)
- Nothing to see here yet

# 1.07
- Completely redid the config system, YOU MUST DELETE AND REGEN YOUR CONFIGS FOR THE LASTTTT TIME.
- Completely refactored the entire project to now have a dedicated API although this doesnt change the mod for players at all.
- Changed JEI system so addons can handle JEI automatically if they want, this wont effect gameplay at all
- Fixed JEI now displays fluid inputs as fluids not fluid containers.
- Added a Disassembler machine that gives an 80% return on each item uncrafting something, uses 5000EU per operation.
- Added the Electric Crafting table, although atm it is in an alpha testing phase with very basic code, use at your own risk!
- Added Phosphorus dust to the base mod, its a byproduct of Netherrack by default.
- Added Sulfur dust to the base mod, its a byproduct of Netherrack by default.
- Added Sulfuric Acid to the base mod, no use for it atm.
- Changed Basalt dust is now a lava byproduct instead of Netherrack.
- Added a shaped recipe for Raw Carbon Fibre out of Carbon.
- Fixed Tesla Staff doing max damage without power.
- Changed Lightning rod checks for just rain now instead of a thunder storm to make it more viable.
- Fixed Creative/Portable Scanner's now have better method interfaces to debug any IC2C based tiles not just GTC ones.
- Fixed a bug with clicking fluid containers that would happen when a tank was near full.
- Fixed the crash with the Magic Energy Converter for real this time.. I think.
- Added recipes to empty tubes with an extractor like GT1.

# 1.06
- Note, this release is small in changes because GTC expansion updates were stalled till this was released.
- Changed overworld Sheldonite to be slightly more rare for balance. might need to regen configs or set sheldonite weight to "3".
- Added another check into the Magic Energy Converter recipe system to hopefully stop an unknown bug im unable to reproduce myself.
- Added compat for Funky Locomotion/frames based systems - machines are no longer unbreakable but just super slow to break with the wrong tool (non wrench).
- Changed Tesla Staff damage to be higher

# 1.05
- This will be the last version that has world changes/removed machines or items from existing worlds.
- Removed Blast Furnace it is now in Trinsdars Mod "GTC Expansion" which focuses on GT2-GT4 content.
- Removed molten fluids are now/will be in GTC Expansion as well.
- Removed in world fluid stuff, it was gonna be a pain todo if/when I port forward - it did not spark "joy".
- Added power from Fusion! you can now reclaim 5% possible energy of the eu required per recipe. No more power thru casings just the tile itself!!!
- Added a "Digital Chest" however instead of being a small Qchest, it is now a double chest that can store/load its inventory with data orbs.
- Added sorting button to GTC Cabinets since inventory tweaks is not compatible with IC2C inventories.
- Added more solid fuels with variable amounts to the Magic Energy Converter.
- Added CT support for UUM-Assembler and Magic Energy Converter thanks to Trinsdar.
- Fixed Creative Scanner loosing power - it now recharges to max the second it returns to a players inventory.
- Fixed some minor errors and cleaned up unused assets
- Fixed a naming issue with the super conductor item, might have to recraft some if you had any lying around.
- Fixed adv circuts recipes eating tons of metal with no added benefit, now you can get 2x adv circuits.
- Added config option to show all possible fluids as GTC tubes instead of just GTC based fluids.
- Fixed GTC ores not responding to fortune.
- Changed mining laser recipe to be somewhat like GT2, as it is very OP these days.
- Changed recipe for IC2C Battery Station, as it is also very OP.
- Added GT1 recipe for Ic2c nuke if the nuke is enabled in ic2c configs.
- Added original GT recipe for Buildcraft Quarry - can be disabled in configs.

# 1.04
- WARNING! In the next version (1.0.5) the Blast Furnace will become part of GTC Expansion (by Trinsdar)
- WARNING! In the next version (1.0.5) GTC fluid blocks and molten fluids will be removed, molten fluids will be part of GTC Expansion as well.
- Removed forestry bronze ingot crafting recipe if its loaded.
- Added temporary recipes for getting your resources back from the Reinforced casing/block and Blast Furnace before next release!
- Added more crafting recipes for duct tape, that hopefully indicate what its capible of doing in world : )
- Removed clicking behavior from quantum chest, but i redid the input/ouput logic to be instantaneous.
- Removed Supercontainer as it has no use, and no future use. Different from the Supercondensator and Superconductor.
- Fixed recipes that use the superconductor item, now use ore dictionary for other mods.
- Fixed Translocator duplicating items when wrenched.
- Fixed Charge-o-mat not charging baubles slots.
- Finally got around to making progress on the basic workbench, can now keep inventory in crafting slots - limit one active user as a time.
- Removed crafting recipe for Raw Carbon Fibre, you must now use carbon in a compressor (trying this code out subject to change).
- Fixed oversight that made machine block recipes glitch when IC2C is in steel mode.
- Fixed GTC recipes not seeing other mods aluminium ingots because I use the element name not the American spelling.
- Added GT2 style flint/iron mortars with slightly higher durability, which can be used for utility or your first bronze.
- Added the Magic Energy Absorber, generates random amounts of EU from enchanted books and tools, has two toggle-able modes for absorbing xp or lingering potions.
- Added the Magic Energy Converter, generates 24 EU per tick from Mercury, Berillium, Neon, Argon, and a lot of other mods liquids.
- Changed some Thermal Foundation fluids to be in the Magical Energy Converter instead of the IC2C Fluid Generator.
- Added the Dragon Egg Energy Siphon, 128 EU per tick when a dragon egg is placed on top.
- Added crops for Aluminium, Platinum, Ruby, Sapphire, Thorium, Titanium, and Tungsten.
- Fixed Echotron block not making sonar sound, I think?
- Added UUM-Assmebler, more of a replicator/scanner/assembler hybrid than the original uu autocrafter tile... Oh and its completely portable.
- Fixed Sheldonite not having a direct smelting recipe to Platinum.
- Fixed a rare chance for zombies with pickaxes to crash the game with an out of bounds integer.
- Added platinum to some basic recipes to give a little more use.

# 1.03
- Changed recipes to use oredict for machine casings for any other mods that wanna use them
- Fixed bug where other dims would have safe spawn zones.
- Added compat for Twilight Forest's, GTC and IC2C ores now generate in hollow hills if ore is enabled (code subject to change).
- Note Twilight Forest compat requires the newest version (3.9.984) as well as updated forge (14.23.5.2813).
- Added more byproducts from clay to please my cruel and evil alpha testers : )
- Added compat for Comp500's "Demagnetize" mod with the electromagnet.
- Added the chance for molten metals to spawn in the nether, iron, silver, gold, electrum, uranium, and platinum.
- Added rare chance for desert/hot biomes to spawn small amounts of liquid mercury.
- Added very large and rare methane clathrates under cold/snowy biomes.
- Added small and infrequent methane pockets in plains/forest biomes.
- Added small and infrequent neon/argon pockets in magical biomes.
- Added Helium, Helium3, and Deuterium trapped gas pockets in the End
- Added, zombie's who spawn underground have a chance to spawn carrying a pickaxe.
- Fixed Baubles items deciding to register whenever they felt like it.
- Added Neon, and Argon, and centrifuge air separating. Cram it Bear im not adding a whole machine/multi for this, it has no use.
- Changed Methane and Hydrogen gas blocks now explode if near a fire source, or player walks into them holding a torch.
- Removed Iridium Ore in The End but....
- Added Platinum Ore, spawn in small and sparatic amounts in jungle biomes, gives iridium in small amounts when processed.
- Added in world blocks for gases and fluids, fluids are fluids, gases float up and disperse. WIP
- Fixed ocean sand not replacing in some chunks near biome borders.

# 1.02
- WARNING! Changed IDSU power tier from 4 to 5. This might cause existing setups to explode :)
- Added Spring Boots, inspired by QwerTech for GT6. Still slightly WIP.
- Added the Electric Translocator, has no internal inventory but a 9 slot filter to pull items behind it to in front of it.
- Changed all newly placed buffers disable power transfer by default and you must enable it per tile.
- Added Basalt dust, working on a way for it to be obtainable without modded basalt stone.
- Fixed tubes can now pick up fluids in addition to already being able to place them.
- LESU now outputs multiple packets per every 10 blocks added.
- Fixed Rock Cutter not having silk touch if you cheated/spawned it in.
- Changed Fusion casing recipe to be not a such chrome nightmare.
- Added the Light Helmet like old GT's! Creates light around the player in dark areas.
- Added insufficient power warning to Blast Furnace, Fusion, and Centrifuge GUI's
- Fixed Hydrogen and Methane processing yielding a net gain of energy.
- Fixed Player Detector constantly setting state changes despite no actual change.
- Fixed Fusion casings using the wrong casings.
- Fixed the Quantum Chest give full stacks if the internal storage is above 64 when clicked.
- Fixed gravel being constantly overwritten with sand on chunk load.
- Fixed Emerald not having a macerator recipe into emerald dust
- Removed the default thick reflector recipe for harder one
- Fixed Rock Cutter defaulting to regular tool enchants in electric enchanter

# 1.01
- I recommend regenerating configs in this version.
- Added option to replace ocean gravel with sand like newer MC versions.
- Added option to reduce fog/haze underwater like newer MC versions
- Added configurable ore dict for any instance of ingotWroughtIron and ingotRefinedIron.
- Fixed some electric items spamming the "use" animation.
- Fixed a bug with the Quantum Chest that would stop it from reaching full capacity.
- Fixed another bug with the Quantum Chest that would ignore items max stack size on output.
- Fixed AESU getting stuck when adjusting near the min/max.
- Added Supercontainer, for end game stuff - not a real use yet.
- Added Supercondensator, not much real use yet just for recipes and stuff.
- Changed Matter Fabricator tier raised to near infinite input.
- Changed Fusion Reactor teir to 5 instead of 6 to avoid confusion.
- Fixed various EU values in later game machines.
- Changed both IC2C macerators to be more expensive like GT1.
- Added GT6 style "safe spawn" zone, 128 block radius and toggable with configs.
- Fixed error in my code that prevented disabling of Bauxite ore.
- Removed useless Draconic Evolution compat, thats really up to mod pack devs.
- Fixed and added some more Thermal compat things.
- Added very thorough EnderIO compat.
- Added a buttload of config options.
