package com.dicoding.fundamental.mysettingpreference

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class MyPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}