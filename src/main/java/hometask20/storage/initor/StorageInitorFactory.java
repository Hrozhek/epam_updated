package hometask20.storage.initor;

import hometask20.storage.initor.fileinitor.TextFileDataInitor;
import hometask20.storage.initor.fileinitor.XmlDomFileDataInitor;
import hometask20.storage.initor.fileinitor.multithread.MultithreadFileInitor;
import hometask20.storage.initor.fileinitor.sax.XmlSaxFileDataInitor;

public final class StorageInitorFactory {

    private StorageInitorFactory() {

    }

    public static StorageInitor getStorageInitor(InitStorageType initStorageType) {
        switch (initStorageType) {

            case MEMORY: {
                return new InMemoryStorageInitor();
            }
            case TEXT_FILE: {
                return new TextFileDataInitor();
            }
            case XML_DOM_FILE:{
                return new XmlDomFileDataInitor();
            }
            case XML_SAX_FILE:{
                return new XmlSaxFileDataInitor();
            }
            case MULTI_THREAD: {
                return new MultithreadFileInitor();
            }
            default: {
                throw new RuntimeException("Unknown storage init type " + initStorageType);
            }
        }
    }

}
