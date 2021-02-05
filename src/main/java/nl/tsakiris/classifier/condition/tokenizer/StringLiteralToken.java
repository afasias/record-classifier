package nl.tsakiris.classifier.condition.tokenizer;

import lombok.Data;

@Data
public class StringLiteralToken implements Token {

  private final String value;

}
