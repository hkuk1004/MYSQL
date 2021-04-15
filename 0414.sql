/*create database test default character set utf8;*/

use test;

create table dept(deptno int not null primary key, dname varchar(30),loc varchar(30));
-- deptno fk, 외래키 참조키
create table emp(empno int not null primary key, ename varchar(20),
	job varchar(20), mgr int, hiredatedate date, sal int,
	comm int, deptno int references dept(deptno));
 -- mysql 관리하기위한 테이블   
use mysql;
show tables;
select * from db;
desc db; -- describe(table 구조를 볼 때)
select * from user;
use test;
insert into dept(deptno, dname, loc) values(10,'회계팀','서울');
insert into dept values(20,'연구팀','인천');
insert into dept values(30,'영구팀','대전');
insert into dept values(40,'운영팀','부산');

set global local_infile = 1;

load data infile 'C:/ProgramData/MySQL/mySQL Server 8.0/Uploads/emp.txt'
	into table emp;

select * from emp;

desc emp;

select empno, ename, job, sal from emp;

select empno, ename, job, sal from emp where sal > 3000;

select empno, ename, job, sal from emp where sal <= 3000; 

select empno, ename, job, sal from emp where job = 'MANAGER' and sal > 2000;

select empno, ename, job, sal from emp where not job = 'MANAGER' or sal > 2000;

select empno, ename, job, sal from emp where job = 'MANAGER' or 'SALESMAN';

select empno, ename, job, sal from emp where job in ('MANAGER','SALESMAN');

select empno, ename, job, sal from emp where job not in('MANAGER','SALESMAN');

select empno, sal from emp where sal between 1500 and 2500;

select empno, ename, job, null from emp where comm is null;

select empno, ename, job, null from emp where comm is not null;

select empno, ename, job, sal from emp
where job = 'SALESMAN' or job = 'MANAGER' and sal >= 2000;

select empno, ename, job, sal from emp
where job = 'SALESMAN' or (job = 'MANAGER' and sal >= 2000);

select empno, ename, job, sal from emp
where job in('SALESMAN','MANAGER') and sal >= 2000;

alter table emp change hiredatedate hiredate date;

select ename,hiredate from emp where hiredate between '1981-01-01' and '1981-12-01';

select ename,hiredate from emp where hiredate like '1981%';

select ename from emp where ename like 'S%';

select ename from emp where ename like 'S%';

select ename from emp where ename like '%S%';
-- L이 두 개 있는 사람
select ename from emp where ename like '%L%L%';
-- L 이 두개 연달아 있는 이름
select ename from emp where ename like '%LL%';
-- 이름의 두번째 글자가 D 인 사람
select ename from emp where ename like '_D%';
-- 이름의 세번째 글자가 A 인 사람
select ename from emp where ename like '__A%';
-- 이름의 끝에서 두번째 글자가 T인사람
select ename from emp where ename like '%T_';
-- 신문이나 게시글 조회할 때 사용
-- select content from newspaper where title like '%코로나%';
use test;
update emp set ename='SMI%TH' where ename='SMITH';
select ename from emp;

-- %가 이름이 있는 데이터를 출력하세요
select * from emp where ename like '%/%%' escape '/';

select * from emp where ename like '%\%%';

select * from emp where ename not like '%\%%';

-- order by 컬럼명(순서, 식, 별칭, sub query)
-- desc 내림차순(큰순), default asc(작은순, 오름차순)
select ename, job, sal, deptno from emp order by deptno;

select ename, job, sal, deptno from emp order by deptno, sal desc;

select ename, job, sal, deptno from emp order by deptno desc, sal desc;

select ename, job, sal, deptno from emp order by deptno desc, job;

-- distinct 중복제거
select distinct deptno from emp;

select ename '이름', job, sal, deptno from emp order by deptno; -- "", '' 구별없음

select ename as '이름', job 업무, sal '급 여', deptno from emp order by 이름;

select ename as '이름', job 업무, sal '급 여', deptno from emp order by 2;

select ename, max(sal) from emp group by ename;

select deptno, max(sal), avg(sal), sum(sal), count(*) from emp group by deptno;

select deptno, max(sal), avg(sal), sum(sal), count(*) 
from emp group by deptno having count(*) >= 5;
-- ceil, ceiling 올림, floor 내림, truncate 절사, round 반올림
select round(3.7), ceil(3.7), floor(3.7), truncate(3.7,0);
select round(3.1), ceil(3.1), floor(3.1), truncate(3.1,0);
select round(-3.7), ceil(-3.7), floor(-3.7), truncate(-3.7,0);
select round(-3.1), ceil(-3.1), floor(-3.1), truncate(-3.1,0);
select round(sal/3), round(sal/3,1), round(sal/3,2), round(sal/3,-1), round(sal/3,-2) from emp;
-- oracle truncate -> trunc
select truncate(sal/3,0),truncate(sal/3,1), truncate(sal/3,2),
truncate(sal/3,-1),truncate(sal/3,-2) from emp;
select pow(2,3), power(2,3), mod(10,3), greatest(2,7,8), least(2,7,8);
select ename, substr(ename, 2,2), substring(ename,2,2) from emp;
-- 이름(급여) || concat
select concat(ename,'(',sal,')') from emp;
select ename, insert(ename,2,2,'대박') from emp;
select job, replace(job,'MAN','PERSON') from emp;
select ename, instr(ename,'A') from emp; -- 문자 A가 있는 위치
-- mid substr, substring가 같다
select ename, left(ename,2), right(ename,2), mid(ename,2,2) from emp;
-- 업무가 manager 이거나 analyst 데이터를 추출
select ename, job from emp where job in('manager','analyst');
select ename, lower(ename), upper(ename), lcase(ename), ucase(ename) from emp;
select ename, reverse(ename) from emp;
-- sysdate, now()
select now(), sysdate(), current_timestamp();
select now()+0, sysdate()+0, current_timestamp()+0; -- 대쉬(-)를 없애줌

select curdate(), current_date();
select curtime(), current_time();
select hiredate, date_add(hiredate, interval 2 day) from emp;

select hiredate, date_add(hiredate, interval 2 day), date_add(hiredate, interval 2 year)
, date_add(hiredate, interval 2 month), date_add(hiredate, interval 2 hour)
, date_add(hiredate, interval 2 minute), date_add(hiredate, interval 2 second)
from emp;

select hiredate, date_sub(hiredate, interval 2 day), date_sub(hiredate, interval 2 year)
, date_sub(hiredate, interval 2 month), date_sub(hiredate, interval 2 hour)
, date_sub(hiredate, interval 2 minute), date_sub(hiredate, interval 2 second)
from emp;