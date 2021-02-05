package nl.tsakiris.classifier.condition.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class ParenthesesEnricher implements TokenizerPostProcessor {

  @Override
  public List<Token> process(List<Token> input) throws TokenizerException {
    List<Token> tokens = new ArrayList<>(input);
    int i = 0;
    while (i < tokens.size()) {
      if (tokens.get(i) instanceof BooleanBinaryOperatorToken) {
        if (i < 1) {
          throw new TokenizerException();
        }
        tokens.add(i, new CloseParenthesisToken());
        int parity = 0;
        int j = i - 1;
        while (j >= 0) {
          Token token = tokens.get(j);
          if (token instanceof OpenParenthesisToken && parity == 0) {
            break;
          } else if (token instanceof CloseParenthesisToken) {
            parity++;
          } else if (token instanceof OpenParenthesisToken) {
            if (--parity < 0) {
              throw new TokenizerException();
            }
          }
          j--;
        }
        tokens.add(j + 1, new OpenParenthesisToken());
        i += 2;
      }
      i++;
    }
    return tokens;
  }

}
