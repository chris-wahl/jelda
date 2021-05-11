This is something of a pet project.  It's an effort which has the somewhat lofty goal of faithfully re-creating the original **_Legend_ _of_ _Zelda_** for the NES in Java.

It originally began as an excuse to teach myself Java in a more meaningful way, some work with Shaders, as well as make an effort to learn Box2D.  It is a rare day that I get any time to work on this - or anything at all - but I'd like to continue building it as I go.

I have _not_ made the assets.  The original asset files are included and the credit belongs to those with their respective tags.

An attempt has been made to over-generalize classes through interfaces, parent classes, etc.  Most of this is academic in process.  No documentation is provided.

The current status:

**Unplayable**.  This isn't actually a game of any kind right now.  The overworld will load dynamically (only levels within 1 level of Link's current position will be loaded at any time).  The Box2D objects - currently, Link's hitbox, the walls of each level, and the level change sensor boxes all load based on the tiles of the particular level.

The raw overworld level tile information is adapted from [this](https://inventwithpython.com/blog/2012/12/10/8-bit-nes-legend-of-zelda-map-data/) post on the Invent with Python blog.

For now, it's effectively just a walking tour of the overworld with a couple of cave transitions.