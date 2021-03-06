package org.givingkitchen.android.util

interface FragmentBackPressedListener {
    /**
     * @return true if this fragment consumes the back pressed event, if not then false
     */
    fun onBackPressed(): Boolean
}