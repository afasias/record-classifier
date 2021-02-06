package nl.tsakiris.classifier.condition.tokenizer;

import lombok.Data;

@Data
public class BooleanConstantToken implements Token {

  private final boolean value;

  @Override
  public String toString() {
    return Boolean.toString(value);
  }

}
