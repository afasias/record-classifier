package nl.tsakiris.classifier.config;

import java.util.Map;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nl.tsakiris.classifier.category.Category;

@Data
@AllArgsConstructor
@JsonDeserialize(builder = Configuration.Builder.class)
@Builder(builderClassName = "Builder")
public class Configuration {

  private final Map<String, FieldType> fields;

  private final Map<String, Aggregation<?>> aggregations;

  private final ImportDriver importDriver;

  private final ExportDriver exportDriver;

  private final Category rootCategory;


  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
