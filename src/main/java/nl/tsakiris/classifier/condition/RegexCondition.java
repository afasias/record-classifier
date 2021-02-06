package nl.tsakiris.classifier.condition;

import java.util.Optional;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import nl.tsakiris.classifier.record.Record;

@AllArgsConstructor
@JsonDeserialize(builder = RegexCondition.Builder.class)
@Builder(builderClassName = "Builder")
public class RegexCondition implements Condition {

  private final String field;

  private final Pattern pattern;

  @Override
  public boolean test(Record record) {
    String termValue = Optional.ofNullable(record.get(field)).orElse("");
    return pattern.matcher(termValue).matches();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
