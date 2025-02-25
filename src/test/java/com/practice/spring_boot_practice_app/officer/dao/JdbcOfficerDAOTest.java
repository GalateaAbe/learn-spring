package com.practice.spring_boot_practice_app.officer.dao;

import com.practice.spring_boot_practice_app.officer.entity.Officer;
import com.practice.spring_boot_practice_app.officer.entity.OfficerRank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class JdbcOfficerDAOTest {

    @Autowired
    private JdbcOfficerDAO dao;

    @Autowired
    private JdbcTemplate template;

    private List<Integer> ids() {
        return template.query("SELECT id FROM officers",
                (rs, rowNum) -> rs.getInt("id"));
    }

    @Test
    void save() {
        Officer officer = new Officer(null, OfficerRank.COMMODORE,
                "Wesley", "Crusher");
        officer = dao.save(officer);
        assertNotNull(officer.id());
    }

    @Test
    void findByIdThatExists() {
        ids().forEach(id -> {
            Optional<Officer> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            assertEquals(id, officer.get().id());
        });
    }

    @Test
    void findByIdThatDoesNotExist() {
        assertThat(ids()).doesNotContain(999);
        Optional<Officer> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    void findAll() {
        List<String> dbNames = dao.findAll().stream()
                .map(Officer::lastName)
                .toList();
        assertThat(dbNames).containsAll(List.of(
                "Kirk", "Picard", "Sisko", "Janeway",
                "Archer", "Burnham", "Pike", "Freeman"));
    }

    @Test
    void count() {
        assertEquals(ids().size(), dao.count());
    }

    @Test
    void delete() {
        ids().forEach(id -> {
            Optional<Officer> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            dao.delete(officer.get());
        });
        assertEquals(0, dao.count());
    }

    @Test
    void existsById() {
        ids().forEach(id -> assertTrue(dao.existsById(id)));
    }
}