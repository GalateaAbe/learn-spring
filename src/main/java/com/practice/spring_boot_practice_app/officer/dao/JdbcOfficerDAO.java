package com.practice.spring_boot_practice_app.officer.dao;

import com.practice.spring_boot_practice_app.officer.entity.Officer;
import com.practice.spring_boot_practice_app.officer.entity.OfficerRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class JdbcOfficerDAO implements OfficerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertOfficer;

    @Autowired
    public JdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Officer> officerRowMapper =
            (resultSet, rowNum) -> new Officer(resultSet.getInt("id"),
                    OfficerRank.valueOf(resultSet.getString("rank")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));

    @Override
    public Officer save(Officer officer) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(officer);
        Number newId = insertOfficer.executeAndReturnKey(parameters);
        return new Officer(newId.intValue(),
                officer.rank(),
                officer.firstName(),
                officer.lastName());
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        try (Stream<Officer> stream =
                     jdbcTemplate.queryForStream(
                             "SELECT * FROM officers WHERE id=?",
                             officerRowMapper,
                             id)) {
            return stream.findFirst();
        }
    }

    @Override
    public List<Officer> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM officers", officerRowMapper);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject(
                "SELECT count(*) FROM officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        jdbcTemplate.update("DELETE FROM officers WHERE id=?", officer.id());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM officers WHERE id=?)",
                Boolean.class, id);
    }
}
