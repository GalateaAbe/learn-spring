package com.practice.spring_boot_practice_app.officer.entity;

public record Officer(Integer id,
                      OfficerRank rank,
                      String firstName,
                      String lastName) {
}
