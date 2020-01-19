package hometask9.carrier.service;

import hometask9.application.serviceholder.ServiceHolder;
import hometask9.carrier.domain.Carrier;
import hometask9.carrier.repo.CarrierRepo;
import hometask9.transportation.domain.Transportation;
import hometask9.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.List;

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
    public void deleteById(Long id) throws CarrierInUseDeleteException {
        try {
            if (ServiceHolder.getInstance() != null) {
                TransportationService transportationService = ServiceHolder.getInstance().getTransportationService();
                List<Transportation> transportations = transportationService.getAll();
                for (Transportation transportation : transportations) {
                    Carrier carrier = this.getById(id);
                    if (transportation.getCarrier().equals(carrier)) {
                        throw new CarrierInUseDeleteException("Carrier " + carrier.getName() + ", id " + id +
                                " cannot be deleted due to it is in use in transportation id " + transportation.getId());
                    }

                }
                if (repo.deleteById(id)) {
                    System.out.println("Carrier deleted successfully");
                } else {
                    throw new IllegalArgumentException("There is no carrier with id: " + id + " nothing to delete");
                }
            }
        }
        catch (NullPointerException npe) {
            System.out.println("Carrier cannot be deleted by id, because its id is null");
        }
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

