package hometask14.carrier.service;

import hometask13.carrier.domain.Carrier;
import hometask13.carrier.repo.CarrierRepo;
import hometask13.carrier.service.CarrierInUseDeleteException;
import hometask13.carrier.service.CarrierService;
import hometask13.transportation.domain.Transportation;
import hometask13.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.List;

public class CarrierServiceImpl implements CarrierService {
    private CarrierRepo repo;

    public void setTransportationService(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    private TransportationService transportationService;

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
    public void deleteById(Long id) throws CarrierInUseDeleteException {
        if (id != null) {
            if (transportationService != null) {
                List<Transportation> transportations = transportationService.getAll();
                Carrier carrier = this.getById(id);
                for (Transportation transportation : transportations) {
                    if (transportation.getCarrier().equals(carrier)) {
                        throw new CarrierInUseDeleteException("Carrier " + carrier.getName() + ", id " + id +
                                " cannot be deleted due to it is in use in transportation id " + transportation.getId());
                    }

                }
                if (repo.deleteById(id)) {
                    System.out.println("Carrier deleted successfully");
                } else {
                    System.out.println("There is no carrier with id: " + id + " nothing to delete");
                }
            }
        }
    }

    @Override
    public List<Carrier> getAll() {
        return repo.getAll();
    }


    @Override
    public List<Carrier> getByName(String name) {
        Carrier[] carrierWithReceivedName = repo.getByName(name);
        if (carrierWithReceivedName.length == 0) {
            System.out.println("There are not carriers with such a name : " + name);
        }
        return Arrays.asList(carrierWithReceivedName);
    }

    @Override
    public void printAll() {
        for (Carrier carrier : repo.getAll()) {
            System.out.println(carrier);
        }
    }

}

