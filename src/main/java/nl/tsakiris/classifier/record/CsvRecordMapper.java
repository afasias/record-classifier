package nl.tsakiris.classifier.record;

import java.util.HashMap;
import java.util.Map;

public class CsvRecordMapper {

  private final Map<String, Integer> headerPositions = new HashMap<>();

  private final Map<String, String> columns;

  public CsvRecordMapper(String[] headers, Map<String, String> mappings) {
    for (int i = 0; i < headers.length; i++) {
      headerPositions.put(headers[i], i);
    }
    this.columns = mappings;
  }

  public Record map(String[] row) {
    Record.Builder recordBuilder = Record.builder();
    for (Map.Entry<String, String> entry : columns.entrySet()) {
      String column = entry.getValue();
      int position;
      try {
        position = Integer.parseInt(column);
      } catch (NumberFormatException e) {
        position = headerPositions.get(column);
      }
      recordBuilder.with(entry.getKey(), row[position]);
    }
    return recordBuilder.build();
  }

}
