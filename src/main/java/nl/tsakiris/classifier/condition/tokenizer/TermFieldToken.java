package nl.tsakiris.classifier.condition.tokenizer;

import lombok.Data;

@Data
public class TermFieldToken implements Token {

  private final String field;

  @Override
  public String toString() {
    return field;
  }

}
