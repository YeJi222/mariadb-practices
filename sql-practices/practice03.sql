-- 테이블간 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select a.emp_no as '사번', concat(last_name, ' ', first_name) as '이름', avg(salary) as '연봉'
from employees a join salaries b on a.emp_no = b.emp_no
where b.to_date='9999-01-01'
group by a.emp_no
order by avg(salary) desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
/* Method1
select employees.emp_no, first_name, title
from employees 안녕하세
join titles
on employees.emp_no=titles.emp_no
order by first_name;
*/ 

-- 공통 컬럼이 emp_no 하나 뿐이므로 natural join이 가능 
select a.emp_no as '사번', concat(last_name, ' ', first_name) as '이름', title as '직책'
from employees a natural join titles b
order by concat(last_name, ' ', first_name);

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요.
-- employees와 dept_emp 테이블 공통 컬럼이 emp_no 하나이므로 natural join 가능 
-- departments 테이블도 마찬가지 공통 컬럼이 하나이므로 natural join이 가능 
select a.emp_no as '사번', concat(last_name, ' ', first_name) as '이름', dept_name as '부서'
from (employees a natural join dept_emp b) natural join departments c
where b.to_date = '9999-01-01' -- 현재 부서 
order by concat(last_name, ' ', first_name);

-- 문제4.
-- 현재, 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select a.emp_no as '사번', concat(last_name, ' ', first_name) as '이름', 
	   salary as '연봉', title as '직책', dept_name as '부서'
from employees a, salaries b, titles c, dept_emp d, departments e
where a.emp_no = b.emp_no and b.emp_no = c.emp_no 
	  and c.emp_no = d.emp_no and d.dept_no = e.dept_no
	  and b.to_date = '9999-01-01'
	  and c.to_date = '9999-01-01'
	  and d.to_date = '9999-01-01'
order by concat(last_name, ' ', first_name);

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. 
-- (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 
-- 이름은 first_name과 last_name을 합쳐 출력 합니다.
select emp_no, concat(first_name, ' ', last_name)
from employees join titles using (emp_no)
where title = 'Technique Leader'
and to_date < '9999-01-01';

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select concat(first_name, ' ', last_name), dept_name, title
from employees a, dept_emp b, departments c, titles d
where a.emp_no = b.emp_no and b.dept_no = c.dept_no and a.emp_no = d.emp_no
and last_name like 'S%';

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
-- 이름, 급여 출력 
-- 급여가 큰 순서대로 출력 
select concat(first_name, ' ', last_name), salary
from (employees natural join salaries b) join titles c using (emp_no)
where title = 'Engineer' and salary >= 40000
and b.to_date = '9999-01-01' and c.to_date = '9999-01-01'
order by salary desc;

select concat(first_name, ' ', last_name), salary
from employees a, salaries b, titles c
where a.emp_no = b.emp_no and b.emp_no = c.emp_no 
and title = 'Engineer' and salary >= 40000
and b.to_date = '9999-01-01' and c.to_date = '9999-01-01'
order by salary desc;

-- 문제8.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select dept_name, avg(salary)
from (salaries a join dept_emp b using (emp_no)) join departments c using (dept_no)
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by dept_name
order by avg(salary) desc;

-- 문제9.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select title, avg(salary)
from titles a join salaries b using(emp_no)
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by title
order by avg(salary) desc;




