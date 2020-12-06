package com.ilya.dto;


import com.ilya.entity.Question;
import com.ilya.entity.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TestWithQuestions {
    private Test test;
    private List<Question> questions;
}
