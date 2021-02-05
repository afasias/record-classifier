package nl.tsakiris.classifier.condition.tokenizer;

public class WhitespaceInputConsumer implements InputConsumer {

  @Override
  public String consume(String input) {
    int pos = 0;
    while (pos < input.length()) {
      char ch = input.charAt(pos);
      if (ch != ' ' && ch != '\t' && ch != '\n' && ch != '\r') {
        break;
      }
      pos++;
    }
    return input.substring(pos);
  }

}
