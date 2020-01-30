package hometask20.storage.initor;

import hometask20.common.business.exception.checked.InitStorageException;

@FunctionalInterface
public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
