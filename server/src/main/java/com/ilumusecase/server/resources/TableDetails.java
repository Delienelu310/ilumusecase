package com.ilumusecase.server.resources;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TableDetails {
    String name;
    String category;
    String adminUsername;
    Integer blindSize;
}
