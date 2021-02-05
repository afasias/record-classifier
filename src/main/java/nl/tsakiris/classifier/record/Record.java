package nl.tsakiris.classifier.record;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize(using = RecordSerializer.class)
public class Record {

  private final Map<String, String> map;

  public String get(String field) {
    return map.get(field);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private final Map<String, String> map = new HashMap<>();

    private Builder() {}

    public Record.Builder with(String field, String value) {
      map.put(field, value);
      return this;
    }

    public Record build() {
      return new Record(map);
    }

  }

}
