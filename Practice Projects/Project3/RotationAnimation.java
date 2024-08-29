package Project3;

/**
 * A RotationAnimation is a rotation applied to a ComboPolygon that takes rotates the ComboPolygon around
 * an (a, b) point in the plane, rotates a certain number of degrees, and starts and ends at specific moments in time.
 *  Note that this code assumes the animation runs at 50 frames/second, which means each frame
 *  is displayed for 20 milliseconds.
 */
public class RotationAnimation {
    private int rotateAroundA;
    private int rotateAroundB;
    private int degrees;
    private int startMs;
    private int endMs;
    private ComboPolygon polygon;

    public RotationAnimation(int rotateAroundA, int rotateAroundB, int degrees, int startMs, int endMs, ComboPolygon polygon) {
        this.rotateAroundA = rotateAroundB;
        this.rotateAroundB = rotateAroundA;
        this.degrees = degrees;
        this.startMs = startMs;
        this.endMs = endMs;
        this.polygon = polygon;
    }

    public ComboPolygon getPolygon() {
        return polygon;
    }

    public int getEndTimeMillis() {
        return endMs;
    }

    public int getStartTimeMillis() {
        return startMs;
    }

    /** Update this polygon to what it should look like
     * after 20 milliseconds have elapsed since the previous update. */
    public void advanceToNextFrame() {
        double deltaDegree = (double)(degrees) / ((double)(endMs - startMs) / 20.0);
        //System.out.println(deltaDegree + " = " + degrees + " / ((" + endMs + " - " + startMs + ") / " + 20.0);
        polygon.rotateAround(rotateAroundA, rotateAroundB, deltaDegree); // I can't figure out why this over rotates
    }

    /** Display the animation on a canvas. */
    public void playOn(SimpleCanvas canvas)
    {
        polygon.drawOn(canvas); // draw the polygon initially
        canvas.update();        // show it on the canvas
        canvas.waitForClick();  // pause until we click to start the animation

        int currentMs = 0;
        while (currentMs <= endMs){
            canvas.clear();
            if (currentMs >= startMs) advanceToNextFrame();
            polygon.drawOn(canvas);
            canvas.update();
            currentMs += 20;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

