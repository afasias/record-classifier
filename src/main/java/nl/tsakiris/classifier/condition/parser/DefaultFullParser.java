package nl.tsakiris.classifier.condition.parser;

import java.util.List;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.tokenizer.DefaultFullTokenizer;
import nl.tsakiris.classifier.condition.tokenizer.FullTokenizer;
import nl.tsakiris.classifier.condition.tokenizer.Token;
import nl.tsakiris.classifier.condition.tokenizer.TokenizerException;

public class DefaultFullParser implements FullParser {

  private final FullTokenizer fullTokenizer = new DefaultFullTokenizer();

  private final Parser booleanParser = new BooleanParser();

  @Override
  public Condition parse(String input) throws ParserException {
    List<Token> tokens;
    try {
      tokens = fullTokenizer.tokenizeAll(input);
    } catch (TokenizerException e) {
      throw new ParserException();
    }
    ParseResult parseResult = booleanParser.parse(tokens);
    if (parseResult == null) {
      throw new ParserException();
    }
    if (!parseResult.getRemainingTokens().isEmpty()) {
      throw new ParserException();
    }
    return parseResult.getCondition();
  }

}
