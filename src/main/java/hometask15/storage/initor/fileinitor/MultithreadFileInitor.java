package hometask15.storage.initor.fileinitor;

import hometask15.application.serviceholder.StorageType;
import hometask15.common.business.exception.checked.InitStorageException;
import hometask15.storage.initor.StorageInitor;

import java.io.File;

public class MultithreadFileInitor implements Runnable {
    private StorageInitor initor;
    private Thread threadRunner;
    public MultithreadFileInitor(StorageInitor initor) {
        this.initor = initor;
        threadRunner = new Thread(this);
    }

    @Override
    public void run() {
        try {
            initor.initStorage();
        }
        catch (InitStorageException e) {
            e.printStackTrace();
        }
    }

    public void parseMultiThread(){
        threadRunner.start();
    }

    public void waitUntilDOne() throws InterruptedException {
        threadRunner.join();;
    }
}
