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
  public Condition parse(String input) throws ParserException, TokenizerException {
    List<Token> tokens = fullTokenizer.tokenizeAll(input);
    if (tokens.isEmpty()) {
      throw new ParserException("Empty input");
    }
    ParseResult parseResult = booleanParser.parse(tokens);
    if (parseResult == null) {
      throw new ParserException("Unrecognized input: " + tokens);
    }
    if (!parseResult.getRemainingTokens().isEmpty()) {
      throw new ParserException("Unrecognized input: " + parseResult.getRemainingTokens());
    }
    return parseResult.getCondition();
  }

}
