package com.software.documentmanager;

import java.io.File;
import java.io.IOException;

public class InvoiceImporter implements Importer {
    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);
    }
    
    
}
