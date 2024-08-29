/**
 * A TranslationAnimation is a translation applied to a ComboPolygon that takes moves the ComboPolygon a certain
 * distance in the x and y directions, and starts and ends at specific moments in time.
 * Note that this code assumes the animation runs at 50 frames/second, which means each frame
 * is displayed for 20 milliseconds.
 */
public class TranslationAnimation {
    private int changeX;  // total change in x direction
    private int changeY;  // total change in y direction
    private int startMs;  // when the animation begins (ms)
    private int endMs;    // when the animation ends (ms)
    private ComboPolygon polygon;  // the polygon we are animating

    public TranslationAnimation(int changeX, int changeY, int startMs, int endMs, ComboPolygon polygon) {
        this.changeX = changeX;
        this.changeY = changeY;
        this.startMs = startMs;
        this.endMs = endMs;
        this.polygon = polygon;
    }

    public ComboPolygon getPolygon() {
        return polygon;
    }

    public int getStartTimeMillis() {
        return startMs;
    }

    public int getEndTimeMillis() {
        return endMs;
    }

    /** Update this polygon to what it should look like
     * after 20 milliseconds have elapsed since the previous update. */
    public void advanceToNextFrame()
    {
        double deltaX = (double)(changeX) / ((double)(endMs - startMs) / 20.0);
        //System.out.println(deltaX + " = " + changeX + " / ((" + endMs + " - " + startMs + ") / " + 20.0);
        double deltaY = (double)(changeY) / ((double)(endMs - startMs) / 20.0);
        //System.out.println(deltaY + " = " + changeY + " / ((" + endMs + " - " + startMs + ") / " + 20.0);
        polygon.translate(deltaX, deltaY);
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
