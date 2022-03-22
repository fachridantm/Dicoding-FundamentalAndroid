package com.dicoding.fundamental.mysettingpreference

class MyPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}