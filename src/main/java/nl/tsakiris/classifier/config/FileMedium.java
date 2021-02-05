package nl.tsakiris.classifier.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize(builder = FileMedium.Builder.class)
@Builder(builderClassName = "Builder")
public class FileMedium implements Medium {

  private final String path;


  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
