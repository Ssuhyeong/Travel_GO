package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<Board, Integer> {

    // 로그인
    @Query(value = "select id, password, name from user where id=:id and password=:password", nativeQuery = true)
    User login(@Param("id") String id,
               @Param("password") String password);

    // 회원가입
    @Transactional
    @Modifying
    @Query(value = "insert into user (id, password, name) values (:id, :password, :name)", nativeQuery = true)
    void regist(@Param("id") String id,
                @Param("password") String password,
                @Param("name") String name);

    // 프로필 확인
    @Query(value = "select id, password, name, from user where name=:name", nativeQuery = true)
    User profile(@Param("name") String name);

    // 계정 삭제
    @Transactional
    @Modifying
    @Query(value = "delete from User where id=:id", nativeQuery = true)
    void delete(@Param("id") String id);

    // ID로 유저 정보 확인하기
    @Query(value = "select id, password, name from user where id = :id", nativeQuery = true)
    User searchbyid(@Param("id") String id);

    // 유저 정보 수정
    @Transactional
    @Modifying
    @Query(value = "update user set id=:id, password=:password, name=:name where id=:start_id", nativeQuery = true)
    void update(
            @Param("id") String id,
            @Param("password") String password,
            @Param("name") String name,
            @Param("start_id") String start_id
            );
}
