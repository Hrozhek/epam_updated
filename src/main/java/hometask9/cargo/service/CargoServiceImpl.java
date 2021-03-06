package hometask9.cargo.service;

import hometask9.carrier.domain.Carrier;
import hometask9.transportation.domain.Transportation;
import hometask9.transportation.service.TransportationServiceImpl;
import hometask9.application.serviceholder.ServiceHolder;
import hometask9.cargo.domain.Cargo;
import hometask9.cargo.repo.CargoRepo;
import hometask9.transportation.service.TransportationService;

import java.util.*;

public class CargoServiceImpl implements CargoService {
    private CargoRepo repo;

    public CargoServiceImpl(CargoRepo repo) {
        this.repo = repo;
    }

    @Override
    public void add(Cargo cargo) {
        boolean cargoWasntAddedBefore = (cargo != null && cargo.getId() == null);
        if (cargoWasntAddedBefore) {
            repo.add(cargo);
            System.out.println("Cargo has been added successfully");
        } else if (cargo == null) {
            System.out.println("Cargo hasn't been added: nothing to add");
        } else {
            repo.deleteById(cargo.getId());
            repo.add(cargo);
            System.out.println("Cargo has been rewritten");
        }
    }

    @Override
    public Cargo getById(Long id) {
        Cargo foundRepo = repo.getById(id);
        if (foundRepo == null) {
            System.out.println("Cargo with id: " + id + " hasn't been found");
        }
        return foundRepo;
    }

    @Override
    public void deleteById(Long id) throws CargoInUseDeleteException {
        try {
            if (ServiceHolder.getInstance() != null) {
                TransportationService transportationService = ServiceHolder.getInstance().getTransportationService();
                List<Transportation> transportations = transportationService.getAll();
                for (Transportation transportation : transportations) {
                    Cargo cargo = this.getById(id);
                    if (transportation.getCargo().equals(cargo)) {
                        throw new CargoInUseDeleteException("Cargo " + cargo.getName() + ", id " + id +
                                " cannot be deleted due to it is in use in transportation id " + transportation.getId());
                    }

                }
                if (repo.deleteById(id)) {
                    System.out.println("Cargo deleted successfully");
                } else {
                    throw new IllegalArgumentException("There is no cargo with id: " + id + " nothing to delete");
                }
            }
        } catch (NullPointerException npe) {
            System.out.println("Cargo cannot be deleted by id, because its id is null");
        }
    }

    @Override
    public List<Cargo> getByName(String name) {
        Cargo[] cargoWithReceivedName = repo.getByName(name);
        if (cargoWithReceivedName.length == 0) {
            System.out.println("There are not cargos with such a name : " + name);
        }
        return Arrays.asList(cargoWithReceivedName);
    }

    @Override
    public List<Cargo> getSortedCargos(CargoSortCondition cargoSortsCondition) {
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
        if (isReserved) {
            return getComparatorForCargoFields(cargoSortFields).reversed();
        }
        else {
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

    private static final Comparator<Cargo> CARGO_NAME_COMPARATOR = new Comparator<>() {
        @Override
        public int compare(Cargo o1, Cargo o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    private static final Comparator<Cargo> CARGO_WEIGHT_COMPARATOR = new Comparator<>() {
        @Override
        public int compare(Cargo o1, Cargo o2) {
            return Double.compare(o1.getWeight(), o2.getWeight());
        }
    };
}
