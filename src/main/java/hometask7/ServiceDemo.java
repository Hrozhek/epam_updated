package hometask7;


import hometask7.cargo.repo.CargoCollectionRepoImpl;
import hometask7.cargo.repo.CargoRepo;
import hometask7.cargo.service.CargoService;
import hometask7.cargo.service.CargoServiceImpl;
import hometask7.carrier.repo.CarrierCollectionRepoImpl;
import hometask7.carrier.repo.CarrierRepo;
import hometask7.carrier.service.CarrierService;
import hometask7.carrier.service.CarrierServiceImpl;
import hometask7.transportation.repo.TransportationCollectionRepoImpl;
import hometask7.transportation.repo.TransportationRepo;
import hometask7.transportation.service.TransportationService;
import hometask7.cargo.domain.Cargo;
import hometask7.cargo.domain.ClothesCargo;
import hometask7.cargo.domain.FoodCargo;
import hometask7.carrier.domain.Carrier;
import hometask7.carrier.domain.CarrierType;
import hometask7.transportation.domain.Transportation;
import hometask7.transportation.service.TransportationServiceImpl;

import java.util.Date;

public class ServiceDemo {
    public static void main(String[] args) {
        CargoRepo cargoRepo = new CargoCollectionRepoImpl();
        CargoService cargoServ = new CargoServiceImpl(cargoRepo);
        CarrierRepo carrierRepo = new CarrierCollectionRepoImpl();
        CarrierService carrierServ = new CarrierServiceImpl(carrierRepo);
        TransportationRepo transportationRepo = new TransportationCollectionRepoImpl();
        TransportationService transportationServ = new TransportationServiceImpl(transportationRepo);

        Cargo apple = new FoodCargo();
        apple.setName("Apple");
        System.out.println("test null delete");
        cargoServ.deleteById(apple.getId());
        System.out.println();
        System.out.println("test adding and rewriting");
        cargoServ.add(apple);
        cargoServ.add(apple);
        System.out.println();
        cargoServ.deleteById(apple.getId());

        Cargo orange = new FoodCargo();
        orange.setName("Orange");
        cargoServ.add(orange);

        Cargo levis501 = new ClothesCargo();
        levis501.setName("Levi's 501");

        Carrier pdp = new Carrier();
        pdp.setName("PDP");
        pdp.setCarrierType(CarrierType.PLANE);
        carrierServ.add(pdp);

        Carrier defex = new Carrier();
        defex.setName("DefEx");
        defex.setCarrierType(CarrierType.CAR);
        carrierServ.add(defex);

        Carrier minipigExpress = new Carrier();
        minipigExpress.setName("MinipigExpress");
        minipigExpress.setCarrierType(CarrierType.SHIP);
        carrierServ.add(minipigExpress);

        Transportation transportation1 = new Transportation();
        transportation1.setBillTo("Ivan ivanich");
        transportation1.setCargo(orange);
        transportation1.setCarrier(pdp);
        transportationServ.add(transportation1);

        Transportation transportation2 = new Transportation();
        transportation2.setBillTo("Tax payers");
        transportation2.setCargo(levis501);
        transportation2.setCarrier(minipigExpress);
        transportation2.setDescription("Little gift for new president");
        transportation2.setTransportationBeginDate(new Date(108, 4, 7));
        transportationServ.add(transportation2);

        Transportation transportation3 = new Transportation();
        transportation3.setBillTo("Ivan ivanich");
        transportation3.setCargo(apple);
        transportation3.setCarrier(defex);
        transportationServ.add(transportation3);
    }

}
