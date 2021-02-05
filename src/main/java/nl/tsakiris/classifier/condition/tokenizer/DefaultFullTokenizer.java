package nl.tsakiris.classifier.condition.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class DefaultFullTokenizer implements FullTokenizer {

  private final InputConsumer whitespaceInputConsumer = new WhitespaceInputConsumer();

  private final Tokenizer[] tokenizers = {
      new BooleanBinaryOperatorTokenizer(),
      new BooleanNotOperatorTokenizer(),
      new ParenthesisTokenizer(),
      new EqualityOperatorTokenizer(),
      new BooleanConstantTokenizer(),
      new TermOperatorTokenizer(),
      new TermFieldTokenizer(),
      new StringLiteralTokenizer(),
  };

  private final TokenizerPostProcessor[] postProcessors = {
      new ParenthesesEnricher(),
  };

  @Override
  public List<Token> tokenizeAll(String input) throws TokenizerException {
    List<Token> tokens = new ArrayList<>();
    while_loop:
    while (true) {
      input = whitespaceInputConsumer.consume(input);
      if (input.isEmpty()) {
        break;
      }
      for (Tokenizer tokenizer : tokenizers) {
        TokenizeResult result = tokenizer.tokenize(input);
        if (result != null) {
          tokens.add(result.getToken());
          input = result.getRemainingInput();
          continue while_loop;
        }
      }
      throw new TokenizerException();
    }
    for (TokenizerPostProcessor postProcessor : postProcessors) {
      tokens = postProcessor.process(tokens);
    }
    return tokens;
  }

}
