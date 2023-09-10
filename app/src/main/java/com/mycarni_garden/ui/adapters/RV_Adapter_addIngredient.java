package com.mycarni_garden.ui.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.ui.models.row_create_ingredient;

import java.util.List;

public class RV_Adapter_addIngredient extends RecyclerView.Adapter<RV_Adapter_addIngredient.ViewHolder> {

    private List<row_create_ingredient> Ingredients;
    private RecyclerView rv_addIngredients;
    private View view;
    private TextWatcher textWatcher;

    public RV_Adapter_addIngredient(RecyclerView rv_addIngredients, List<row_create_ingredient> Ingredients) {
        this.Ingredients = Ingredients;
        this.rv_addIngredients = rv_addIngredients;
        ViewGroup parent = (ViewGroup) rv_addIngredients.getParent();
        row_create_ingredient initial_column = addRow(parent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    // Watch text of latest column, add new column when filled
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        row_create_ingredient column = addRow(parent);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            // Recursively add a column if under 6 in total (Usually >= 3 components)
            @Override
            public void afterTextChanged(Editable s) {
                int position = Ingredients.lastIndexOf(column);
                if (Ingredients.size() < 5 && column.isFilled() && position == Ingredients.size() - 1) {
                    column.removeOnTextChangedListener(textWatcher);
                    row_create_ingredient newRow = addRow(parent);
                }
            }
        };

        setTextWatcher(column);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        row_create_ingredient ingredient = Ingredients.get(position);
        setTextWatcher(Ingredients.get((Ingredients.size())-1));
    }

    @Override
    public int getItemCount() {
        return Ingredients.size();
    }

    private row_create_ingredient addRow(ViewGroup parent){
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_row_substrate_create_ingredient, parent, false);
        row_create_ingredient column = new row_create_ingredient(view);
        Ingredients.add(column);
        //notifyItemInserted(Ingredients.size()-1);
        return column;
    }

    public void setTextWatcher(row_create_ingredient column) {
        column.setOnTextChangedListener(textWatcher);
    }
}