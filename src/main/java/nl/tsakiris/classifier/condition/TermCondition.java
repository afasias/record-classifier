package nl.tsakiris.classifier.condition;

import java.util.Optional;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import nl.tsakiris.classifier.record.Record;

@AllArgsConstructor
@JsonDeserialize(builder = TermCondition.Builder.class)
@Builder(builderClassName = "Builder")
public class TermCondition implements Condition {

  private final String field;

  private final TermOperator operator;

  private final String value;

  @Override
  public boolean test(Record record) {
    String termValue = Optional.ofNullable(record.get(field)).orElse("");
    switch (operator) {
      case equals:
        return termValue.equals(value);
      case equalsIgnoreCase:
        return termValue.equalsIgnoreCase(value);
      case startsWith:
        return termValue.startsWith(value);
      case contains:
        return termValue.contains(value);
      case endsWith:
        return termValue.endsWith(value);
      default:
        throw new IllegalArgumentException(operator.name());
    }
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
