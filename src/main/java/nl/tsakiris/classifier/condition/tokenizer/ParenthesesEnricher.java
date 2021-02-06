package nl.tsakiris.classifier.condition.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class ParenthesesEnricher implements TokenizerPostProcessor {

  @Override
  public List<Token> process(List<Token> input) throws TokenizerException {
    if (input.isEmpty()) {
      return input;
    }
    if (input.get(0) instanceof BooleanBinaryOperatorToken) {
      throw new TokenizerException("Unexpected token: " + input.get(0) + " at beginning of input");
    }
    List<Token> tokens = new ArrayList<>(input);
    for (int i = 1; i < tokens.size(); i++) {
      if (!(tokens.get(i) instanceof BooleanBinaryOperatorToken)) {
        continue;
      }
      tokens.add(i, new CloseParenthesisToken());
      int parity = 0;
      int j = i - 1;
      while (j >= 0) {
        Token token = tokens.get(j);
        if (token instanceof OpenParenthesisToken) {
          if (--parity == 0) {
            break;
          }
        } else if (token instanceof CloseParenthesisToken) {
          parity++;
        }
        j--;
      }
      tokens.add(j + 1, new OpenParenthesisToken());
      i += 2;
    }
    return tokens;
  }

}
