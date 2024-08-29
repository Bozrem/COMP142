import java.awt.*;

public class Tests {
    public static void main(String[] args) {
        //testPolygons();

        //testTranslation();
        //testRotation();

        //testComboPolygons();

        //testTranslationFrames();
        //testTranslationAnimation();

        //testRotationAnimation();

        testFullAnimation();
    }

    public static void testPolygons() {
        Polygon rectangle = new Polygon(4, Color.RED);
        rectangle.addPoint(100, 100);
        rectangle.addPoint(200, 100);
        rectangle.addPoint(200, 300);
        rectangle.addPoint(100, 300);
        System.out.println(rectangle); // tests your toString.

        Polygon triangle = new Polygon(3, Color.BLUE);
        triangle.addPoint(300, 300);
        int triangleX = (int) (300+(100 * Math.cos(3.14/3)));
        int triangleY = (int) (300-(100 * Math.sin(3.14/3)));
        triangle.addPoint(triangleX, triangleY);
        triangle.addPoint(400, 300);
        System.out.println(triangle);

        SimpleCanvas canvas = new SimpleCanvas(500, 500);
        rectangle.drawOn(canvas);
        triangle.drawOn(canvas);

        canvas.show();
    }

    public static void testTranslation() {
        Polygon rectangle = new Polygon(4, Color.RED);
        rectangle.addPoint(100, 100);
        rectangle.addPoint(200, 100);
        rectangle.addPoint(200, 300);
        rectangle.addPoint(100, 300);

        System.out.println(rectangle);
        rectangle.translate(10, 20);
        System.out.println(rectangle);
        rectangle.translate(-30, -40);
        System.out.println(rectangle);

        Polygon triangle = new Polygon(3, Color.BLUE);
        triangle.addPoint(300, 300);
        int triangleX = (int) (300+(100 * Math.cos(3.14/3)));
        int triangleY = (int) (300-(100 * Math.sin(3.14/3)));
        triangle.addPoint(triangleX, triangleY);
        triangle.addPoint(400, 300);

        System.out.println(triangle);
        triangle.translate(10, 20);
        System.out.println(triangle);
        triangle.translate(-30, -40);
        System.out.println(triangle);
    }

    public static void testRotation() {
        Polygon rectangle = new Polygon(4, Color.RED);
        rectangle.addPoint(100, 100);
        rectangle.addPoint(200, 100);
        rectangle.addPoint(200, 300);
        rectangle.addPoint(100, 300);
        System.out.println(rectangle);

        rectangle.rotateAround(250, 250, 10);

        SimpleCanvas canvas = new SimpleCanvas(500, 500);
        rectangle.drawOn(canvas);

        canvas.show();
    }

    public static void testComboPolygons() {
        Polygon square1 = new Polygon(4, Color.RED);
        square1.addPoint(140, 140);
        square1.addPoint(180, 140);
        square1.addPoint(180, 180);
        square1.addPoint(140, 180);
        Polygon square2 = new Polygon(4, Color.BLUE);
        square2.addPoint(180, 180);
        square2.addPoint(220, 180);
        square2.addPoint(220, 220);
        square2.addPoint(180, 220);
        Polygon square3 = new Polygon(4, Color.GREEN);
        square3.addPoint(180, 140);
        square3.addPoint(220, 140);
        square3.addPoint(220, 180);
        square3.addPoint(180, 180);
        Polygon square4 = new Polygon(4, Color.BLACK);
        square4.addPoint(140, 180);
        square4.addPoint(180, 180);
        square4.addPoint(180, 220);
        square4.addPoint(140, 220);

        ComboPolygon combo = new ComboPolygon(4);
        combo.addPolygon(square1);
        combo.addPolygon(square2);
        combo.addPolygon(square3);
        combo.addPolygon(square4);

        System.out.println(combo);
        combo.translate(100, 100);
        System.out.println(combo);
        combo.rotateAround(250, 250, 45);
        System.out.println(combo);

        SimpleCanvas c = new SimpleCanvas(500, 500);
        combo.drawOn(c);
        c.show();
    }

    public static void testTranslationFrames() {
        Polygon rectangle = new Polygon(4, Color.RED);
        rectangle.addPoint(100, 100);
        rectangle.addPoint(200, 100);
        rectangle.addPoint(200, 300);
        rectangle.addPoint(100, 300);
        ComboPolygon combo = new ComboPolygon(1);
        combo.addPolygon(rectangle);
        TranslationAnimation anim = new TranslationAnimation(100, 200, 0, 2000, combo);

        SimpleCanvas canvas = new SimpleCanvas(500, 500);
        rectangle.drawOn(canvas);
        canvas.show();
        anim.playOn(canvas);
    }

    public static void testTranslationAnimation() {
        Polygon square1 = new Polygon(4, Color.RED);
        square1.addPoint(140, 140);
        square1.addPoint(180, 140);
        square1.addPoint(180, 180);
        square1.addPoint(140, 180);
        Polygon square2 = new Polygon(4, Color.BLUE);
        square2.addPoint(180, 180);
        square2.addPoint(220, 180);
        square2.addPoint(220, 220);
        square2.addPoint(180, 220);
        Polygon square3 = new Polygon(4, Color.GREEN);
        square3.addPoint(180, 140);
        square3.addPoint(220, 140);
        square3.addPoint(220, 180);
        square3.addPoint(180, 180);
        Polygon square4 = new Polygon(4, Color.BLACK);
        square4.addPoint(140, 180);
        square4.addPoint(180, 180);
        square4.addPoint(180, 220);
        square4.addPoint(140, 220);

        ComboPolygon combo = new ComboPolygon(4);
        combo.addPolygon(square1);
        combo.addPolygon(square2);
        combo.addPolygon(square3);
        combo.addPolygon(square4);

        TranslationAnimation anim = new TranslationAnimation(50, 200, 0, 3000, combo);
        SimpleCanvas canvas = new SimpleCanvas(500, 500);
        canvas.show();
        anim.playOn(canvas);
    }

    public static void testRotationAnimation() {
        Polygon square1 = new Polygon(4, Color.RED);
        square1.addPoint(140, 140);
        square1.addPoint(180, 140);
        square1.addPoint(180, 180);
        square1.addPoint(140, 180);
        Polygon square2 = new Polygon(4, Color.BLUE);
        square2.addPoint(180, 180);
        square2.addPoint(220, 180);
        square2.addPoint(220, 220);
        square2.addPoint(180, 220);
        Polygon square3 = new Polygon(4, Color.GREEN);
        square3.addPoint(180, 140);
        square3.addPoint(220, 140);
        square3.addPoint(220, 180);
        square3.addPoint(180, 180);
        Polygon square4 = new Polygon(4, Color.BLACK);
        square4.addPoint(140, 180);
        square4.addPoint(180, 180);
        square4.addPoint(180, 220);
        square4.addPoint(140, 220);

        ComboPolygon combo = new ComboPolygon(4);
        combo.addPolygon(square1);
        combo.addPolygon(square2);
        combo.addPolygon(square3);
        combo.addPolygon(square4);

        RotationAnimation anim = new RotationAnimation(180, 180, 360, 0, 2000, combo);
        SimpleCanvas canvas = new SimpleCanvas(500, 500);
        canvas.show();
        anim.playOn(canvas);
    }

    public static void testFullAnimation()
    {
        // make 4 squares in corners
        Polygon square1 = new Polygon(4, Color.RED);
        square1.addPoint(25, 25);
        square1.addPoint(75, 25);
        square1.addPoint(75, 75);
        square1.addPoint(25, 75);
        Polygon square2 = new Polygon(4, Color.BLUE);
        square2.addPoint(475, 475);
        square2.addPoint(425, 475);
        square2.addPoint(425, 425);
        square2.addPoint(475, 425);
        Polygon square3 = new Polygon(4, Color.GREEN);
        square3.addPoint(25, 475);
        square3.addPoint(75, 475);
        square3.addPoint(75, 425);
        square3.addPoint(25, 425);
        Polygon square4 = new Polygon(4, Color.BLACK);
        square4.addPoint(475, 25);
        square4.addPoint(475, 75);
        square4.addPoint(425, 75);
        square4.addPoint(425, 25);

        ComboPolygon combo1 = new ComboPolygon(1);
        combo1.addPolygon(square1);

        ComboPolygon combo2 = new ComboPolygon(1);
        combo2.addPolygon(square2);

        ComboPolygon combo3 = new ComboPolygon(1);
        combo3.addPolygon(square3);

        ComboPolygon combo4 = new ComboPolygon(1);
        combo4.addPolygon(square4);

        // animate the 4 squares (first leg)
        TranslationAnimation tanim1 = new TranslationAnimation(400, 0, 0, 2000, combo1);
        TranslationAnimation tanim2 = new TranslationAnimation(-400, 0, 0, 2000, combo2);
        TranslationAnimation tanim3 = new TranslationAnimation(0, -400, 0, 2000, combo3);
        TranslationAnimation tanim4 = new TranslationAnimation(0, 400, 0, 2000, combo4);

        // second leg
        TranslationAnimation tanim5 = new TranslationAnimation(400, 0, 2000, 4000, combo3);
        TranslationAnimation tanim6 = new TranslationAnimation(-400, 0, 2000, 4000, combo4);
        TranslationAnimation tanim7 = new TranslationAnimation(0, -400, 2000, 4000, combo2);
        TranslationAnimation tanim8 = new TranslationAnimation(0, 400, 2000, 4000, combo1);

        // third leg
        TranslationAnimation tanim9 = new TranslationAnimation(400, 0, 4000, 6000, combo2);
        TranslationAnimation tanim10 = new TranslationAnimation(-400, 0, 4000, 6000, combo1);
        TranslationAnimation tanim11 = new TranslationAnimation(0, -400, 4000, 6000, combo4);
        TranslationAnimation tanim12 = new TranslationAnimation(0, 400, 4000, 6000, combo3);

        // fourth leg
        TranslationAnimation tanim13 = new TranslationAnimation(400, 0, 6000, 8000, combo4);
        TranslationAnimation tanim14 = new TranslationAnimation(-400, 0, 6000, 8000, combo3);
        TranslationAnimation tanim15 = new TranslationAnimation(0, -400, 6000, 8000, combo1);
        TranslationAnimation tanim16 = new TranslationAnimation(0, 400, 6000, 8000, combo2);

        // make 4 triangles in center
        Polygon tri1 = new Polygon(3, Color.RED);
        tri1.addPoint(250, 225);
        tri1.addPoint(225, 250);
        tri1.addPoint(150, 150);

        Polygon tri2 = new Polygon(3, Color.BLUE);
        tri2.addPoint(250, 275);
        tri2.addPoint(275, 250);
        tri2.addPoint(350, 350);

        Polygon tri3 = new Polygon(3, Color.GREEN);
        tri3.addPoint(225, 250);
        tri3.addPoint(250, 275);
        tri3.addPoint(150, 350);

        Polygon tri4 = new Polygon(3, Color.BLACK);
        tri4.addPoint(275, 250);
        tri4.addPoint(250, 225);
        tri4.addPoint(350, 150);

        ComboPolygon triangleCombo = new ComboPolygon(4);
        triangleCombo.addPolygon(tri1);
        triangleCombo.addPolygon(tri2);
        triangleCombo.addPolygon(tri3);
        triangleCombo.addPolygon(tri4);

        // animate triangles
        RotationAnimation ranim = new RotationAnimation(250, 250, 360, 0, 8000, triangleCombo);

        AnimationSequence seq = new AnimationSequence(16, 1);
        seq.addTranslation(tanim1);
        seq.addTranslation(tanim2);
        seq.addTranslation(tanim3);
        seq.addTranslation(tanim4);
        seq.addTranslation(tanim5);
        seq.addTranslation(tanim6);
        seq.addTranslation(tanim7);
        seq.addTranslation(tanim8);
        seq.addTranslation(tanim9);
        seq.addTranslation(tanim10);
        seq.addTranslation(tanim11);
        seq.addTranslation(tanim12);
        seq.addTranslation(tanim13);
        seq.addTranslation(tanim14);
        seq.addTranslation(tanim15);
        seq.addTranslation(tanim16);
        seq.addRotation(ranim);
        SimpleCanvas canvas = new SimpleCanvas(500, 500);
        canvas.show();
        seq.playOn(canvas);
    }
}
