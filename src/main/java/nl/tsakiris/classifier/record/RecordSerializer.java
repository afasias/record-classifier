package nl.tsakiris.classifier.record;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import nl.tsakiris.classifier.record.Record;

public class RecordSerializer extends JsonSerializer<Record> {

  @Override
  public void serialize(Record record, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeObject(record.getMap());
  }

}
