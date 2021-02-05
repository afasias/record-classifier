package nl.tsakiris.classifier.condition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import nl.tsakiris.classifier.record.Record;

@AllArgsConstructor
@JsonDeserialize(builder = NotCondition.Builder.class)
@Builder(builderClassName = "Builder")
public class NotCondition implements Condition {

  private final Condition condition;

  @Override
  public boolean test(Record record) {
    return !condition.test(record);
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
