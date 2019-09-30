# Changelog
# 1.04 (Not Released)
- Added GT2 style flint/iron mortars with slightly higher durability, which can be used for utility or your first bronze.
- Added the Magic Energy Absorber, generates random amounts of EU from enchanted books and tools, has two addional modes for absorbing xp or lingering potions.
- Added the Magic Energy Converter, generates 24 EU per tick from Mercury, Berillium, Neon, and Argon (more stuff to come).
- Added the Dragon Egg Energy Siphon, 128 EU per tick when a dragon egg is placed on top.
- Added crops for Aluminium, Platinum, Ruby, Sapphire, Thorium, Titanium, and Tungsten.
- Added original GT1 helium fusion recipe, costs 40 million EU but will output 1,048,576 EU into adjacent Supercondensator(must be front or back of the fusion tile).
- Fixed Echotron block not making sonar sound, I think?
- Added UUM-Assmebler, more of a replicator/scanner/assembler hybrid than the original uu autocrafter tile
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
