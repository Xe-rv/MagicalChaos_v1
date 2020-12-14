/* ShotEntity.java
 * March 27, 2006
 * Represents player's ship
 */

//shooting class
public class BallEntity extends Entity {
	private double moveSpeed = 700; // horizontal speed shot moves
	private boolean used = false; // true if shot hits something
	private Game game; // the game in which the ship exists

	/* construct the shot
	 * input: game - the game in which the shot is being created
	 *        ref - a string with the name of the image associated to
	 *              the sprite for the shot
	 *        x, y - initial location of shot
	 */
	public BallEntity(Game g, String r, int newX, int newY) {
			super(r, newX, newY);  // calls the constructor in Entity
		    game = g;
		    dx = -moveSpeed;
	} // constructor

	/* move
	 * input: delta - time elapsed since last move (ms)
	 * purpose: move shot
	 */
	public void move (long delta){
	    super.move(delta);  // calls the move method in Entity
	
	    // if shot moves off top of screen, remove it from entity list
	   if (x < -50) {
		game.removeEntity(this);
	   }// if

	} // move


	/* collidedWith
	 * input: other - the entity with which the shot has collided
	 * purpose: notification that the shot has collided
	 *          with something
	 */
   public void collidedWith(Entity other) {
     // prevents double kills
     if (used) {
       return;
     } // if
     
     // if it has hit an alien, kill it!
     if (other instanceof ShipEntity) {
       // remove affect entities from the Entity list
       game.removeEntity(this);
       game.removeEntity(other);
       
       // notify the game that the alien is dead
       game.notifyDeath();
       used = true;
     } // if

   } // collidedWith

} // ShipEntity class
