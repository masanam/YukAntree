package com.wartatv.yukantree.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.activity.BaseActivity;
import com.wartatv.yukantree.activity.ProductActivity;
import com.wartatv.yukantree.model.Cart;
import com.wartatv.yukantree.model.Product;
import com.wartatv.yukantree.util.localstorage.LocalStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by .
 * www.wartatv.com
 */
public class HostAdapter extends RecyclerView.Adapter<HostAdapter.MyViewHolder> {

    List<Product> productList;
    Context context;
    String Tag;

    LocalStorage localStorage;
    Gson gson;
    List<Cart> cartList = new ArrayList<>();
    String _quantity, _price, _attribute, _subtotal;

    public HostAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    public HostAdapter(List<Product> productList, Context context, String tag) {
        this.productList = productList;
        this.context = context;
        Tag = tag;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;
        if (Tag.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_new_home_products, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_new_products, parent, false);
        }

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Product product = productList.get(position);
        localStorage = new LocalStorage(context);
        gson = new Gson();
        cartList = ((BaseActivity) context).getCartList();
        holder.quantity.setText("1");

        holder.title.setText(product.getTitle());
        holder.price.setText(product.getContact());
        holder.currency.setText(product.getCurrency());
        holder.attribute.setText(product.getCity());
        holder.host_address.setText(product.getAddress());
        holder.host_phone.setText(product.getPhone());
        holder.shopNow.setText("get Loket");

        Picasso.get().load(product.getImage()).error(R.drawable.no_image).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Log.d("Error : ", e.getMessage());
            }
        });

//        if (!cartList.isEmpty()) {
//            for (int i = 0; i < cartList.size(); i++) {
//                if (cartList.get(i).getId().equalsIgnoreCase(product.getId())) {
//                    holder.shopNow.setVisibility(View.GONE);
//                    holder.quantity_ll.setVisibility(View.VISIBLE);
//                    holder.quantity.setText(cartList.get(i).getQuantity());
//
//                }
//            }
//        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("type",productList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("type",productList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return productList.size();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, host_address, host_phone, attribute, currency, price, shopNow;
        ProgressBar progressBar;
        LinearLayout quantity_ll;
        TextView plus, minus, quantity;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            host_address = itemView.findViewById(R.id.host_address);
            host_phone = itemView.findViewById(R.id.host_phone);

            imageView = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            attribute = itemView.findViewById(R.id.product_attribute);
            price = itemView.findViewById(R.id.product_price);
            currency = itemView.findViewById(R.id.product_currency);
            shopNow = itemView.findViewById(R.id.shop_now);
            progressBar = itemView.findViewById(R.id.progressbar);
            quantity_ll = itemView.findViewById(R.id.quantity_ll);
            quantity = itemView.findViewById(R.id.quantity);
            plus = itemView.findViewById(R.id.quantity_plus);
            minus = itemView.findViewById(R.id.quantity_minus);
            cardView = itemView.findViewById(R.id.card_view);

        }
    }
}
