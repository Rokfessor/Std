package org.mvarlamov.model;

import lombok.Data;

@Data
public class Tag {
    private int id;
    private int dishId;
    private String text;
}
