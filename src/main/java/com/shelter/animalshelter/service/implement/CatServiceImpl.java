package com.shelter.animalshelter.service.implement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.repository.CatRepository;
import com.shelter.animalshelter.service.CatService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;


    @Override
    public Cat create(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat getById(Long cat_id) {
        return catRepository.findById(cat_id).get();
    }
    @Override
    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    @Override
    public Cat update(Cat cat) {
        Optional<Cat> catId = catRepository.findById(cat.getCat_id());
        if (catId.isEmpty()) {
            throw new NotFoundException("Кота нет");
        }
        Cat currentCat = catId.get();
        EntityUtils.copyNonNullFields(cat, currentCat);
        return catRepository.save(currentCat);
    }

    @Override
    public void remove(Long cat_id) {
        catRepository.deleteById(getById(cat_id).getCat_id());
    }
}