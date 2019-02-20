package org.thegivingkitchen.android.thegivingkitchen.ui.homescreen.home

import org.thegivingkitchen.android.thegivingkitchen.R
import org.thegivingkitchen.android.thegivingkitchen.ui.homescreen.about.AboutFragment
import org.thegivingkitchen.android.thegivingkitchen.ui.homescreen.assistance.AssistanceFragment
import org.thegivingkitchen.android.thegivingkitchen.ui.homescreen.events.EventsFragment
import org.thegivingkitchen.android.thegivingkitchen.ui.homescreen.give.GiveFragment
import org.thegivingkitchen.android.thegivingkitchen.ui.homescreen.safetynet.safetynettab.SafetynetFragment

enum class HomeSection(val id: Int, val screenName: String) {
    ABOUT(R.id.aboutFragment, AboutFragment::class.java.simpleName),
    EVENTS(R.id.eventsFragment, EventsFragment::class.java.simpleName),
    ASSISTANCE(R.id.assistanceFragment, AssistanceFragment::class.java.simpleName),
    GIVE(R.id.giveFragment, GiveFragment::class.java.simpleName),
    SAFETYNET(R.id.safetynetFragment, SafetynetFragment::class.java.simpleName)
}