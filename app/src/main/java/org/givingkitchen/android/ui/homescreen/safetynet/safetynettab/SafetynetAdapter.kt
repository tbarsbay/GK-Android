package org.givingkitchen.android.ui.homescreen.safetynet.safetynettab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.givingkitchen.android.R
import org.givingkitchen.android.ui.homescreen.safetynet.SocialServiceProvider
import org.givingkitchen.android.util.setTextIfItExists

class SafetynetAdapter(var items: MutableList<Any>, val facebookSectionExpanded: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val learnMoreClicks: PublishSubject<Boolean> = PublishSubject.create()
    private val joinUsClicks: PublishSubject<Boolean> = PublishSubject.create()
    private val resourcesFilterClicks: PublishSubject<Boolean> = PublishSubject.create()
    private val countyFilterClicks: PublishSubject<Boolean> = PublishSubject.create()
    private val expandFacebookSectionClicks: PublishSubject<Boolean> = PublishSubject.create()
    private val serviceProviderClicks: PublishSubject<Int> = PublishSubject.create()

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_SERVICE_PROVIDER = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                VIEW_TYPE_HEADER
            }
            else -> {
                VIEW_TYPE_SERVICE_PROVIDER
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_safetynet_header, parent, false), learnMoreClicks, joinUsClicks, resourcesFilterClicks, countyFilterClicks, expandFacebookSectionClicks)
            }
            else -> {
                SocialServiceProviderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_social_service_provider, parent, false), serviceProviderClicks)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SocialServiceProviderViewHolder) {
            holder.bind(items[position] as SocialServiceProvider)
        } else {
            (holder as HeaderViewHolder).bind(facebookSectionExpanded)
        }
    }

    override fun getItemCount() = items.size

    fun learnMoreClicks(): Observable<Boolean> = learnMoreClicks
    fun joinUsClicks(): Observable<Boolean> = joinUsClicks
    fun resourcesFilterClicks(): Observable<Boolean> = resourcesFilterClicks
    fun countiesFilterClicks(): Observable<Boolean> = countyFilterClicks
    fun expandFacebookSectionClicks(): Observable<Boolean> = expandFacebookSectionClicks
    fun serviceProviderClicks(): Observable<Int> = serviceProviderClicks
}

class SocialServiceProviderViewHolder(val view: View, private val clicks: PublishSubject<Int>) : RecyclerView.ViewHolder(view) {
    fun bind(socialServiceProvider: SocialServiceProvider) {
        view.findViewById<TextView>(R.id.title_SafetynetRecycler).setTextIfItExists(socialServiceProvider.name)
        view.findViewById<TextView>(R.id.category_SafetynetRecycler).setTextIfItExists(socialServiceProvider.category)
        view.findViewById<TextView>(R.id.description_SafetynetRecycler).setTextIfItExists(socialServiceProvider.description)
        val cellIndex = socialServiceProvider.index
        if (cellIndex != null) {
            view.setOnClickListener { clicks.onNext(cellIndex) }
        }
    }
}

class HeaderViewHolder(val view: View, private val learnMoreClicks: PublishSubject<Boolean>, private val joinUsClicks: PublishSubject<Boolean>, private val resourcesFilterClicks: PublishSubject<Boolean>, private val countyFilterClicks: PublishSubject<Boolean>, private val expandSectionClicks: PublishSubject<Boolean>) : RecyclerView.ViewHolder(view) {
    private var isExpanded = true

    fun bind(expandFacebookSection: Boolean) {
        view.findViewById<TextView>(R.id.learnMoreButton_safetynetTab).setOnClickListener { learnMoreClicks.onNext(false) }
        view.findViewById<TextView>(R.id.joinUsButton_safetynetTab).setOnClickListener { joinUsClicks.onNext(false) }
        view.findViewById<View>(R.id.resourcesFilterTouchTarget_safetynetTab).setOnClickListener { resourcesFilterClicks.onNext(false) }
        view.findViewById<View>(R.id.countiesFilterTouchTarget_safetynetTab).setOnClickListener { countyFilterClicks.onNext(false) }
        view.findViewById<View>(R.id.collapseFacebookButton_safetynetTab).setOnClickListener { expandSectionClicks.onNext(toggleFacebookGroupsExpandedState()) }
        setFacebookGroupsExpandedState(expandFacebookSection)
    }

    private fun toggleFacebookGroupsExpandedState(): Boolean {
        isExpanded = !isExpanded
        return setFacebookGroupsExpandedState(isExpanded)
    }

    private fun setFacebookGroupsExpandedState(expanded: Boolean): Boolean {
        val collapseSectionButton = view.findViewById<ImageView>(R.id.collapseFacebookButton_safetynetTab)
        val facebookGroupsDescription = view.findViewById<View>(R.id.facebookGroupsDescription_safetynetTab)
        val facebookBottomDivider = view.findViewById<View>(R.id.facebookBottomDivider_safetynetTab)
        val joinUsButton = view.findViewById<View>(R.id.joinUsButton_safetynetTab)

        return if (expanded) {
            collapseSectionButton.setImageDrawable(collapseSectionButton.resources.getDrawable(R.drawable.ic_expand_less, collapseSectionButton.context.theme))
            facebookGroupsDescription.visibility = View.VISIBLE
            facebookBottomDivider.visibility = View.VISIBLE
            joinUsButton.visibility = View.VISIBLE
            true
        } else {
            collapseSectionButton.setImageDrawable(collapseSectionButton.resources.getDrawable(R.drawable.ic_expand_more, collapseSectionButton.context.theme))
            facebookGroupsDescription.visibility = View.GONE
            facebookBottomDivider.visibility = View.GONE
            joinUsButton.visibility = View.GONE
            false
        }
    }
}
