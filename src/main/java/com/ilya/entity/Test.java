package com.ilya.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test extends Entity {
    private int id;
    private String name;
    private int creatorId;
    private int questionCount;
}
