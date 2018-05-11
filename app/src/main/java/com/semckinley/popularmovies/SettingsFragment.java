package com.semckinley.popularmovies;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.semckinley.popularmovies.R;

/**
 * Created by stephen.mckinley on 5/8/18.
 */

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
