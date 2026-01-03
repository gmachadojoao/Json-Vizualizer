package io.github.gmachadojao.back_end.Services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.gmachadojao.back_end.DTO.JsonNodeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JsonServices {

    private final ObjectMapper objectMapper;

    public String parseJson(String json) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNodeDTO rootDto = parse("root", rootNode);
        return objectMapper.writeValueAsString(rootDto);
    }

    private JsonNodeDTO parse(String key, JsonNode node) {
        JsonNodeDTO dto = new JsonNodeDTO();
        dto.setId(UUID.randomUUID().toString());
        dto.setKey(key);
        dto.setType(node.getNodeType().name());

        if (node.isObject()) {
            List<JsonNodeDTO> children = new ArrayList<>();
            node.fields().forEachRemaining(entry ->
                    children.add(parse(entry.getKey(), entry.getValue()))
            );
            dto.setChildren(children);
        } else if (node.isArray()) {
            List<JsonNodeDTO> children = new ArrayList<>();
            int index = 0;
            for (JsonNode element : node) {
                children.add(parse("[" + index + "]", element));
                index++;
            }
            dto.setChildren(children);
        } else {
            if (node.isTextual()) {
                dto.setValue(node.asText());
            } else if (node.isNumber()) {
                dto.setValue(node.numberValue());
            } else if (node.isBoolean()) {
                dto.setValue(node.asBoolean());
            } else if (node.isNull()) {
                dto.setValue(null);
            }
        }

        return dto;
    }
}
