package hometask23.carrier.service;

import hometask23.carrier.domain.Carrier;
import hometask23.carrier.repo.CarrierRepo;
import hometask23.transportation.domain.Transportation;
import hometask23.transportation.service.TransportationService;

import java.util.List;
import java.util.Optional;

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
        boolean carrierWasntAddedBefore = (carrier != null && !this.getById(carrier.getId()).isPresent());
        if (carrierWasntAddedBefore) {
            repo.add(carrier);
            System.out.println("Carrier has been added successfully");
        } else if (carrier == null) {
            System.out.println("Carrier hasn't been added: nothing to add");
        } else {
            repo.update(carrier);
            System.out.println("Carrier has been rewritten");
        }
    }

    @Override
    public Optional<Carrier> getById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return repo.getById(id);
    }

    @Override
    public void deleteById(Long id) throws CarrierInUseDeleteException {
        if (id != null) {
            if (transportationService != null) {
                List<Transportation> transportations = transportationService.getAll();
                Carrier carrier = this.getById(id).get();
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
        List<Carrier> carrierWithReceivedName = repo.getByName(name);
        if (carrierWithReceivedName.isEmpty()) {
            System.out.println("There are not carriers with such a name : " + name);
        }
        return carrierWithReceivedName;
    }

    @Override
    public void printAll() {
        for (Carrier carrier : repo.getAll()) {
            System.out.println(carrier);
        }
    }

}

