use test;
select year(now()), month(now()), monthname(now()), dayofmonth(now());
-- dayofweek : 일요일(1), 월요일(2), ... 토요일(7)
-- weekday : 월요일(0), 화요일(1), ... 일요일(7)
-- week : 1년중에 몇 째 주인지
select dayname(now()), dayofweek(now()),weekday(now()), week(now());
select to_days(now()) - to_days('1990-5-28');
select to_days(now()) - to_days('1991-7-20');
select now(), date_format(now(),'(%W) %Y/%m/%d (%p)%H:%i:%s');
-- 급여가 1500 이상은 good 아니면 hell
select ename, sal, if(sal >= 1500, 'good','hell') 급여상태 from emp;
select ename, sal, case when sal >= 1500 then 'good'
	when sal < 1500 then 'hell' end 급여실태 from emp;
-- deptno 10 회계, 20 연구소 아니면 영업
select ename,deptno, case deptno when 10 then '회계' when 20 then '연구소'
	end 부서명 from emp;
select ename, deptno, if(deptno=10,'회계', if(deptno=20,'연구소','영업'))
		from emp;
select ename, sal, comm, sal+ifnull(comm,0) from emp;

select ename, sal from emp order by sal desc limit 5;
-- 큰 순서로 인덱스 5번부터 5건
select ename, sal from emp order by sal desc limit 5, 5;
-- 입사일 순서로 6~10번 입사한 사람의 이름과 입사일을 출력
select ename, hiredate from emp order by hiredate limit 5, 5;

select sha('1');

select ename, format(sal*7/3,0), format(sal*7/3,1), format(sal*7/3,2),
	format(sal*7/3,-1), format(sal*7/3,-2) from emp;
    
select ename, job, sal, e.deptno, dname, loc from emp e, dept d where e.deptno = d.deptno;    

select * from dept;

select distinct deptno from emp;
-- ANSI 
select ename, job, sal, e.deptno, dname, loc 
	from emp e  right outer join dept d 
	on e.deptno = d.deptno;
-- 회사에서 급여를 가장 많이 받는 사람의 이름과 급여
select ename, sal from emp where sal = max(sal);
select max(sal) from emp;
-- 먼저 실행할 문장을 괄호에 넣어서 실행 sub query
select ename, sal from emp where sal=(select max(sal) from emp);
-- emp 테이블에서
-- 1. 회사 평균급여보다 급여를 많이 받는 사람의 이름, 급여
select ename, sal from emp where sal > (select avg(sal) from emp);
-- 2. 자기 부서 평균급여보다 급여를 많이 받는 사람의 이름, 급여
select ename, sal from emp e where sal > (select avg(sal) from emp
	where e.deptno = deptno);
-- 3. 자기 부서 평균급여보다 급여를 많이받는 사람의 이름, 급여, 부서평균급여(round)
-- in_line view에서 함수로 생성된 값은 반드시 별칭을 사용해야 한다
select ename, sal, avgSal from emp e, (select deptno, round(avg(sal)) avgSal
	from emp group by deptno) v where e.deptno = v.deptno and sal > avgSal;
-- 4. 입사가 가장 빠른 사람의 이름, 입사일
select ename, hiredate from emp where hiredate = (select min(hiredate) from emp);
-- 5. 인천에서 근무하는 사람의 이름, 급여, 부서명, 근무지 급여큰순 정렬
select ename, sal, dname, loc from emp e, dept d where e.deptno=d.deptno and
	loc='인천' order by sal desc;
-- 6. 사번, 이름, 급여, 부서명, 근무지 부서명순 부서명이 같으면 급여 큰순 정렬
select empno, ename, sal, dname, loc from emp e, dept d where e.deptno=d.deptno
	order by dname, sal desc;
-- 7. 업무별 급여합계, 최대급여, 최소급여
select job, sum(sal), max(sal), min(sal) from emp group by job;