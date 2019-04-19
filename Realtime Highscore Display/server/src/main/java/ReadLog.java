import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.pusher.rest.Pusher;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReadLog implements PusherAuth {

    private static String TABLE_NAME = "highscores";

    public static void main(String[] args) throws IOException {
        final Map<String, Long> tableMap = new HashMap<String, Long>();

        Pusher pusher = new Pusher(id, key, secret);
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);

        BinaryLogClient client = new BinaryLogClient("localhost", 3306, USER, PASSWORD);

        client.registerEventListener(event -> {
            EventData data = event.getData();

            if(data instanceof TableMapEventData) {
                TableMapEventData tableData = (TableMapEventData)data;
                tableMap.put(tableData.getTable(), tableData.getTableId());
            } else if(data instanceof WriteRowsEventData) {
                WriteRowsEventData eventData = (WriteRowsEventData)data;
                if(eventData.getTableId() == tableMap.get(TABLE_NAME)) {
                    for(Object[] product: eventData.getRows()) {
                        pusher.trigger(TABLE_NAME, "insert", getProductMap(product));
                    }
                }
            } else if(data instanceof UpdateRowsEventData) {
                UpdateRowsEventData eventData = (UpdateRowsEventData)data;
                if(eventData.getTableId() == tableMap.get(TABLE_NAME)) {
                    for(Map.Entry<Serializable[], Serializable[]> row : eventData.getRows()) {
                        pusher.trigger(TABLE_NAME, "update", getProductMap(row.getValue()));
                    }
                }
            } else if(data instanceof DeleteRowsEventData) {
                DeleteRowsEventData eventData = (DeleteRowsEventData)data;
                if(eventData.getTableId() == tableMap.get(TABLE_NAME)) {
                    for(Object[] product: eventData.getRows()) {
                        pusher.trigger(TABLE_NAME, "delete", product[0]);
                    }
                }
            }
        });
        client.connect();
    }

    static Map<String, String> getProductMap(Object[] product) {
        Map<String, String> map = new HashMap<>();
        map.put("ID", java.lang.String.valueOf(product[0]));
        map.put("Name", java.lang.String.valueOf(product[1]));
        map.put("TimeTaken", java.lang.String.valueOf(product[2]));
        map.put("Type", java.lang.String.valueOf(product[3]));

        return map;
    }
}
