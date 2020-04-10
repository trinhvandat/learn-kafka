package com.spring.kafka.model;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode


public class User implements Serializable {

    private String name;
    private int age;

}
