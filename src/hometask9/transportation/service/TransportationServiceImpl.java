package hometask9.transportation.service;

import hometask9.cargo.domain.Cargo;
import hometask9.transportation.domain.Transportation;
import hometask9.transportation.repo.TransportationRepo;

import java.util.List;

public class TransportationServiceImpl implements TransportationService {
    private TransportationRepo repo;

    public TransportationServiceImpl(TransportationRepo repo) {
        this.repo = repo;
    }


    @Override
    public void add(Transportation transportation) {
        boolean transportationWasntAddedBefore = (transportation != null && transportation.getId() == null);
        if (transportationWasntAddedBefore) {
            repo.add(transportation);
            System.out.println("Transportation has been added successfully");
        } else if (transportation == null) {
            System.out.println("Transportation hasn't been added: nothing to add");
        } else {
            repo.deleteById(transportation.getId());
            repo.add(transportation);
            System.out.println("Transportation has been rewritten");
        }
    }

    @Override
    public Transportation getById(Long id) {
        Transportation foundRepo = repo.getById(id);
        if (foundRepo == null) {
            System.out.println("Transportation with id: " + id + " hasn't been found");
        }
        return foundRepo;
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (repo.deleteById(id)) {
                System.out.println("Transportation deleted successfully");
            } else {
                System.out.println("There is no transportation with id: " + id + " nothing to delete");
            }
        } catch (NullPointerException npe) {
            System.out.println("Transportation cannot be deleted by id, because its id is null");
        }
    }

    @Override
    public List<Transportation> getAll() {
        return repo.getAll();
    }

    @Override
    public void printAll() {
        for (Transportation transportation: repo.getAll()) {
            System.out.println(transportation);
        }
    }

}