package com.example.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;
@Service
public class DepartmentServiceIml implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}
	@Override
	public List<Department> fetchDepartmentsList() {
		return departmentRepository.findAll();
	}
	@Override
	public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException   {
		Optional<Department> department=departmentRepository.findById(departmentId);
		if (!department.isPresent()) {
			throw new DepartmentNotFoundException("Department Not exist");
			}
		return department.get();
	}
	@Override
	public void deleteDepartmentById(Long departmentId) {
		   departmentRepository.deleteById(departmentId);
		
	}
	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		Department deptBD=departmentRepository.findById(departmentId).get();
		if (Objects.nonNull(department.getDeptName())&& !"".equalsIgnoreCase(department.getDeptName())) {
			deptBD.setDeptName(department.getDeptName());
		}
		if (Objects.nonNull(department.getDeptAddress())&& !"".equalsIgnoreCase(department.getDeptAddress())) {
			deptBD.setDeptAddress(department.getDeptAddress());
		}
		if (Objects.nonNull(department.getDeptCode())&& !"".equalsIgnoreCase(department.getDeptCode())) {
			deptBD.setDeptCode(department.getDeptCode());
		}
		return departmentRepository.save(deptBD);
		


	}
	@Override
	public Department fetchDepartmentByName(String departmentName) {
		return  departmentRepository.findByDeptName(departmentName) ;
	}
	@Override
	public void deleteDepartmentByName(String departmentName) {
		departmentRepository.deleteByDeptName(departmentName);
		
	}
	 
	

}
