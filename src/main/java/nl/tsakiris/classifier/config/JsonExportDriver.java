package nl.tsakiris.classifier.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize(builder = JsonExportDriver.Builder.class)
@Builder(builderClassName = "Builder")
public class JsonExportDriver implements ExportDriver {

  private final Medium target;


  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
