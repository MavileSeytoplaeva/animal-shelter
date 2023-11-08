package com.shelter.animalshelter.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.Volunteer;
import com.shelter.animalshelter.repository.VolunteerRepository;
import com.shelter.animalshelter.service.VolunteerService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    @Override
    public Volunteer create(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer getById(Long id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        if (optionalVolunteer.isEmpty()) {
            throw new NotFoundException("По указанному id волонтёр не найден!");
        }
        return optionalVolunteer.get();
    }

    @Override
    public List<Volunteer> getAll() {
        List<Volunteer> all = volunteerRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("Волонтёры не найдены!");
        }
        return all;
    }

    @Override
    public Volunteer update(Volunteer volunteer) {
        Volunteer currentVolunteer = getById(volunteer.getTelegramId());
        EntityUtils.copyNonNullFields(volunteer, currentVolunteer);
        return volunteerRepository.save(currentVolunteer);
    }

    @Override
    public void delete(Volunteer volunteer) {
        volunteerRepository.delete(getById(volunteer.getTelegramId()));
    }

    @Override
    public void deleteById(Long id) {
        volunteerRepository.deleteById(getById(id).getTelegramId());
    }
}