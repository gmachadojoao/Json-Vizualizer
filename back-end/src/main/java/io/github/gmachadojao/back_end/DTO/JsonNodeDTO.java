package io.github.gmachadojao.back_end.DTO;

import lombok.Data;

import java.util.List;

@Data
public class JsonNodeDTO {
    private String id;
    private String key;
    private String type; // object, array, string, number, boolean, null
    private Object value; // for primitive values
    private List<JsonNodeDTO> children;
}
