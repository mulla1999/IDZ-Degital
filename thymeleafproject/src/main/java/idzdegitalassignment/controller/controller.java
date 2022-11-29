package idzdegitalassignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import idzdegitalassignment.entity.Employee;
import idzdegitalassignment.response.ResponseApi;
import idzdegitalassignment.service.UserService;

@Controller
public class controller {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String getAllEmployee(Model model) {
		model.addAttribute("ListEmployee", userService.findAllEmp());
		return "index";

	}

	@GetMapping("EmployeeForm")
	public String addEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_Employee";
	}

	@PostMapping("saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		userService.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormUpdate/{id}")
	public String showFromUpdate(@PathVariable(value = "id") int id, Model model) {
		// get emp from service
		Employee employeeById = userService.updateEmployee(id);
		// set emp as model attributr to form
		model.addAttribute("employee", employeeById);
		return "update_employee";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable(value = "id") int id, Model model) {
		String deleteEmployee = userService.deleteEmployee(id);
		model.addAttribute("employee", deleteEmployee);
		return "redirect:/";
	}

	@GetMapping("/getall")
	public ResponseEntity<ResponseApi> getAll() {
		List<Employee> employee = userService.findAllEmp();
		return new ResponseEntity<>(
				ResponseApi.builder().data(employee).message("Employee Data Fetched Successfuly").error(false).build(),
				HttpStatus.OK);
	}

}
