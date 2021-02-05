package nl.tsakiris.classifier.condition.tokenizer;

import lombok.Data;

@Data
public class BooleanBinaryOperatorToken implements Token {

  private final BooleanBinaryOperator operator;

}
