package nl.tsakiris.classifier.condition.parser;

import java.util.List;
import nl.tsakiris.classifier.condition.tokenizer.Token;

public interface Parser {

  ParseResult parse(List<Token> tokens) throws ParserException;

}
