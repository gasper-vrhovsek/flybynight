package org.gv.flybynight.bean;

import org.gv.flybynight.FlyByNight;
import org.gv.flybynight.helpers.RecordLineParser;
import org.gv.flybynight.suncertify.db.Data;
import org.gv.flybynight.suncertify.db.DataInfo;
import org.gv.flybynight.suncertify.db.DatabaseException;
import org.gv.flybynight.suncertify.db.FieldInfo;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gasper on 10/27/16.
 */
public class DatabaseBean implements SmartLifecycle {
    private static Logger log = Logger.getLogger(FlyByNight.class.getName());

    private Data db;

    private String storeFile;
    private String dataFile;

    private static final FieldInfo[] FIELDS = new FieldInfo[]{
            new FieldInfo("key", 256),
            new FieldInfo("flightNumber", 5),
            new FieldInfo("originAirport", 3),
            new FieldInfo("destinationAirport", 3),
            new FieldInfo("carrier", 128),
            new FieldInfo("price", 12),
            new FieldInfo("day", 3),
            new FieldInfo("time", 5),
            new FieldInfo("duration", 8),
            new FieldInfo("availableSeats", 8)};

    public DatabaseBean() {
        log.log(Level.INFO, "database bean constructor asdf");


    }

    @Override
    public void start() {
        log.log(Level.ALL, "DatabaseBean start()");

                try {
            File file = new File(storeFile);

            if (file.exists()) {
                db = new Data(storeFile);
            } else {
                db = new Data(storeFile, FIELDS);
            }

            Resource dataFileResource = new ClassPathResource(dataFile);
            if (dataFileResource.exists()) {
                insertInitialData(db, dataFileResource);
            } else {
                log.log(Level.WARNING, "Initial data file does not exist! asdf");
            }
        } catch (IOException | DatabaseException e) {
            log.log(Level.SEVERE, "Exception starting DatabaseBean asdf", e);
        }

    }

    @Override
    public void stop() {

    }

    public List<DataInfo> getAllRecords() throws DatabaseException {
        int recordCount = db.getRecordCount();
        List<DataInfo> records = new ArrayList<>();

        for (int i = 1; i <= recordCount; i++) {
            records.add(db.getRecord(1));
        }
        return records;
    }

    public boolean isRunning() {
        return false;
    }

    public String getStoreFile() {
        return storeFile;
    }

    public void setStoreFile(String storeFile) {
        this.storeFile = storeFile;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    private void insertInitialData(Data db, Resource resource) throws IOException, DatabaseException {
        InputStream resourceInputStream = resource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceInputStream));

        RecordLineParser recordLineParser = new RecordLineParser();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            db.add(recordLineParser.getData(line, db.getRecordCount()));
        }
    }

    public String testInitDb() throws IOException {
        Resource dataFileResource = new ClassPathResource(dataFile);
        InputStream resourceInputStream = dataFileResource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceInputStream));

        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {

            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    @Override
    public int getPhase() {
        return 1;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {

    }
}
