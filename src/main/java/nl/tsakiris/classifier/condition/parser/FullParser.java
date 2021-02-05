package nl.tsakiris.classifier.condition.parser;

import nl.tsakiris.classifier.condition.Condition;

public interface FullParser {

  Condition parse(String input) throws ParserException;

}
