package com.mycarni_garden.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.repositories.FamiliesRepository;
import com.mycarni_garden.ui.viewmodels.FamiliesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainViewModel extends ViewModel {
    private List<familySimulation> familiesInfoList = new ArrayList<>();
    
    private class familySimulation {
        public int id;
        public String name;
        
        public familySimulation(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    //-----------------------------------------
    
    public List getFamiliesInfoList() {
        return familiesInfoList;
    }

    public void addToFamiliesInfoList(int id, String name) {
        if (findFamilyIdByName(name)==-1) {
            System.out.println("Add new family sim: " + name);
            familiesInfoList.add(new familySimulation(id, name));
        }
    }
    
    public int findFamilyIdByName(String name){
        System.out.println("findFamilyIdByName");
        for (int i = 0; i < familiesInfoList.size(); i++) {
            familySimulation currentSim = familiesInfoList.get(i);
            if (Objects.equals(currentSim.name, name)) {
                System.out.println("Found id '" + currentSim.id + "' for " + currentSim.name);
                return currentSim.id;
            }
        }
        System.out.println("Found none");
        return -1;
    }
}