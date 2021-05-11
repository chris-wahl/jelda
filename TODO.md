Basic Overworld/World transition complete.

~~TODO: Draw LevelChange Sensors~~

1.  ~~Build LevelChangeSensor Class~~
2.  ~~Set camTargets to RoomCenters~~
    - ~~Add debug test L key to set link as camtarget~~
3.  ~~Test changes~~
4.  ~~Set level loaded checking to be performed when the LevelChangeSensor untriggers (and sets on current level),
   rather than constantly~~
    - ~~Send vertical/horizontal flag into constructor; store for repeated cases of MakeBody~~
5. ~~Setup Level to set a LevelChangeSensor on desired tiles - will be loaded/unloaded with the rest of the tiles/sensors~~
6. ~~Detect positions appropriate to place level senors in overWorld (sand tile, grey tile)~~
    - ~~Run along top edge of Level and Right edge of level~~
    - ~~Sensors require two instances of Level, thus must set AFTER levels are created - InitGrid() already called~~

~~#### TODO: Post work~~

- ~~Determine camera error in InsideWorld load~~
- ~~Check multi-door sensors~~

#### TODO: Bring in Link's direction / animation detectinon / shaders
 
 1. Get link to load default green clothes shader
 2. Set up directionFaciing information
 3. Load animation when isMoving
    - Store to last frame
    - Call last frame if not isMoving on draw()

#### TODO: Equip weapons, establish shield hitboxes