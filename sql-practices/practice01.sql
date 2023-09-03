use employees;

show tables;

select * from employees;

select * from salaries where emp_no=10002;

-- select 연습
-- 예제1: departments 테이블의 모든 데이터를 출력
select * from departments;

-- 프로젝션(projection)
-- 예제2: employees 테이블에서 직원 이름, 성별, 입사일을 출력
select first_name as '이름',
		gender as '성',
        hire_date as '입사일'
from employees;

-- distinct
-- 예제1: titles 테이블에서 모든 직급을 출력 
select title from titles;

-- 기본 SQL 문제입니다.

-- 문제1.
-- 사번이 10944인 사원의 이름은(전체 이름)
-- Ans) Remko
select 
	concat(first_name, ' ', last_name) 이름
from 
	employees 
where 
	emp_no = 10944;

-- 문제2. 
-- 전체직원의 다음 정보를 조회하세요. 가장 선임부터 출력이 되도록 하세요. 
-- 출력은 이름, 성별, 입사일 순서이고 “이름”, “성별”, “입사일로 컬럼 이름을 대체해 보세요.
-- Ans) 300024 rows
select 
	first_name as '이름', 
	gender as '성별', 
	hire_date as '입사일' 
from 
	employees 
order by 
	hire_date;

-- 문제3.
-- 여직원과 남직원은 각 각 몇 명이나 있나요?
-- Ans) 179973, 120051
select 
	gender 성별, count(*) 수 
from 
	employees
group by 
	gender;

-- 문제4.
-- 현재(to_date='9999-01-01') 근무하고 있는 직원 수는 몇 명입니까? (salaries 테이블을 사용합니다.) 
-- +) select count(*) from salaries where NOW() BETWEEN from_date AND to_date;
-- +) select count(*) from salaries where from_date <= NOW() AND NOW() <= to_date;
-- Ans) 240124
select 
	count(*) 
from 
	salaries 
where 
	to_date = '9999-01-01';

-- 문제5.
-- 부서는 총 몇 개가 있나요?
-- Ans) 9
select 
	count(distinct dept_name) 
from 
	departments;

-- 문제6.
-- 현재(to_date='9999-01-01') 부서 매니저는 몇 명이나 있나요?(역임 매너저는 제외)
-- Ans) 9
select 
	count(*) 
from 
	dept_manager 
where 
	to_date='9999-01-01';

-- 문제7. (X 생략)
-- 전체 부서를 출력하려고 합니다. 순서는 부서이름이 긴 순서대로 출력해 보세요.
select 
	distinct dept_name 
	from departments 
order by 
	length(dept_name) desc;

-- 문제8.
-- 현재 급여가 120,000이상 받는 사원은 몇 명이나 있습니까?
-- Ans) 2159
--select count(*) from employees, salaries where salary >= 120000 and employees.emp_no = salaries.emp_no and NOW() between from_date and to_date;

select 
	count(*) 
from 
	salaries 
where 
	salary >= 120000 
	and to_date='9999-01-01';

-- 문제9.
-- 어떤 직책들이 있나요? 중복 없이 이름이 긴 순서대로 출력해 보세요.
-- Ans) 7 rows 
select 
	distinct title 
from 
	titles 
order by 
	length(title) desc;

-- 문제10
-- 현재 Enginner 직책의 사원은 총 몇 명입니까?
-- Ans) 30983
-- select count(*) from employees, titles where title='Engineer' AND employees.emp_no=titles.emp_no AND NOW() BETWEEN from_date AND to_date;
select 
	count(*) 
from 
	titles 
where 
	title='Engineer' 
	and to_date='9999-01-01';

-- 문제11
-- 사번이 13250(Zeydy)인 직원이 직책 변경 상황을 시간순으로 출력해보세요.
select * 
from 
	titles 
where 
	emp_no = 13250 
order by 
	from_date;

