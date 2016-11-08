package org.gv.flybynight;

import org.gv.flybynight.bean.DatabaseBean;
import org.gv.flybynight.dto.TestDto;
import org.gv.flybynight.suncertify.db.DataInfo;
import org.gv.flybynight.suncertify.db.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gasper on 10/26/16.
 */
@Controller
@EnableWebMvc
@RequestMapping(value = "/flybynight")
public class FlyByNight {
    private static Logger log = Logger.getLogger(FlyByNight.class.getName());

    @Autowired
    private DatabaseBean db;

    // initialize DB
    @RequestMapping(value = "init", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String initDb() {
        log.log(Level.ALL, "initDb");

        try {
            return db.testInitDb();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{ result : \'failed\'}";
    }

    // get all ?
    @RequestMapping(value = "getAll", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
    public
    @ResponseBody
    List getAllRecords() throws DatabaseException {


        List<DataInfo> allRecords = db.getAllRecords();
        return allRecords;

//        return new DataInfoDto(allRecords);
    }


    // get all ?
    @RequestMapping(value = "getTest", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
    public
    @ResponseBody
    Object getTest() throws DatabaseException {
        TestDto test = new TestDto(1, "asdfg");
        return test;
    }
}
