package nl.tsakiris.classifier.condition.tokenizer;

import java.util.List;

public interface FullTokenizer {

  List<Token> tokenizeAll(String input) throws TokenizerException;

}
