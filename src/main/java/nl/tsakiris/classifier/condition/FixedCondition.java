package nl.tsakiris.classifier.condition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import nl.tsakiris.classifier.record.Record;

@AllArgsConstructor
@JsonDeserialize(builder = FixedCondition.Builder.class)
@Builder(builderClassName = "Builder")
public class FixedCondition implements Condition {

  private final boolean value;

  @Override
  public boolean test(Record record) {
    return value;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
