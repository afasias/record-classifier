package nl.tsakiris.classifier.condition.tokenizer;

import java.util.List;

public class ParenthesesParityChecker implements TokenizerPostProcessor {

  @Override
  public List<Token> process(List<Token> input) throws TokenizerException {
    int parity = 0;
    for (Token token : input) {
      if (token instanceof CloseParenthesisToken) {
        if (--parity < 0) {
          throw new TokenizerException("Unbalanced close parenthesis");
        }
      } else if (token instanceof OpenParenthesisToken) {
        parity++;
      }
    }
    if (parity > 0) {
      throw new TokenizerException("Unbalanced open parenthesis");
    }
    return input;
  }

}
