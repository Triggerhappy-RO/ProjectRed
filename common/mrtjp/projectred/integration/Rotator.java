package mrtjp.projectred.integration;

import static mrtjp.projectred.transmission.BasicWireUtils.oldBACK;
import static mrtjp.projectred.transmission.BasicWireUtils.oldFRONT;
import static mrtjp.projectred.transmission.BasicWireUtils.oldLEFT;
import static mrtjp.projectred.transmission.BasicWireUtils.oldRIGHT;

/**
 * Provides simple means of obtaining a relative to absolute directions and vice
 * versa. This works if you need a FRONT, BACK, LEFT, or RIGHT direction
 * relative to what you need;
 * 
 * @author MrTJP, Immibis
 * 
 */
public class Rotator {

    /** Magic array of numbers */
    private static int[][] rotationMap = { 
        { 9, 9, 4, 5, 3, 2 }, 
        { 9, 9, 5, 4, 2, 3 }, 
        { 5, 4, 9, 9, 0, 1 }, 
        { 4, 5, 9, 9, 1, 0 }, 
        { 2, 3, 1, 0, 9, 9 }, 
        { 3, 2, 0, 1, 9, 9 }, };

    /** [side][front][relative direction] to absolute direction **/
    public static final int[][][] relToAbs = new int[6][6][4];

    /** [side][front][absolute direction] to relative direction **/
    public static final int[][][] absToRel = new int[6][6][6];

    /** Sets up the arrays, since they are too massive to do by hand. **/
    static {
        for (int side = 0; side < 6; side++) {
            for (int front = 0; front < 6; front++) {
                if ((front & 6) == (side & 6)) {
                    continue;
                }
                relToAbs[side][front][oldFRONT] = front;
                relToAbs[side][front][oldBACK] = front ^ 1;
                relToAbs[side][front][oldLEFT] = rotationMap[side][front];
                relToAbs[side][front][oldRIGHT] = rotationMap[side][front ^ 1];
                for (int dir = 0; dir < 4; dir++) {
                    absToRel[side][front][relToAbs[side][front][dir]] = dir;
                }
            }
        }
    }
    
    /**
     * Used to get relative direction (0-3).
     * @param side The side you are on.
     * @param facing The direction the front is facing (ForgeDirection).
     * @param direction The direction you want the relative direction for (ForgeDirection).
     * @return
     */
    public static int absoluteToRelative(int side, int facing, int direction) {
        // If the side and direction are the same or opposites, such an absolute direction does not exist.
        if ((side & 6) == (direction & 6)) {
            return -1;
        }
        return absToRel[side][facing][direction];
    }
    
    /**
     * Used to get absolute direction (ForgeDirection). 
     * @param side The side you are on.
     * @param facing The direction the front is facing (ForgeDirection).
     * @param direction The direction you want the absolute direction for (0-3).
     * @return
     */
    public static int relativeToAbsolute(int side, int facing, int direction) {
        return relToAbs[side][facing][direction];
    }

}
