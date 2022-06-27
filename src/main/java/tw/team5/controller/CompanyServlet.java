package tw.team5.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.team5.bean.Company;
import tw.team5.service.CompanyService;

@RequestMapping("/company")
@Controller
public class CompanyServlet {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/list")
	public String listCompanies(Model m) {
		List<Company> companies = companyService.getAllCompanies();
		m.addAttribute("companies",companies);
		return "CompanyDashBoard";
	}
	
	@GetMapping("/showForm")
    public String showFormForAdd(Model m) {
		Company company = new Company();
        m.addAttribute("company", company);
        return "CompanyCreate";
    }
	
	@PostMapping("/saveCompany")
	public String saveCustomer(@ModelAttribute("company") Company company,Model m,@RequestParam(value="id") Integer compid) {
		
		//設定輸入錯誤
		Map<String, String> errorMsg = new HashMap<String, String>();
		m.addAttribute("errorMsg", errorMsg);
		if(companyService.isDup(compid)) {
			errorMsg.put("compid", "帳號(統編)重複，請重新輸入新帳號");
			return "CompanyCreate";
		}else {
		companyService.save(company);
		return "redirect:/company/list";
		}
	}
	@GetMapping("/detail")
	public String processShowDetail(@RequestParam("companyId") int detailId,Model m){
		Company companydeatail = companyService.getCompany(detailId);
		m.addAttribute("companydeatail",companydeatail);
		return "CompanyDetail";
	}
	@GetMapping("/showupdateinformation")
	public String showInformaionFromUpdate(@RequestParam("companyId") Integer updateId,Model m){
		Company companyupdate = companyService.getCompany(updateId);
		m.addAttribute("companyupdate",companyupdate);
		return "CompanyUpdate";
	}
	@PostMapping("/update")
	public String processUpdate(Company company){
		companyService.updateCompany(company);
		return "redirect:/company/list";
	}
	@GetMapping("/delete")
	public String processDelete(@RequestParam("companyId") int deleteId){
		companyService.deleteCompany(deleteId);
		return "redirect:/company/list";
	}
	
}
