package nl.tsakiris.classifier.condition.tokenizer;

public class BooleanNotOperatorTokenizer implements Tokenizer {

  @Override
  public TokenizeResult tokenize(String input) {
    if (input.startsWith("!")) {
      return TokenizeResult.builder()
          .token(new BooleanNotOperatorToken())
          .remainingInput(input.substring(1))
          .build();
    }
    return null;
  }

}
