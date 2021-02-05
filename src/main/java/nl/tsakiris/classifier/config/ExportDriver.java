package nl.tsakiris.classifier.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = HtmlExportDriver.class, name = "html"),
    @JsonSubTypes.Type(value = JsonExportDriver.class, name = "json"),
})
public interface ExportDriver {
}
