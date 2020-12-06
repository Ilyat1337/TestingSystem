package com.ilya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends Entity {
    private int id;
    private int testId;
    private String text;
    private int correctAnswer;
    private List<Answer> answers;
}
