package nl.tsakiris.classifier.condition.tokenizer;

import lombok.Data;
import nl.tsakiris.classifier.condition.TermOperator;

@Data
public class TermOperatorToken implements Token {

  private final TermOperator value;

  @Override
  public String toString() {
    return value.name();
  }

}
