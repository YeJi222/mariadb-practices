-- 혼합 SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from employees join salaries using(emp_no)
where to_date = '9999-01-01'and salary > (
	select avg(salary) 
	from salaries
	where to_date = '9999-01-01'
);

select count(*)
from employees a, salaries b
where a.emp_no = b.emp_no
	and to_date = '9999-01-01'and salary > (
	select avg(salary) 
	from salaries
	where to_date = '9999-01-01'
);

-- 문제2. (X 생략)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select emp_no, concat(first_name, ' ', last_name), avg(salary)

select avg(salary) from salaries;

select dept_name, a.emp_no, salary
from employees a, dept_emp b, departments c, salaries d
where a.emp_no = b.emp_no and b.dept_no = c.dept_no and a.emp_no = d.emp_no
group by dept_name
having salary > (
	select avg(salary)
	from (salaries natural join dept_emp) join departments using(dept_no)
	group by dept_name
);

select avg(salary) 
from (salaries natural join dept_emp) join departments using(dept_no)
group by dept_name;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- 총무 20000 
select dept_name, avg(salary)
from dept_emp a, departments b, salaries c
where a.dept_no = b.dept_no and a.emp_no = c.emp_no
group by dept_name;



-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- Engineer 40000 



-- 문제8. (순수 join 문제) 
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select dept_name as '부서 이름', concat(first_name, ' ', last_name) as '사원이름',
	   salary as '연봉'
from employee, 










