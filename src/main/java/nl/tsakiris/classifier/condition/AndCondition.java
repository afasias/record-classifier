package nl.tsakiris.classifier.condition;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import nl.tsakiris.classifier.record.Record;

@AllArgsConstructor
@JsonDeserialize(builder = AndCondition.Builder.class)
@Builder(builderClassName = "Builder")
public class AndCondition implements Condition {

  private final List<Condition> conditions;

  @Override
  public boolean test(Record record) {
    for (Condition condition : conditions) {
      if (!condition.test(record)) {
        return false;
      }
    }
    return true;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
