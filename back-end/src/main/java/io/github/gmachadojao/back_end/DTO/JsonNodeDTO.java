package io.github.gmachadojao.back_end.DTO;

import io.github.gmachadojao.back_end.Config.JsonEnum;
import lombok.Data;

import java.util.List;

@Data
public class JsonNodeDTO {
    private String id;
    private String key;
    private JsonEnum type;
    private Object value;
    private List<JsonNodeDTO> children;
}
