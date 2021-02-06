package nl.tsakiris.classifier.condition.tokenizer;

import lombok.Data;

@Data
public class BooleanBinaryOperatorToken implements Token {

  private final BooleanBinaryOperator operator;

  @Override
  public String toString() {
    switch (operator) {
      case AND:
        return "&&";
      case OR:
        return "||";
      default:
        throw new IllegalArgumentException();
    }
  }

}
