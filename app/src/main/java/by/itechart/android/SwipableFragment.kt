package by.itechart.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.itechart.android.data.entities.CardItem
import kotlinx.android.synthetic.main.fragment_swipable.*

class SwipableFragment : Fragment(R.layout.fragment_swipable) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CardsAdapter()
        swipableViewPager.adapter = adapter

        adapter.items = listOf(
            CardItem(
                "What is Lorem Ipsum?",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
                R.drawable.test_image_1,
                R.layout.view_swiped_type1
            ),
            CardItem(
                "Title #2",
                "description #2",
                R.drawable.test_image_2,
                R.layout.view_swiped_type2
            ),
            CardItem(
                "Title #3",
                "description #3",
                R.drawable.test_image_1,
                R.layout.view_swiped_type1
            ),
            CardItem(
                "Title #4",
                "description #4",
                R.drawable.test_image_2,
                R.layout.view_swiped_type2
            )
        )

        swipableBottomSheetView.setContentLayout(LayoutInflater.from(context).inflate(adapter.items[0].layout, null))
        swipableViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) =
                swipableBottomSheetView.setContentLayout(
                    LayoutInflater.from(context).inflate(adapter.items[position].layout, null)
                )
        })
    }
}