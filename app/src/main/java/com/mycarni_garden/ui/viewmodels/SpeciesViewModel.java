package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.repositories.SpeciesRepository;

import java.util.List;

public class SpeciesViewModel extends AndroidViewModel {
    private SpeciesRepository repository;
    private LiveData<List<Species>> allSpecies;

    public SpeciesViewModel(@NonNull Application application) {
        super(application);
        repository = new SpeciesRepository(application);
        allSpecies = repository.getAllSpecies();
    }

    public void insert(FileSet fileSet) {
        repository.insert(fileSet);
    }

    public void update(Species species) {
        repository.update(species);
    }

    public LiveData<List<Species>> getSpeciesOfFamilyFromOrigin(int family_id, int origin_id) { return repository.getSpeciesOfFamilyFromOrigin(family_id, origin_id); }

    public LiveData<List<Species>> getAllSpecies() {
        return allSpecies;
    }

    public class FileSet {
        public String continent;
        public String area;
        public boolean isHighlander;
        public int winterLvl;

        public List<Integer> lighting_ids;
        public List<Integer> substrate_ids;

        public String species_name;
        public int growth;
        public String lifespan;
        public int family_id;
        public String description;

        public FileSet(String continent, String area, boolean isHighlander, int winterLvl, List<Integer> lighting_ids, List<Integer> substrate_ids, String species_name, int growth, String lifespan, int family_id, String description) {
            this.continent = continent;
            this.area = area;
            this.isHighlander = isHighlander;
            this.winterLvl = winterLvl;
            this.lighting_ids = lighting_ids;
            this.substrate_ids = substrate_ids;
            this.species_name = species_name;
            this.growth = growth;
            this.lifespan = lifespan;
            this.family_id = family_id;
            this.description = description;
        }
    }

    public void persistFiles(String continent, String area, boolean isHighlander, int winterLvl, List<Integer> lighting_ids, List<Integer> substrate_ids, String species_name, int growth, String lifespan, int family_id, String description){

        FileSet fileSet = new FileSet(continent, area, isHighlander, winterLvl, lighting_ids, substrate_ids, species_name, growth, lifespan, family_id, description);

        //------- Start persisting

        insert(fileSet);
    }
}
