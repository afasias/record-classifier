package nl.tsakiris.classifier.condition.tokenizer;

public class ParenthesisTokenizer implements Tokenizer {

  @Override
  public TokenizeResult tokenize(String input) {
    if (input.startsWith("(")) {
      return TokenizeResult.builder()
          .token(new OpenParenthesisToken())
          .remainingInput(input.substring(1))
          .build();
    }
    if (input.startsWith(")")) {
      return TokenizeResult.builder()
          .token(new CloseParenthesisToken())
          .remainingInput(input.substring(1))
          .build();
    }
    return null;
  }

}
