package com.example.bookaholic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen,container,false);

        ImageView logo = view.findViewById(R.id.logo);
        ImageView ind1 = view.findViewById(R.id.ind1);
        ImageView ind2 = view.findViewById(R.id.ind2);
        ImageView ind3 = view.findViewById(R.id.ind3);

        TextView desc = view.findViewById(R.id.desc);
        Button getStarted = view.findViewById(R.id.getStarted);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });

        switch(position)
        {
            case 0:
                logo.setImageResource(R.drawable.logo1);
                ind1.setImageResource(R.drawable.indicator_selected);
                ind2.setImageResource(R.drawable.indicator_unselected);
                ind3.setImageResource(R.drawable.indicator_unselected);
                desc.setText("No one has ever become poor by giving");
                getStarted.setVisibility(View.INVISIBLE);
                break;
            case 1:
                logo.setImageResource(R.drawable.logo2);
                ind1.setImageResource(R.drawable.indicator_unselected);
                ind2.setImageResource(R.drawable.indicator_selected);
                ind3.setImageResource(R.drawable.indicator_unselected);
                desc.setText("Don't let used books become useless books");
                getStarted.setVisibility(View.INVISIBLE);
                break;
            case 2:
                logo.setImageResource(R.drawable.logo3_2);
                ind1.setImageResource(R.drawable.indicator_unselected);
                ind2.setImageResource(R.drawable.indicator_unselected);
                ind3.setImageResource(R.drawable.indicator_selected);
                desc.setText("Specially designed for Freshers to get Drafters, Containers, Calculators, Notes and Books");
                getStarted.setVisibility(View.VISIBLE);
                break;

        }

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
