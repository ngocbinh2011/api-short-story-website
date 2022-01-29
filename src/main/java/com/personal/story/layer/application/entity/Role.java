package com.personal.story.layer.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private Permission permission;

    enum Permission {
        ADMIN, WRITER, READER;

        static class RoleConverter implements AttributeConverter<Role, String>{

            @Override
            public String convertToDatabaseColumn(Role role) {
                return null;
            }

            @Override
            public Role convertToEntityAttribute(String s) {
                return null;
            }
        }
    }
}
