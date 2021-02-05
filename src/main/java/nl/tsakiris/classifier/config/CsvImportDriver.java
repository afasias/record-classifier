package nl.tsakiris.classifier.config;

import java.util.Map;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize(builder = CsvImportDriver.Builder.class)
@Builder(builderClassName = "Builder")
public class CsvImportDriver implements ImportDriver {

  private final Medium source;

  private final boolean hasHeader;

  private final char quoteChar;

  private final char separator;

  private final Map<String, String> dataMapping;


  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
