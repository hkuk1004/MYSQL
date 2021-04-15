-- 사용권한을 가진 데이터베이스만 볼 수 있음
show databases;
create database test default character set utf8;
-- sakila라는 데이터베이스를 사용하겠다
use sakila;
-- 사용 가능한 데이터베이스를 모두 보여줘
-- oracle : select * from tab; 또는 select * from user_tables;
show tables;
use world;
show tables;