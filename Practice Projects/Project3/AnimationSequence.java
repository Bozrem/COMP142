package Project3;

/**
 * An AnimationSequence is a collection of animations.  Each animation is either a translation or
 * rotation applied to a ComboPolygon.  Because each animation starts and ends at a specific moment
 * in time, we can use this class to begin and end animations whenever we want, running them simultaneously
 * or in a sequence.
 */
public class AnimationSequence {
    private TranslationAnimation[] translations;
    private RotationAnimation[] rotations;
    private int nextTransIndex, nextRotIndex;

    public AnimationSequence(int numTranslations, int numRotations) {
        translations = new TranslationAnimation[numTranslations];
        rotations = new RotationAnimation[numRotations];
        nextTransIndex = 0;
        nextRotIndex = 0;
    }

    /** Add a new TranslationAnimation to this sequence. */
    public void addTranslation(TranslationAnimation t)
    {
        translations[nextTransIndex] = t;
        nextTransIndex++;
    }

    /** Add a new RotationAnimation to this sequence. */
    public void addRotation(RotationAnimation t)
    {
        rotations[nextRotIndex] = t;
        nextRotIndex++;
    }

    /** Retrieve the total time this AnimationSequence will last. */
    private int getMaxTime()
    {
        int highestMs = 0;
        for (TranslationAnimation translationAnimation : translations){
            if (translationAnimation.getEndTimeMillis() > highestMs) highestMs = translationAnimation.getEndTimeMillis();
        }
        for (RotationAnimation rotationAnimation : rotations){
            if (rotationAnimation.getEndTimeMillis() > highestMs) highestMs = rotationAnimation.getEndTimeMillis();
        }
        return highestMs;
    }

    public void playOn(SimpleCanvas canvas)
    {
        // Display all the initial polygons, before they start moving.
        for (int i = 0; i < translations.length; i++) {
            translations[i].getPolygon().drawOn(canvas);
        }

        for (int i = 0; i < rotations.length; i++) {
            rotations[i].getPolygon().drawOn(canvas);
        }
        canvas.update();
        canvas.waitForClick();

        int currentMs = 0;
        System.out.println(getMaxTime());
        while (currentMs <= getMaxTime()){

            canvas.clear();

            for (TranslationAnimation translation : translations){
                if (currentMs >= translation.getStartTimeMillis() && currentMs <= translation.getEndTimeMillis()){
                    translation.advanceToNextFrame();
                    translation.getPolygon().drawOn(canvas);
                }
            }
            for (RotationAnimation rotation : rotations){
                if (currentMs >= rotation.getStartTimeMillis() && currentMs <= rotation.getEndTimeMillis()){
                    rotation.advanceToNextFrame();
                    rotation.getPolygon().drawOn(canvas);
                }
            }
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

