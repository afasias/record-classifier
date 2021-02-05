package nl.tsakiris.classifier.condition.tokenizer;

public class EqualityOperatorTokenizer implements Tokenizer {

  @Override
  public TokenizeResult tokenize(String input) {
    if (input.startsWith("==")) {
      return TokenizeResult.builder()
          .token(new EqualityOperatorToken())
          .remainingInput(input.substring(2))
          .build();
    }
    return null;
  }

}
