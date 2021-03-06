package com.zerdi.sampledeliveryapp.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zerdi.sampledeliveryapp.R
import com.zerdi.sampledeliveryapp.model.entity.meal.Meal
import com.zerdi.sampledeliveryapp.utils.listener.IMealOnClick

class MealItemAdapter : RecyclerView.Adapter<MealItemAdapter.ViewHolder>() {
    private lateinit var mealList: List<Meal>
    private var basketAnimationVisibility: Boolean = false
    private var listener: IMealOnClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTextView: AppCompatTextView = view.findViewById(R.id.nameTextView)
        private val detailTextView: AppCompatTextView = view.findViewById(R.id.detailTextView)
        private val priceTextView: AppCompatTextView = view.findViewById(R.id.priceTextView)
        private val imageView: AppCompatImageView = view.findViewById(R.id.iconImageView)
        private val basketImageButton: AppCompatImageButton =
            view.findViewById(R.id.basketImageButton)
        private val basketAnimation: LottieAnimationView = view.findViewById(R.id.basketAnimation)
        private val containerLinearLayout: LinearLayout =
            view.findViewById(R.id.containerLinearLayout)

        fun bind(
            meal: Meal,
            listener: IMealOnClick?,
            basketAnimationVisibility: Boolean,
            context: Context
        ) {
            val options = RequestOptions().placeholder(R.drawable.no_data_yellow)
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(options)
                .load(meal.imageUrl).into(imageView)
            nameTextView.text = meal.name
            detailTextView.text = meal.ingredients.joinToString(separator = ",") { it }
            priceTextView.text = context.getString(R.string.price_string, "$", meal.price)

            containerLinearLayout.setOnClickListener {
                listener?.onClick(meal)
            }
            basketImageButton.setOnClickListener {
                listener?.onClickBasket(meal)
            }

            basketImageButton.isVisible = basketAnimationVisibility.not()
            basketAnimation.isVisible = basketAnimationVisibility
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mealList[position]
        val context = holder.itemView.context
        holder.bind(item, listener, basketAnimationVisibility, context)
    }

    override fun getItemCount(): Int = mealList.size

    fun setData(mealList: List<Meal>?) {
        basketAnimationVisibility = false
        mealList?.let {
            this.mealList = mealList
            notifyDataSetChanged()
        }
    }

    fun isBasketAnimationVisible(meal: Meal, isVisible: Boolean) {
        basketAnimationVisibility = isVisible
        val position = mealList.indexOf(meal)
        notifyItemChanged(position)
    }

    fun addListener(listener: IMealOnClick?) {
        this.listener = listener
    }

}