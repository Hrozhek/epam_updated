package hometask18.storage.initor;

import hometask18.common.business.exception.checked.InitStorageException;

@FunctionalInterface
public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
