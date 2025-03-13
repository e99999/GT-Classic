# Changelog
# 1.2.2
- Removed the Dragon Egg/Trophy Energy Siphon. It has been deprecated, and its functionallity now resides in the Magic Energy Absorber (Similar to later GT's).
- Warning: existing Siphons in the world will automatically be converted to Magic Energy Absorbers once loaded or placed (This is a config option just incase - read below)
- Warning: I have only tested siphon replacement in SSP if you are worried about potentional issues make a backup or turn off 'replaceOldSiphonWithNewAbsorber` in the config!
- Changed the Magic Energy Absorber now generates a flat rate 128EU per tick with ANY valid trophy instead of a variable rate, can use any Twilight Forest trophy or Dragon Egg. Power from active beacons has been removed.
- Changed the Magic Energy Absorber no longer has wacky functions no one to my knowledge ever used i.e absorbing enchanted tools and books/absord lingering potions/drawing energy from end portals.
- Changed Magic Energy Absorber and Magic Energy Converter have slightly easier recipes, the converter no longer needs a Beacon to craft but the Absorber still does.
- Changed Magic Absorber and Converter now have purple names, cause everyone knows magic = purple duh.
- Changed Superconductor cable full block is no longer a valid beacon base to account for the Absorber (previously trophy siphon) no longer allowing power from beacons.
- Changed Superconductor cable full block recipe is now just the previous/max tier cable + iron scaffolding because it offers no benefit other than being a full block now.
- Changed Superconductor cable lowest tier is now 8192 instead of 512 because 512 is useless and the old school GT skill "hole" to manage power at 8192 doesnt exist anymore with newer IC2C's.
- Changed Superconductor cable step down and step up crafting is now 4x instead of 2x because it makes more sense with the EU values each has.
- Changed range of Echotron item and machine from 16 and 32 blocks to 24 and 48.
- Added 'Ulitmate Armor' similiar to the old Ultimate Cheat Armor but actually craftable as an endgame goal, provides creative flight and a whole host of other goodies.
- Changed the version of GTCX required to run properly or else bad stuff might happen with the new armor, you will need the latest (0.3.2) to launch.
- Changed Technitium to have the enchanted glowly texture overlay because its the most end game metal in the mod and its also the most boring looking by default.
- Added in world multiblock guide for Fusion Reactor which will appear when placed and dissapear when the structure is complete or the Fusion Reactor is removed.
- Changed Fusion recipes in JEI so you can see that you are actually gaining power with each fusion recipe instead of only displaying the net gain .
- Changed Fusion tooltip to make it clear you need a Supercondensator to step down the power to a usable level (high tier Superconductor cable obviously still works to move the power around).
- Changed Fusion GUI to be more informative now that it doesnt need to contain the icon representing the multiblock.
- Fixed Scanners not displaying extremely high amounts of power correctly.
- Changed Sensor Stick tooltips to display better information as I thought the process was a little confusing returning to the mod myself.
- Changed the tooltips for Sensor Sticks to explain how they can be used after have scanned a block
- Changed the tooltips for Tesseract Generators and Tesseract Terminals to make it easier to understand how they work
- Changed the information that Scanners provide when scanning Tesseracts to explain exactly why they not working
- Added particles that appear above the block Tesseract Generators are connected too when the tesseract field is correctly generated.
- Fixed an issue with Tesseract Terminals that prevented them from being disabled with redstone.
- Changed Redstone Transmitter tooltip to make it more obvious it can only connect to Redstone Receivers.
- Changed Microwave Energy Transmitter tooltip to make it more obvious that it can be connect and transmit to ANY tile that supports EU.
- Added tooltips to ores to make it even easier to see what they are a source of.
- Added a recipe to get a small amount of Thorium from centrifuging rare earth.
- Added tooltips to make it clear that Quantum Chests and Tanks will keep their inventory/storage when picked up.
- Fixed IDSU having a 30% chance to break when wrenched, it is now 0% (like all other GTC machines).
- Changed IC2C items and machines that have removals toggled on in the GTC config will now be removed from JEI (Mass Fab, Plasmafier, and Plasma Cable)(all on by default).
- Changed IC2C HV Solar Panel recipe to not need IV-Transformer if IC2C Plasmafier is removed because that would put it past fusion which seems excessive.
- Changed Drum tooltips and magnifying glass/scanner info to be clearer and more useful.
- Added a tiny amount of platinum from centrifuging endstone incase jungle RNG is not on your side.
- Fixed Quantum Chests dropping its output slot on wrenching, it will now save its output slot along with the quantum inventory so nothing drops and will show in the tooltip.

# 1.2.1
- Fixed so GT Classic can run on the latest Forge and IC2C, ported fixes from Rongmario's Vintage Gregtech fork. 
- Removed GTCX/VGTX warning message on player login and associated config option.
- No other changes to this version other than fixes.
- If you want GTC to be exactly as I left it this is the version to play, possible futures updates will bring small changes and updates.
- Any future versions will still remain compatible with GTCX/VGTX.

# 1.1.7
- Fixed Electric Autocrafter dropping simulated item slots on wrenching.
- Changed Thorium rod durability from 12500 to 32000.

# 1.1.6
- Added better performance of all multiblock structures, GTCX automatically gets this as well.
- Added better performance of some basic tiles.
- Fixed miscalculation with matter fab resulting in half of the output of UU.
- Added GT Worktables now pull items from internal storage, thanks to Trinsdar.
- Fixed Tesseracts checking all sides for redstone power to disable, now only checks front.
- Changed Tesseracts use less power, 64 EU/t for the Generator and 1 EU/t for the Terminal.
- Changed recipes for IC2C Ocean Generator and Wave Generator because they are very OP.
- Changed LESU casing blocks a lot cheaper to craft.
- Added crushedCentrifued Ore from IC2 Extras to Type Filter.
- Added comparator output for Quantum Tanks.
- Removed extremely rare materials like Osmium from loot table, you can still get Iridium.
- Removed Uranium and Plutonium from loot table cause radiation.
- Added GTC loot to Twilight Forest loot tables.
- Changed increased the durability of Crumble Horn, Peacock Fan, Ore Magnet, and Giant Pick from TForest like GT6 (configurable).
- Added some processing recipes for TForest in GTC and IC2C machines.
- Added ability for GT Scanners to be nuclear thermometers.
- Added ability for GT Scanners to scan through reactor chamber/wall blocks.
- Added, using a sensor card on a reactor wall in a display screen will now show just the heat because its just the wall, scan the core!
- Fixed GT Scanners not getting the correct EU output for Reactors.
- Changed Beryllium from a fluid to a solid as it should be, recipe to convert from fluid to solid in the Centrifuge.
- Changed the Dragon Egg Energy Siphon to a Trophy Energy Siphon which can make power from more sources (configurable). Support for Open Blocks coming next update!
- Changed the explosion strength and heat output of Thorium based Reactors by factor of 0.25!
- Added Isotopic, Near Depleted, Depleted, and Re-Enriched variants of Thorium and Plutonium Rods.
- Removed bloated processing recipes for blocks to dusts etc.. in IC2C machines.
- Removed centrifuging wet coal to get some Thorium recipe.
- Removed Ic2 Extras compat recipes so it can handle it itself.
- Added full block variant of Max level Superconductor Cable, for nefarious beacon things.
- Added a few recipes for turning vanilla slabs back to blocks (configurable).
- Fixed Pyrite missing Sulfur from centrifuge recipe.
- Added warning message for GTCX (configurable).

# 1.1.5
- Added ability to add materials to GTC via Craft Tweaker thanks to Trinsdar.
- Added the Redstone Controlled Lamp from GT4, can be painted different colors and has a variable light level.
- Changed the Bedrock Miner to now try to break a pipe/consume lubricant every mining operation instead of every successful operation.
- Changed the Bedrock Miner now auto outputs out of the back instead of the top for easier automation with GTC Drums.
- Fixed crash with Player Detector throwing NPE on block update.
- Fixed Player Detector and Redstone Receiver not updating adjacent blocks redstone level like stone etc..
- Fixed colored tiles being reset to no color on servers.
- Fixed Type filter allowing similar types to pass through the filter.
- Added a recipe for compat with other mods unrefined rubber.
- Fixed Redstone Ore being mine able with the Jack Hammer.
- Fixed Superconductor not being able to handle it's stated max value thanks to Trinsdar.
- Change processing for Thermal Foundation's Glowstone, Ender, Oilshale, and Destabalized Redstone.
- Changed Fluid Buffer and Fluid Translocator throughput by 20X to handle GTCX Turbines and Thermal Boiler.
- Added ability to disable/enable Tesseracts via redstone.
- Fixed some issues with colored blocks on servers.
- Changed all Fusion recipes now have a net gain instead of just Helium.
- Change JEI for Fusion now shows net gain instead of just cost and output.

# 1.1.4
- Added a very basic Nuclear Control style Display Screen, just single block form. Use a sensor stick to set block to read, will automatically parse useful information.
- Note the Display Screens are very basic, and I have not decided how I want to handle lots of things like modular screens or adding information/titles yourself.   
- Fixed a bug where relogging would cause the Basic Worktable to be stuck "in use" texture.
- Changed Player Detector and Drum toggle modes to be sneaking with an empty hand instead of with a Wrench.
- Added ability for IDSU, AESU, and LESU to output redstone level like IC2C EU storage tiles, sneak click with an empty hand to cycle modes.
- Added comparator output for the Drum and the Battery Block.
- Changed, you can now click the Matter Fabricator with a fluid container to empty it into the internal tank.
- Changed, you can now click the Magic Energy Converter with a fluid container to empty it into the internal tank.
- Fixed crash with AE2 particle effect ASM
- Fixed Tesseract Terminal updating fluid capability but not items, now updates item pipes and stuff if destination is invalid.

# 1.1.3
- Changed, Magical Energy Absorber can now siphon energy from End Portals with a few very specific behaviors. only one per portal or KABOOM! (configurable). Also if you absorb portals in Overworld Strongholds there is a chance to disable the portal and remove an Ender Eye from the portal frame every so often (roughly one MC day).
- Added the Electric Rock Breaker from other GT's. Place lava and water vertically for Stone, or on either side for Cobble. You can also input Redstone for Obsidian and Quartz for various vanilla stone types.
- Fixed Energy Absorber was miscalculating, using the amount of enchants and not the sum of total levels of enchants on a book.
- Fixed UU-Plasma being the wrong amount, now takes 10 UU to make one cell like IC2.
- Added very funny centrifuge recipe for getting UU back from UU Plasma Cells, it would take you 8 hours at the default speed.
- Changed, Boosted UU-Plasma magical fuel value because it is now way more expensive to make.
- Changed the Mob Repeller and Microwave Energy Transmitter recipes to be a little more thought out.
- Changed, Increased the Magic Energy Converter internal tank capacity from 1 to 16 buckets.
- Added Centrifuge recipe for getting resources from Prismarine, treating it as a potassium feldspar based stone.
- Changed, UU-Matter Assembler now requires a Quantum Chest to craft and has an Integer.MAX_VALUE limit for UU Storage like the original.
- Changed, Boosted Loot! items can now spawn in multiples of up to 5 instead of just one.
- Fixed, Jack Hammer not working with modded stone types, now it detects any block made of stone that is not an ore block.
- Added leaves decaying faster, can be disabled in the config. Wont load the code if Fast Leaf Decay is loaded.
- Fixed it so you cant accidently place a fluid block when clicking a full drum with a fluid container.
- Fixed Clay Dust Centrifuge recipe giving you 2 free tubes.
- Changed, Super Solid Fuels can now use charcoal in addition to regular coal.
- Fixed vanilla rail recipes being added if Railcraft was loaded.

# 1.1.2
- Added a new block, The Block Extender. Allows you to extends side of any tile its attached too, they cannot extend each other.
- Added Type Filter, similar to a buffer but filters input using ore dict tags. Additional filters can be added in configs under mod compat.
- Added Item Filter, just because so much of the code was already implemented, its like a buffer/translocator hybrid from older GT's.
- Changed the Tesseract Terminal no longer transfers point to point, but is more like a transdimensional block extender.
- Fixed the Item Translocator converting fluid containers to other fluids when it successfully transfered them.
- Fixed Item Translocator not inputting into the correct side for the output tile.
- Changed Disassembler to no longer use TForest uncrafting table and fixed recipe being weird and allowing any Ic2 Machine.
- Changed Superconductor Item recipe to be more like GT4 using cheaper helium cells and more tungsten vs iridium.
- Changed Electric Macerator can be crafted with Tungsten ingots in addition to the original diamond recipe.
- Fixed Drum not auto outputting into the correct side for connected fluid tile.
- Added Invar as another possible metal to use in mixed metal.
- Added Gold as another possible metal to use in basic circuits.
- Changed, Magic Energy Converter now is more like a generator in that it consumes items and generates power over time vs the an instant amount of power.
- Changed, increased the magic fuel value for some vanilla items.
- Changed the recipes for translocators to not use IC2 upgrades to craft, I found it annoying in play testing myself.
- Changed AESU recipe to be more like GT4, less of an endless lapis grind.
- Added UU Matter Plasma as a dynamite magic fuel!
- Fixed some recipes not using ender chest ore dict
- Fixed buffers not checking the correct stack size to see if an inventory was full, buffers now output redstone if there no empty slots
- Added mouseover tooltips for every single GTC Gui that has a button.
- Changed, Fluid Buffer now can output a redstone signal/invert based on the tank.
- Added a few more Matter Fabricator amplifier items like older GT's, mostly gem dusts/redstone/glowstone etc..
- Allows the magnifying glass to see EU reader tooltip info, has a config option that defaults too true
- Made hopper/piston recipes with gtc less insane, now just use anyIron for hoppers and anyIron + Bronze for pistons. renamed config options
- Added config option for less grass on world gen like older MC, off by default.
- Added partial Chinese translation by YPXxiao.

# 1.1.1
- We wrapping this project up on 1.12.2, no data net, no massive open ended time sink projects. Couple more feature additions and then GTC 1.12.2 is done except bug fixes and QOL.
- Changed, wireless block setting/getting is now done with a Sensor Stick instead of Portable Scanner so you arent changing it all the time debugging a block.
- Added Redstone Transmitter and Receiver, can send redstone signals wirelessly within dimensions.
- Added Personal Forcefield Generator, physically repells mobs in a radius around the player.
- Removed compat for EnderIO's mixed alloy.
- Added Tesseract Generator and Tesseract Terminal, behavior is like GT4 except you use a Sensor Stick to pair the Terminal to the destination Tesseract Generator.
- Changed Bedrock Miner texture thanks to help from CrossVas.
- Changed lava centrifuging recipe to give Copper intsead of iron like most older GT's.
- Added hidden recipe for making fireworks from Phosphorus in addition to Gun Powder.
- Bedrock veins now only spawn in the overworld.
- Added a Centrifuge recipe for a small amount of Thorium from hydrated coal.
- Added Super Solid Fuels like in GT5U with insanely long burn times, done through crafting steps in GTC however. 
- Added Charcoal Pit/Pile Igniter like in GT5U, can be any shape up to 800 logs in size, logs must be surrounded by dirt or grass.
- Added config option to disable power from hydrogen, more complex handling can be done with IC2C Tweaker.

# 1.1.0
- Changed the Player Detector to be more up to date, change modes by sneak clicking with a wrench or read modes with a magnifying glass/reader.
- Changed GTC texture system to be much more dynamic and efficent, should use less ram - doesnt effect gameplay at all for users
- Changed, basic data net stuff is now implemented, using a computer cube as a network manager you can move items and fluids around digitally.
- Changed item mortars have been replaced with block mortars similar to GT6, there are two types flint and iron, flint has lower chance to actually break the item.
- Changed, loot table being weak sauce, now all ingots and dusts are viable as loot even addons materials.
- Fixed right clicking with scanners and magnifying glass being inconsistent with an item in the off hand.
- Added, Working on my pet project the GTC "data net", use a computer cube to control a logistics setup with various types of inputs/outputs.
- Changed bedrock ores are now infinite and 4 x time more rare.
- Added config option for automatic gem -> block / ingot -> block compressor recipes of all modded materials.
- Added massive net gain of power for doing helium fusion, Power comes out front or back of fusion controller, needs a supercondensator or max tier superconductor out.

# 1.09
- Started working on my own digital item/fluid management system, blocks are left in for testing with no recipes while i work on it.
- Added commas to big numbers in various places, JEI, energy storage gui's etc..
- Changed the Centrifuge now has tank to process fluids, although this shifts the slot count so you might have an upgrade or battery in the wrong slot.. oops : )
- Change the Matter Fabricator now also has a tank for processing fluids directly.
- Changed, drums can now auto output if sneak-wrenched, gases go up, fluids go down.
- Improved the basic worktable with help from Trinsdar
- Fixed some buffers getting pissy about what could go in what side.
- Fixed Methane and Hydrogen processing yeilding a net loss on power, they are now valid sources to generate power from.
- Added Bedrock Ore Veins, which are indicated by "Orechids" (GT6) off all vanilla/ic2/gtc ores. Not infinite but can be mined for a long time.
- Added Bedrock Miner, an HV machine which mines bedrock ores in 5 x 5 area below the miner, 4096 per operation.
- Added Microwave Energy Transmitter from GT5U, however it no longer explodes machines and self adjusts its own internal transformer.
- Fixed materials not part of GTC being overlooked with basic automation machine recipes, blocks to dusts, gems to block etc..
- Added Magnifying Glass to get small amounts of info from various tiles.
- Added Low Voltage Battery Block, its a battery that is a block and and item, you can use either as either one. Just LV for now.
- Changed Superconductor cable now has a model instead of full block.
- Changed all GTC electric items have that nice GT cyan charge/damage bar.
- Added Spray Can item which can be used with the new dye I added...
- Added Incredible Magic Dye which can be put into a spray can used for ANY color!
- Changed a bunch of GTC machines now show their active state in jei instead of inactive.
- Changed storage blocks are now colorable, cabinets, drums, and workbenches.
- Fixed a bug where fusion reactors would not animate on being active.
- Added "in use" texture to worktable for SMP until its able to support multiple players.
- Fixed Speigers extremely funky recipes for end game content, rebalanced for use with GTC.

# 1.08
- Fixed for the last time, the null error with the magic energy converter, now all possible fluids tubes are displayed in JEI instead of just GTC.
- Fixed rendering of fluids on tubes are now the actual fluid, thanks to some help from Muramasa!
- Added config option for harder jeptacks (off by default) which is also GTCX compatible.
- Changed the color of Platinum and Sheldonite ore to match other mods
- Added recipe for IC2C Fertilizer from Sulfur, Calacite, and Phosphorus
- Changed centrifuging Apatite to give Phosphorus now instead of straight fertilizer.
- Removed forcing Carbon for Carbon Fibre so the Centrifuge isnt gated behind itself.. oops
- Added Invar and Nickel into base mod materials, not a whole lot of use yet but its a very common material.
- Changed Spring boots now only damage when running 50% of the time not every single leap.
- Fixed some recipes not overriding with GTCX and IC2C Steel Mode.
- Added bi-directional ore dicting for "ingotChromium" and "ingotChrome" which I believe only Alchemistry uses atm.

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
