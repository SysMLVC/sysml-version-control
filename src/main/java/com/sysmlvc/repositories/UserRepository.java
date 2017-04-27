package com.sysmlvc.repositories;

/**
 * Created by Jason Han on 6/10/16.
 */

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.sysmlvc.domains.nodes.User;

@Repository
public interface UserRepository extends GraphRepository<User> {

    @Query("MATCH (u:User) WHERE u.username = {0} RETURN u")
    User findByUsername(String username);

    @Query("MATCH (u:User) WHERE u.email = {0} RETURN u")
    User findByEmail(String email);

    @Query("MATCH (u:User) WHERE u.activation = {0} RETURN u")
    User findByActivation(String activation);

    @Query("MATCH (s:Specialty)-[:USER_SPECIALTIES]-(u:User) WHERE ID(s) = {0} RETURN u")
    Set<User> findBySpecialty(Long id);

    @Query("MATCH (u:User) WHERE lower(u.fullName) CONTAINS lower({0}) RETURN u")
    Set<User> searchByName(String name);
}