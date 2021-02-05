package nl.tsakiris.classifier.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize(builder = HtmlExportDriver.Builder.class)
@Builder(builderClassName = "Builder")
public class HtmlExportDriver implements ExportDriver {

  private final Medium target;

  private final String defaultAggregation;


  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
