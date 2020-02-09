package hometask23.cargo.service;

import java.util.Set;

public class CargoSortCondition {
    Set<CargoSortFields> sortFields;

    private boolean isReversed = false;

    public CargoSortCondition(Set<CargoSortFields> sortFields) {
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
