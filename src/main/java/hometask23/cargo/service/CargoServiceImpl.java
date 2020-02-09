package hometask23.cargo.service;

import hometask23.cargo.domain.Cargo;
import hometask23.cargo.repo.CargoRepo;
import hometask23.transportation.domain.Transportation;
import hometask23.transportation.service.TransportationService;

import java.util.*;

public class CargoServiceImpl implements CargoService {
    private CargoRepo repo;

    public void setTransportationService(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    private TransportationService transportationService;

    public CargoServiceImpl(CargoRepo repo) {
        this.repo = repo;
    }


    @Override
    public void add(Cargo cargo) {
        boolean cargoWasntAddedBefore = (cargo != null && !this.getById(cargo.getId()).isPresent());
        if (cargoWasntAddedBefore) {
            repo.add(cargo);
            System.out.println("Cargo has been added successfully");
        } else if (cargo == null) {
            System.out.println("Cargo hasn't been added: nothing to add");
        } else {
            repo.update(cargo);
            System.out.println("Cargo has been rewritten");
        }
    }

    @Override
    public Optional<Cargo> getById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return repo.getById(id);
    }

    @Override
    public void deleteById(Long id) throws CargoInUseDeleteException {
        if (id != null) {
            if (transportationService != null) {
                List<Transportation> transportations = transportationService.getAll();
                Cargo cargo = this.getById(id).get();
                transportations.forEach((transportation) -> {
                    if (transportation.getCargo().equals(cargo)) {
                        throw new CargoInUseDeleteException("Cargo " + cargo.getName() + ", id " + id +
                                " cannot be deleted due to it is in use in transportation id " + transportation.getId());
                    }
                });
                if (repo.deleteById(id)) {
                    System.out.println("Cargo deleted successfully");
                } else {
                    System.out.println("There is no cargo with id: " + id + " nothing to delete");
                }
            }
        }
    }

    @Override
    public List<Cargo> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Cargo> getByName(String name) {
        List<Cargo> cargoWithReceivedName = repo.getByName(name);
        if (cargoWithReceivedName.isEmpty()) {
            System.out.println("There are not cargos with such a name : " + name);
        }
        return cargoWithReceivedName;
    }

    @Override
    public List<Cargo> getSorted(CargoSortCondition cargoSortsCondition) {
        List<Cargo> cargos = repo.getAll();
        if (cargoSortsCondition.needSorting()) {
            ArrayList<CargoSortFields> cargoSortFields = new ArrayList(cargoSortsCondition.getSortFields());
            boolean needReverse = cargoSortsCondition.isOrderReversed();
            if (cargoSortFields.size() == 1) {
                Collections.sort(cargos, getComparatorWithRightOrder(cargoSortFields.get(0), needReverse));
            } else if (cargoSortFields.size() == 2) {
                Collections.sort(cargos,
                        getComparatorWithRightOrder(cargoSortFields.get(0), needReverse).thenComparing
                                (getComparatorWithRightOrder(cargoSortFields.get(1), needReverse)));
            }
        }
        return cargos;
    }

    @Override
    public void printAll() {
        for (Cargo cargo : repo.getAll()) {
            System.out.println(cargo);
        }
    }

    private Comparator<Cargo> getComparatorWithRightOrder(CargoSortFields cargoSortFields, boolean isReserved) {
        if (cargoSortFields != null && isReserved) {
            return getComparatorForCargoFields(cargoSortFields).reversed();
        } else {
            return getComparatorForCargoFields(cargoSortFields);
        }
    }

    private Comparator<Cargo> getComparatorForCargoFields(CargoSortFields cargoSortFields) {
        switch (cargoSortFields) {
            case SORT_BY_NAME: {
                return CARGO_NAME_COMPARATOR;
            }
            case SORT_BY_WEIGHT: {
                return CARGO_WEIGHT_COMPARATOR;
            }
        }
        return null;
    }

    private static final Comparator<Cargo> CARGO_NAME_COMPARATOR = Comparator.comparing(Cargo::getName);

    private static final Comparator<Cargo> CARGO_WEIGHT_COMPARATOR = Comparator.comparingDouble(Cargo::getWeight);
}
