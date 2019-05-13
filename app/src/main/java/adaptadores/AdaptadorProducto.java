package adaptadores;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.MainModule.view.SlideshowDialogFragment;
import com.core.hilosdecolores.mainmodule.Producto;


import java.util.ArrayList;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolder> {
    private ArrayList<Producto> android;

    private Context mContext;

    public AdaptadorProducto(ArrayList<Producto> android, Context mContext) {
        this.android = android;
        this.mContext=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tarjeta_producto, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

         viewHolder.title.setText(android.get(i).getProducto_titulo());



        Glide.with(viewHolder.imageView.getContext()).load(android.get(i).getProduct_img())
                .thumbnail(0.5f)
                .error(R.drawable.needle)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);





    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public interface ClickListener {

        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private AppCompatImageView imageView;
        CardView cardView;
        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title_product);
            cardView =(CardView)view.findViewById(R.id.cardview_product);

            imageView=(AppCompatImageView) view.findViewById(R.id.image_product);
        }
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private AdaptadorProducto.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final AdaptadorProducto.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}


