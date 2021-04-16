use test;

select * from emp;
-- self join : 재귀적(같은 테이블 내에 자식.부모) 관계
select w.ename 직원, m.ename 관리자 from emp m, emp w
	where w.mgr = m.empno;
select ename, job, sal, d.deptno, dname from dept d left outer join emp e
	on e.deptno = d.deptno;
-- mgr code null 인 경우에 empno null 없어도 보여줘
select w.ename 직원, m.ename 관리자 from emp m right outer join emp w
	on w.mgr = m.empno;
    
select ename, sal from emp e where sal > (select avg(sal) from emp
	where e.deptno = deptno);
-- 관리자만 출력
select ename from emp e where exists (select empno from emp where e.empno = mgr);
select ename from emp where empno in (select mgr from emp);

-- topN 급여가 6-10등 출력
select ename, sal from emp order by sal desc limit 5,5;
create user 'kim'@'localhost' identified by '1234';
grant all on *.* to 'kim'@'localhost';
create user 'kang'@'%' identified by '1234';
grant all on test.* to 'kang'@'%';

/* 
drop table emp;
drop table dept;
-- data Export 네비게이터에서 백업할 테이블 지정해서 경로 설정하고 백업
-- data Import/Restore 네비게이터에서 백업받을 테이블 지정해서 경로 설정하고 백업
*/

drop procedure if exists dept_insert;
delimiter //
create procedure dept_insert(
	vdeptno int, vdname varchar(20), vloc varchar(20))
begin
	insert into dept value(vdeptno, vdname, vloc);
end;
//
delimiter ;

call dept_insert(50,'대박','이대');
select * from dept;
set global log_bin_trust_function_creators = 1;
drop function if exists getDur;
delimiter //
create function getDur(vhiredate date)
	returns int
begin
	declare dur int;
    set dur = year(current_date()) - year(vhiredate);
    return dur;
end //
delimiter ;
select ename, job, sal, hiredate, getDur(hiredate) 근무일 from emp;
select getDur('1990-05-28');

drop table sawon;
drop table sal_t;
drop table warehouse;
drop table product;
create table sawon (empno int(4) not null primary key, ename varchar(20), tel varchar(30));
create table sal_t (sal_id int not null primary key auto_increment,
 empno int(4) references sawon(empno), sal int(10) default 0);
delimiter //
-- for each row를 사용하면 new와 old를 사용가능
create trigger insert_sal
   after insert on sawon for each row
  begin
   insert into sal_t (empno) values(new.empno);
end //
delimiter ;
insert into sawon values(1111,'철수','010-1111-1111');
insert into sawon values(2222,'영희','010-1111-1234');
select * from sal_t;
create table product(pno varchar(10) not null primary key,
	pname varchar(20), stock int default 0);
create table warehouse (num int not null primary key auto_increment,
	pno varchar(10) references product(pno), qty int);
delimiter //
create trigger wh_insert 
	after insert on warehouse for each row
begin
	update product set stock = stock + new.qty where pno=new.pno;
end //
delimiter ;
insert into product values('a1','사과',0);
insert into product values('b1','바나나',0);
insert into product values('c1','귤',0);
select * from product;
insert into warehouse(pno,qty) values ('a1',11);
insert into warehouse(pno,qty) values ('a1',3);
insert into warehouse(pno,qty) values ('b1',11);
insert into warehouse(pno,qty) values ('c1',7);

delimiter //
create trigger wh_delete
	after delete on warehouse for each row
begin
	update product set stock = stock - old.qty where pno = old.pno;
end //
delimiter ;

select * from warehouse;

delimiter //
create trigger wh_update
	after update on warehouse for each row
begin
	update product set stock = stock - old.qty + new.qty where pno=old.pno;
end //
delimiter ;

update warehouse set qty = 7 where num = 3;

