package hometask14.cargo.service;

import hometask13.cargo.service.CargoSortFields;

import java.util.Set;

public class CargoSortCondition {
    Set<hometask13.cargo.service.CargoSortFields> sortFields;

    private boolean isReversed = false;

    public CargoSortCondition(Set<hometask13.cargo.service.CargoSortFields> sortFields) {
        this.sortFields = sortFields;
    }

    public boolean isOrderReversed() {
        return isReversed;
    }

    public void setOrderReversed() {
        isReversed = true;
    }

    public void setOrderReversed(boolean isReversed) {
        this.isReversed = isReversed;
    }

    public boolean needSorting() {
        return sortFields != null && sortFields.size() > 0;
    }

    public Set<CargoSortFields> getSortFields() {
        return sortFields;
    }
}
