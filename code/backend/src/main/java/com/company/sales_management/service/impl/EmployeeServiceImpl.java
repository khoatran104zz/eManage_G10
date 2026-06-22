package com.company.sales_management.service.impl;

import com.company.sales_management.dto.request.EmployeeRequest;
import com.company.sales_management.dto.response.EmployeeResponse;
import com.company.sales_management.entity.Branch;
import com.company.sales_management.entity.Employee;
import com.company.sales_management.exception.ResourceNotFoundException;
import com.company.sales_management.repository.BranchRepository;
import com.company.sales_management.repository.EmployeeRepository;
import com.company.sales_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Page<EmployeeResponse> findAll(String search, Integer branchId, Pageable pageable) {
        return employeeRepository.findAllWithFilters(search, branchId, pageable).map(this::toResponse);
    }

    @Override
    public EmployeeResponse findById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nhân viên", "id", id));
        return toResponse(employee);
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = new Employee();
        mapRequestToEntity(request, employee);
        return toResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponse update(Integer id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nhân viên", "id", id));
        mapRequestToEntity(request, employee);
        return toResponse(employeeRepository.save(employee));
    }

    @Override
    public void delete(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nhân viên", "id", id);
        }
        employeeRepository.deleteById(id);
    }

    private void mapRequestToEntity(EmployeeRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        if (request.getBranchId() != null) {
            Branch branch = branchRepository.findById(request.getBranchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Chi nhánh", "id", request.getBranchId()));
            employee.setBranch(branch);
        }
    }

    private EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setPhone(employee.getPhone());
        response.setEmail(employee.getEmail());
        response.setPosition(employee.getPosition());
        if (employee.getBranch() != null) {
            response.setBranchId(employee.getBranch().getId());
            response.setBranchName(employee.getBranch().getName());
        }
        response.setCreatedAt(employee.getCreatedAt());
        response.setUpdatedAt(employee.getUpdatedAt());
        return response;
    }
}
