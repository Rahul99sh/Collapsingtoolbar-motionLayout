package com.pro.arachnomeshuiuxassignment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.pro.arachnomeshuiuxassignment.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int lastScrollY = 0;
    boolean isHidden = false;
    int bottomAppBarHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomAppBarHeight = binding.bottomAppBar.getHeight();

        List<String> list = Arrays.asList("Super StockList","Super StockList", "Super StockList", "Super StockList", "Super StockList");
        List<String> list2 = Arrays.asList("All","All", "All", "All", "All");
        List<String> list3 = Arrays.asList("Distributor","Distributor", "Distributor", "Distributor", "Distributor");
        List<String> list4 = Arrays.asList("Retailer","Retailer", "Retailer", "Retailer", "Retailer");

        adapter a = new adapter(this);
        binding.recyclerview.setAdapter(a);
        a.setList(list);
        binding.nested.setOnScrollChangeListener((View.OnScrollChangeListener) (view, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > lastScrollY && !isHidden) { // Scrolling down
                binding.bottomAppBar.performHide();
                animateFABDown();
                isHidden = true;
            } else if (scrollY < lastScrollY && isHidden) { // Scrolling up
                binding.bottomAppBar.performShow();
                animateFABUp();
                isHidden = false;
            }
            lastScrollY = scrollY;
        });

        TextView[] textViews = {
                binding.home,
                binding.buyer,
                binding.attendence,
                binding.orders,
                binding.more
        };

// Define corresponding drawables for each TextView
        int[] textDrawableIds = {
                R.drawable.home,
                R.drawable.buyers,
                R.drawable.attendence,
                R.drawable.bag,
                R.drawable.more
        };

        int[] textDrawableIds_2 = {
                R.drawable.home2,
                R.drawable.buyers2,
                R.drawable.attendence,
                R.drawable.bag2,
                R.drawable.more2
        };

        for (int i = 0; i < textViews.length; i++) {
            int finalI = i;
            textViews[i].setOnClickListener(v -> {
                for (int j = 0; j < textViews.length; j++) {
                    if (finalI == j) {
                        // Apply selected properties
                        textViews[j].setTextColor(getResources().getColor(R.color.primary));
                        textViews[j].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        textViews[j].setCompoundDrawablesWithIntrinsicBounds(0, textDrawableIds_2[j], 0, 0);
                        // Apply other selected styles
                    } else {
                        // Apply default properties for other TextViews
                        textViews[j].setTextColor(getResources().getColor(R.color.text_sec));
                        textViews[j].setTypeface(Typeface.DEFAULT);
                        textViews[j].setCompoundDrawablesWithIntrinsicBounds(0, textDrawableIds[j], 0, 0);
                        // Apply other default styles
                    }
                }
            });
        }


        List<CardView> cardViews = Arrays.asList(
                binding.all,
                binding.superlist,
                binding.distributor,
                binding.retailer
        );

        for (int i = 0; i < cardViews.size(); i++) {
            final int index = i;
            cardViews.get(i).setOnClickListener(v -> {
                for (int j = 0; j < cardViews.size(); j++) {
                    if (j == index) {
                        // Apply changes to the clicked card
                        cardViews.get(j).setCardBackgroundColor(getResources().getColor(R.color.tab_active));
                        switch (j) {
                            case 0:
                                a.setList(list2);
                                break;
                            case 1:
                                a.setList(list);
                                break;
                            case 2:
                                a.setList(list3);
                                break;
                            case 3:
                                a.setList(list4);
                                break;
                            default:
                                break;
                        }
                    } else {
                        // Reset other cards to default
                        cardViews.get(j).setCardBackgroundColor(getResources().getColor(R.color.primary_dark));
                        // Reset the icons or backgrounds
                    }
                }
            });
        }





    }

    private void animateFABUp() {
        // Shift FAB up animation
        binding.fab.animate()
                .translationY(0f) // Shift FAB to its original position
                .setDuration(200) // Set duration for the animation
                .start();
    }

    private void animateFABDown() {
        // Shift FAB down animation
        float distanceToShift = getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._45sdp); // Adjust this distance as needed
        binding.fab.animate()
                .translationY(distanceToShift) // Shift FAB down by a specific distance
                .setDuration(200) // Set duration for the animation
                .start();
    }

}