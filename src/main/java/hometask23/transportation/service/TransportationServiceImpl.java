package hometask23.transportation.service;

import hometask23.transportation.domain.Transportation;
import hometask23.transportation.repo.TransportationRepo;

import java.util.List;
import java.util.Optional;

public class TransportationServiceImpl implements TransportationService {
    private TransportationRepo repo;

    public TransportationServiceImpl(TransportationRepo repo) {
        this.repo = repo;
    }


    @Override
    public void add(Transportation transportation) {
        boolean transportationWasntAddedBefore = (transportation != null && !this.getById(transportation.getId()).isPresent());
        if (transportationWasntAddedBefore) {
            repo.add(transportation);
            System.out.println("Transportation has been added successfully");
        } else if (transportation == null) {
            System.out.println("Transportation hasn't been added: nothing to add");
        } else {
            repo.update(transportation);
            System.out.println("Transportation has been rewritten");
        }
    }

    @Override
    public Optional<Transportation> getById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return repo.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {

            if (repo.deleteById(id)) {
                System.out.println("Transportation deleted successfully");
            } else {
                System.out.println("There is no transportation with id: " + id + " nothing to delete");
            }
        }
        else {
            System.out.println("Transportation cannot be deleted because its id is null");
        }
    }

    @Override
    public List<Transportation> getAll() {
        return repo.getAll();
    }

    @Override
    public void printAll() {
        for (Transportation transportation : repo.getAll()) {
            System.out.println(transportation);
        }
    }

}
