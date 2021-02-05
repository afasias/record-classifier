package nl.tsakiris.classifier.condition;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import nl.tsakiris.classifier.condition.parser.DefaultFullParser;
import nl.tsakiris.classifier.condition.parser.FullParser;

public class ConditionDeserializer extends JsonDeserializer<Condition> {

  private final FullParser fullParser = new DefaultFullParser();

  @Override
  @SneakyThrows
  public Condition deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) {
    String text = jsonParser.getText();
    return fullParser.parse(text);
  }

}
