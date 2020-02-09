package hometask23.storage.initor;

import hometask23.common.business.exception.checked.InitStorageException;

@FunctionalInterface
public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
