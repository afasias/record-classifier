package nl.tsakiris.classifier.condition.tokenizer;

public class RegexOperatorTokenizer implements Tokenizer {

  @Override
  public TokenizeResult tokenize(String input) {
    if (input.startsWith("~")) {
      return TokenizeResult.builder()
          .token(new RegexOperatorToken())
          .remainingInput(input.substring(1))
          .build();
    }
    return null;
  }

}
