package nl.tsakiris.classifier.condition;

import nl.tsakiris.classifier.record.Record;

public interface Condition {

  boolean test(Record record);

}
