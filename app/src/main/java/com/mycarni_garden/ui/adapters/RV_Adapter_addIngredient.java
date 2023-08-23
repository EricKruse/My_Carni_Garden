package com.mycarni_garden.ui.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.ui.models.column_create_ingredient;

import java.util.ArrayList;
import java.util.List;

public class RV_Adapter_addIngredient extends RecyclerView.Adapter<RV_Adapter_addIngredient.ViewHolder> {

    private List<column_create_ingredient> Ingredients;
    private RecyclerView rv_addIngredients;
    private View view;
    private TextWatcher textWatcher;

    public RV_Adapter_addIngredient(RecyclerView rv_addIngredients, List<column_create_ingredient> Ingredients) {
        this.Ingredients = Ingredients;
        this.rv_addIngredients = rv_addIngredients;
        ViewGroup parent = (ViewGroup) rv_addIngredients.getParent();
        column_create_ingredient initial_column = addColumn(parent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        column_create_ingredient column = addColumn(parent);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int position = Ingredients.lastIndexOf(column);
                if (Ingredients.size() < 5 && column.isFilled() && position == Ingredients.size() - 1) {
                    column.removeOnTextChangedListener(textWatcher);
                    column_create_ingredient newColumn = addColumn(parent);
                }
            }
        };

        setTextWatcher(column);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        column_create_ingredient ingredient = Ingredients.get(position);
        setTextWatcher(Ingredients.get((Ingredients.size())-1));
    }

    @Override
    public int getItemCount() {
        return Ingredients.size();
    }

    private column_create_ingredient addColumn(ViewGroup parent){
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_column_substrate_create_ingredient, parent, false);
        column_create_ingredient column = new column_create_ingredient(view);
        Ingredients.add(column);
        //notifyItemInserted(Ingredients.size()-1);
        return column;
    }

    public void setTextWatcher(column_create_ingredient column) {
        column.setOnTextChangedListener(textWatcher);
    }
}