package hometask7.carrier.service;

import hometask7.carrier.domain.Carrier;
import hometask7.carrier.repo.CarrierArrayRepoImpl;
import hometask7.carrier.repo.CarrierCollectionRepoImpl;
import hometask7.carrier.repo.CarrierRepo;

public class CarrierServiceImpl implements CarrierService {
    private CarrierRepo repo;

    public CarrierServiceImpl(CarrierRepo repo) {
        this.repo = repo;
    }

    @Override
    public void add(Carrier carrier) {
        boolean carrierWasntAddedBefore = (carrier != null && carrier.getId() == null);
        if (carrierWasntAddedBefore) {
            repo.add(carrier);
            System.out.println("Carrier has been added successfully");
        } else if (carrier == null) {
            System.out.println("Carrier hasn't been added: nothing to add");
        } else {
            repo.deleteById(carrier.getId());
            repo.add(carrier);
            System.out.println("Carrier has been rewritten");
        }
    }

    @Override
    public Carrier getById(Long id) {
        Carrier foundRepo = repo.getById(id);
        if (foundRepo == null) {
            System.out.println("Carrier with id: " + id + " hasn't been found");
        }
        return foundRepo;
    }

    @Override
    public void deleteById(Long id) {
        if (repo.deleteById(id)) {
            System.out.println("Carrier deleted successfully");
        } else {
            System.out.println("There is no carrier with id: " + id + " nothing to delete");
        }
    }

    @Override
    public Carrier[] getByName(String name) {
        Carrier[] carrierWithReceivedName = repo.getByName(name);
        if (carrierWithReceivedName.length == 0) {
            System.out.println("There are not carriers with such a name : " + name);
        }
        return carrierWithReceivedName;
    }

}

