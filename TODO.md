# Todo (Ideas)
- If you are reading this dont get your hopes up - just possible ideas but nothing more atm.
- I plan todo a casual playthrough myself, any changes will be QOL or singular fun additions, no huge overhauls, no giant webs of coding.
- Give the player more obvious information about the magic machines and make them more useful:
- Better distinction between Magic Energy Converter and Magic Energy Absorber in the tool tips
- Centrifuge progress bar looks like 1 pixel to high on the y axis maybe?
- Computer Cube should be an in game Wiki for tiles. (Someday not soon, add compat for mods if that GTCX guy uses GTC???)
- Project E compat (post fusion)
- Avartia compat (post fusion)
- Maybe Galactricraft/Planets compat if GTCX/VTCX is not loaded?
- Maybe make more viables ways of exploring to progress vs straight mining in caves.
- Platinum from end stone centrifuge recipe
- Dramtically reduce power it takes to generate a tesseract, and better tooltips
- Make portable scanner use regular circuts
- Make the sensor stick a little easier to understand (maybe GUI's to those machines)
- Redstone transmitter tooltip should explain need to connect to redstone reciever, and no need for power
- Possibly make redstone transmitter tiles and screens work interdimensionally
- Better tooltips for tesseracts
- Increase range of echotron slightly
- Add tooltips for most items/tools
- Add icon for EU output in magic fuel generator recipes? (maybe)
- Add Gregorius-Tree lol (use oak tree as base)
- Add tooltip to explain how you can click tanks to open flow (up for gases/down for fluids)
- When you toggle a drum and there is a fluid/gas explain where it will go in addition to the existing tooltips
- Gregtech Cellphone?
- Add a tool for manually mining bedrock ore maybe? (would be a chance based thing so miner would always be better)

# Magical Energy Redo
- I plan on maybe simplifying the magic energy stuff because its so convoluted and no one used any of these features
- DELETE magic energy absorber as we know it,
- Replace Trophy Generator as new Magic Energy Absorber (remove gui, remove fake recipe map, and no more power from beacons)
- Remove ability for superconductor and magic energy absorber to be used in beacons.
- All trophies generate a flat 128EU, eggs and twilight forest trophies - no other skulls or heads.
- Recipe will take platinum instead of a beacon in the middle.
- Platinum from end stone centrifuge recipe
- Make sure to delete all the utility methods for old absorber
- Make sure GTCX recongizes this or might cause issues if its looking for a block that isnt there anymore.
- Hotwsap the dragon egg tile entity into the magic energy absorber block to limit screwing up of block/tile data.
- End crystal instead of beacon for magic energy converter recipe

# Maybe-do
- Fix Speiger's total apathy twoards rotated block textures - will be a PITA have to remove and redo facing implementation
- Multiplayer support for Basic Worktable.
- Redo Forge Fluid Transfer method to account for speigers dumb multi tanks
- FIX STUPID RECIPE DUPE/ERROR LOADING WARNINGS
- Make custom recipe modifer for fusion power out

# Bugs
- Try to reduce lag when setting the recipe in an autocrafter
- Optimize the Charge-O-Mat, and Bedrock Miner