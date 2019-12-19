package hometask8.cargo.service;

import hometask8.cargo.domain.Cargo;
import hometask8.cargo.repo.CargoRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
    public void deleteById(Long id) {
        if (repo.deleteById(id)) {
            System.out.println("Cargo deleted successfully");
        } else {
            System.out.println("There is no cargo with id: " + id + " nothing to delete");
        }
    }

    @Override
    public Cargo[] getByName(String name) {
        Cargo[] cargoWithReceivedName = repo.getByName(name);
        if (cargoWithReceivedName.length == 0) {
            System.out.println("There are not cargos with such a name : " + name);
        }
        return cargoWithReceivedName;
    }

    public void sortByName() {
        Collections.sort(repo.getAll(), new CargoNameComparator());
    }

    public void sortByWeight() {
        Collections.sort(repo.getAll(), new CargoWeightComparator());
    }

    public void sortByNameAndWeight() {
        Collections.sort(repo.getAll(), new CargoNameComparator().thenComparing(new CargoWeightComparator()));
    }

    private class CargoNameComparator implements Comparator<Cargo> {
        public int compare(Cargo o1, Cargo o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private class CargoWeightComparator implements Comparator<Cargo> {
        public int compare(Cargo o1, Cargo o2) {
            return Double.compare(o1.getWeight(), o2.getWeight());
        }
    }
    
}