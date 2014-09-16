package com.venmo.android.pin;

import android.content.Context;

public class PinFragmentConfiguration {

    public static final int UNLIMITED_TRIES = -1;

    private boolean mShouldVibrateOnKey = true;
    private int mKeyVibrationDuration = 10;
    private Validator mValidator;
    private int mMaxTries = UNLIMITED_TRIES;
    private TryDepletionListener mTryDepletionListener;

    public PinFragmentConfiguration(Context c) {
        mValidator = new DefaultValidator(c);
    }

    public PinFragmentConfiguration(Validator validator) {
        mValidator = validator;
    }

    @SuppressWarnings("unused")
    public PinFragmentConfiguration validator(Validator validator) {
        if (validator == null) {
            throw new IllegalArgumentException("Cannot pass in a null validator");
        }
        mValidator = validator;
        return this;
    }

    public Validator validator() {
        return mValidator;
    }

    /**
     * @param max number of allowed errors per session. For no maximum, use {@link
     * com.venmo.pin.PinFragmentConfiguration#UNLIMITED_TRIES}
     * @param depletionListener listener to take action when tries have been used up
     */
    @SuppressWarnings("unused")
    public PinFragmentConfiguration maxTries(int max, TryDepletionListener depletionListener) {
        if (!(max > 0) && max != UNLIMITED_TRIES) {
            throw new IllegalArgumentException(
                    "Max Tries should either be greater than 0 or PinFragmentConfiguration.UNLIMITED_TRIES");
        }
        mMaxTries = max;
        mTryDepletionListener = depletionListener;
        return this;
    }

    public TryDepletionListener tryDepletionListener() {
        return mTryDepletionListener;
    }

    public int maxTries() {
        return mMaxTries;
    }

    @SuppressWarnings("unused")
    public PinFragmentConfiguration vibrateOnKey(boolean vibrate) {
        mShouldVibrateOnKey = vibrate;
        return this;
    }

    public boolean shouldVibrateOnKey() {
        return mShouldVibrateOnKey;
    }

    /**
     * @param duration duration of the vibration on key press. Throws {@code IllegalStateException}
     * if vibration state has been set to false. see {@link #vibrateOnKey(boolean)}
     */
    public PinFragmentConfiguration vibrationDuration(int duration) {
        mShouldVibrateOnKey = true;
        mKeyVibrationDuration = duration;
        return this;
    }

    public int vibrateDuration() {
        return mKeyVibrationDuration;
    }

}
