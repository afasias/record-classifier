package nl.tsakiris.classifier.condition.parser;

import java.util.List;
import nl.tsakiris.classifier.condition.tokenizer.Token;

public class ExpressionParser implements Parser {

  private final Parser[] parsers = {
      new BooleanConstantParser(),
      new StringEqualityParser(),
      new StringFunctionParser(),
      new RegexMatchParser(),
  };

  @Override
  public ParseResult parse(List<Token> tokens) throws ParserException {
    if (tokens.isEmpty()) {
      return null;
    }
    for (Parser parser : parsers) {
      ParseResult parseResult = parser.parse(tokens);
      if (parseResult != null) {
        return parseResult;
      }
    }
    return null;
  }

}
