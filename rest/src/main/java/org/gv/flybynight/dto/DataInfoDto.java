package org.gv.flybynight.dto;

import org.gv.flybynight.suncertify.db.DataInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gasper on 11/8/16.
 */
public class DataInfoDto implements Serializable {

    private List<DataInfo> values;

    private DataInfoDto() {

    }

    public DataInfoDto(List<DataInfo> values) {
        this.values = values;
    }

    public List<DataInfo> getValues() {
        return values;
    }

    public void setValues(List<DataInfo> values) {
        this.values = values;
    }
}
