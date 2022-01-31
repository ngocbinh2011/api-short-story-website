package com.personal.story.layer.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    @Convert(converter = Permission.RoleConverter.class)
    private Permission role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public enum Permission {
        ADMIN, WRITER, READER;

        public static class RoleConverter implements AttributeConverter<Permission, String>{

            @Override
            public String convertToDatabaseColumn(Permission role) {
                return role.toString();
            }

            @Override
            public Permission convertToEntityAttribute(String s) {
                return Permission.valueOf(s);
            }
        }

    }

}
