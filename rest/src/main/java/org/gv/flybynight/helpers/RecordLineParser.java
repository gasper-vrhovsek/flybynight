package org.gv.flybynight.helpers;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by gasper on 11/7/16.
 */
public class RecordLineParser {
    private static String DELIMITER = "\\^";

    public String[] getData(String line, int recordCount) {
        String[] split = line.split(DELIMITER);
        String[] data = {String.valueOf(recordCount)};
        return ArrayUtils.addAll(data, split);
    }
}
