package nl.tsakiris.classifier.condition.tokenizer;

import static nl.tsakiris.classifier.condition.tokenizer.BooleanBinaryOperator.AND;
import static nl.tsakiris.classifier.condition.tokenizer.BooleanBinaryOperator.OR;

public class BooleanBinaryOperatorTokenizer implements Tokenizer {

  @Override
  public TokenizeResult tokenize(String input) {
    if (input.startsWith("&&")) {
      return TokenizeResult.builder()
          .token(new BooleanBinaryOperatorToken(AND))
          .remainingInput(input.substring(2))
          .build();
    }
    if (input.startsWith("||")) {
      return TokenizeResult.builder()
          .token(new BooleanBinaryOperatorToken(OR))
          .remainingInput(input.substring(2))
          .build();
    }
    return null;
  }

}
