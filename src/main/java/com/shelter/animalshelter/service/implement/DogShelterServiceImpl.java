package com.shelter.animalshelter.service.implement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.animals.Dog;
import com.shelter.animalshelter.model.shelters.DogShelter;
import com.shelter.animalshelter.repository.DogSheltersRepository;
import com.shelter.animalshelter.service.ShelterService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogShelterServiceImpl implements ShelterService<DogShelter, Dog> {

    private final DogSheltersRepository dogRepository;


    @Override
    public DogShelter addShelter(DogShelter shelter) {
        return dogRepository.save(shelter);
    }

    @Override
    public DogShelter updateShelter(DogShelter shelter) {
        DogShelter currentShelter = getSheltersId(shelter.getId());
        EntityUtils.copyNonNullFields(shelter, currentShelter);
        return dogRepository.save(currentShelter);
    }

    @Override
    public DogShelter getSheltersId(long id) {
        Optional<DogShelter> shelterId = dogRepository.findById(id);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Собачки остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public DogShelter getShelterByName(String name) {
        Optional<DogShelter> shelterId = dogRepository.findByName(name);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Собачки остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public List<DogShelter> getShelter() {
        return dogRepository.findAll();
    }

    @Override
    public List<Dog> getAnimal(long index) {
        return getSheltersId(index).getList();
    }


    @Override
    public String delShelter(long index) {
        String result;
        Optional<DogShelter> dogShelter = dogRepository.findById(index);
        if (dogShelter.isPresent()) {
            dogRepository.deleteById(index);
            result = "Запись удалена";
        } else {
            throw new NotFoundException("Собачки без приюта. Мы его не нашли(");
        }
        return result;
    }
}