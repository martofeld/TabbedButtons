package com.mfeldsztejn.tabbedbuttons;

import android.content.Context;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by mfeldsztejn on 12/22/16.
 */
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml", sdk = 18, constants = BuildConfig.class)
public abstract class RobolectricTest {

    protected Context getContext() {
        return RuntimeEnvironment.application;
    }

}
