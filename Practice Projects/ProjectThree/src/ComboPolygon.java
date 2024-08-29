import java.util.Arrays;

/**
 * A ComboPolygon consists of a collection of Polygons that together will
 * be treated as a single unit.
 */
public class ComboPolygon {
    private Polygon[] polys;
    private int nextIndex;

    /** Create a new ComboPolygon that will contain a certain number of Polygons. */
    public ComboPolygon(int numberOfPolys)
    {
        polys = new Polygon[numberOfPolys];
        nextIndex = 0;
    }

    /** Add a new Polygon to this ComboPolygon. */
    public void addPolygon(Polygon p)
    {
        polys[nextIndex] = p;
        nextIndex++;
    }

    /** Translate this ComboPolygon in the direction given by distx and disty. */
    public void translate(double distx, double disty)
    {
        for (Polygon poly : polys) poly.translate(distx, disty);
    }

    /** Rotate this ComboPolygon around the point (a, b) by the degrees given by angle. */
    public void rotateAround(double a, double b, double angle)
    {
        for (Polygon poly : polys) poly.rotateAround(a, b, angle);
    }

    /** Draw this ComboPolygon on a canvas.  The canvas should already be constructed and shown. */
    public void drawOn(SimpleCanvas canvas)
    {
        for (Polygon poly : polys) poly.drawOn(canvas);
    }

    /** Return a String representation of this ComboPolygon. */
    public String toString() {
        return "ComboPolygon: " + Arrays.toString(polys);
    }
}
