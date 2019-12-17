package hometask7.transportation.service;

import hometask7.transportation.domain.Transportation;
import hometask7.transportation.repo.TransportationArrayRepoImpl;
import hometask7.transportation.repo.TransportationCollectionRepoImpl;
import hometask7.transportation.repo.TransportationRepo;

public class TransportationServiceImpl implements TransportationService {
    private TransportationRepo repo;

    public TransportationServiceImpl() {
        TransportationRepo defaultRepo = new TransportationCollectionRepoImpl();
        repo = defaultRepo;
    }

    public void setNewRepoAsArray() {
        repo = new TransportationArrayRepoImpl();
    }

    public void setNewRepoAsCollection() {
        repo = new TransportationCollectionRepoImpl();
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
        if (repo.deleteById(id)) {
            System.out.println("Transportation deleted successfully");
        } else {
            System.out.println("There is no transportation with id: " + id + " nothing to delete");
        }
    }

}
