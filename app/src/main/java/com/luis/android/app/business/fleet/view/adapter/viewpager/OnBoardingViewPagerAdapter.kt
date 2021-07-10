package com.luis.android.app.business.fleet.view.adapter.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.luis.android.app.business.fleet.MethodUtils.Companion.getStringByLanguage
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.StringUtils
import com.luis.android.app.business.fleet.domain.model.OnBoardingPageModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class OnBoardingViewPagerAdapter(
    var onBoardingList: ArrayList<OnBoardingPageModel>,
    val context: Context
) : PagerAdapter() {



    override fun getCount(): Int {
        return onBoardingList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj as CardView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.onboarding_viewpager_item, container, false)

        val onBoardingImageView = itemView.findViewById<ImageView>(R.id.image_view)
        val title = itemView.findViewById<TextView>(R.id.onboarding_title_tv)
        val subtitle = itemView.findViewById<TextView>(R.id.onboarding_sub_title_tv)
        val body = itemView.findViewById<TextView>(R.id.onboarding_body_tv)
        val onboardingDinamicImageView = itemView.findViewById<ImageView>(R.id.onboarding_logo_iv)

        title.text = getStringByLanguage(onBoardingList.get(position).title)
        subtitle.text = getStringByLanguage(onBoardingList.get(position).subtitle)
        body.text = getStringByLanguage(onBoardingList.get(position).body)

        if (onBoardingList.get(position).image.isNotEmpty()) {
            Picasso.get().load(onBoardingList.get(position).image).into(
                onboardingDinamicImageView,
                object : Callback {
                    override fun onSuccess() {
                        println("Success")
                    }

                    override fun onError(e: Exception) {
                        println("Error: " + e.message)
                    }
                })
        }

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as CardView)
    }

    fun updateData(onBoardingList: ArrayList<OnBoardingPageModel>) {
        this.onBoardingList = onBoardingList
        notifyDataSetChanged()
    }

}